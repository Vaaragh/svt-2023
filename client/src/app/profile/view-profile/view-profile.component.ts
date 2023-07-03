import { Component, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { ConfigService, GroupService, UserService } from 'src/app/service';


@Component({
  selector: 'app-view-profile',
  templateUrl: './view-profile.component.html',
  styleUrls: ['./view-profile.component.css']
})
export class ViewProfileComponent implements OnInit {

  user;
  groupList: any[]

  constructor(
    private router:Router,
    private userService: UserService,
    private config: ConfigService,
    private groupService: GroupService,
  ) {}

  ngOnInit() {
    this.user = this.userService.currentUser
    this.getUserGroups()
    console.log(JSON.stringify(this.groupList))
  }


  navigateTo(){
    this.router.navigate(['users/profile/edit'])
  }

  navigateToEdit(){
    this.router.navigate(['groups/create'])
  }

  getUserGroups(){
    this.groupService.getUserGroups().subscribe((groups) => (this.groupList= groups))

  }

}
