import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject } from 'rxjs/Subject';
import { takeUntil } from 'rxjs/operators';
import { UserService } from 'src/app/service';
import { ReactService } from 'src/app/service/react.service';
import { PostService } from 'src/app/service/post.service.service';

interface DisplayMessage {
  msgType: string;
  msgBody: string;
}


@Component({
  selector: 'app-post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.css']
})
export class PostListComponent implements OnInit {
  @Input() posts: any[]
  editing = false;
  form: FormGroup
  private ngUnsubscribe: Subject<void> = new Subject<void>();
  notification: DisplayMessage;
  returnUrl: string;
  user: string



  constructor(
    private postService: PostService,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private userService: UserService,
    private reactService: ReactService,

  ) {}

  ngOnInit() {
    this.user = this.userService.currentUser.username;
  }

  checkUser(username: string) {
    if (username === this.user){
      return true;
    } else{
      return false;
    }
  }

  deletePost(postId: number) {
    
    this.postService.delete(postId).subscribe((posts) => {this.postService.getPosts()});
  }

  editPost(postId, postContent) {
    this.editing = true;
    this.route.params
      .pipe(takeUntil(this.ngUnsubscribe))
      .subscribe((params: DisplayMessage) => {
        this.notification = params;
      });
    // get return url from route parameters or default to '/'
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    this.form = this.formBuilder.group({
      id: postId,
      content: ['', Validators.compose([Validators.required, Validators.minLength(3), Validators.maxLength(64)])],
    });
    this.form.get("content").setValue(postContent)
  }

  onSubmit(){
    this.postService.edit(this.form.value).subscribe((result) => {
    });
  }

  react(postId, typeR){
    var obj1 = {}
    obj1["post"] = postId
    obj1["reactionType"] = typeR    
    this.reactService.add(obj1)
  }
}
