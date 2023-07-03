import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService, UserService } from 'src/app/service';
import {Subject} from 'rxjs/Subject';
import {takeUntil} from 'rxjs/operators';


interface DisplayMessage {
  msgType: string;
  msgBody: string;
}

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {
title = 'Edit Profile'
form: FormGroup;
user;
submitted = false;
  notification: DisplayMessage;
  returnUrl: string;
  username: AbstractControl;
  password: AbstractControl;
  firstName: AbstractControl;
  lastName: AbstractControl;
  email: AbstractControl;

  private ngUnsubscribe: Subject<void> = new Subject<void>();


  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private userService: UserService,
    private route: ActivatedRoute,
    private authService: AuthService,

  ) { }

  ngOnInit() {
    this.user = this.userService.currentUser
    this.route.params
    .pipe(takeUntil(this.ngUnsubscribe))
    .subscribe((params: DisplayMessage) => {
      this.notification = params;
    });
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    this.form = this.formBuilder.group({
      username: ['', Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(64)])],
      password: ['', Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(32)])],
      firstName: [''],
      lastName: [''],
      email: ['']
    });
    this.form.get("username").setValue(this.user.username);
    this.form.get("firstName").setValue(this.user.firstName);
    this.form.get("lastName").setValue(this.user.lastName);
    this.form.get("email").setValue(this.user.email);

  }

  onSubmit(){
    this.notification = undefined;
    this.submitted = true;
    this.authService.updateUser(this.form.value)
    .subscribe(data => {
      console.log(data);
      this.authService.login(this.form.value).subscribe(() => {
        this.userService.getMyInfo().subscribe();
      });
      this.router.navigate([this.returnUrl]);
    },
      error => {
        this.submitted = false;
        console.log('Update error');
        this.notification = { msgType: 'error', msgBody: error['error'].message };
      });
  }

  

}
