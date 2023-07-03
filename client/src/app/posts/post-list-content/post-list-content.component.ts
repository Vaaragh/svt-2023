import { Component, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';
import { PostService } from 'src/app/service/post.service.service';

@Component({
  selector: 'app-post-list-content',
  templateUrl: './post-list-content.component.html',
  styleUrls: ['./post-list-content.component.css']
})
export class PostListContentComponent implements OnInit {
  postList: any[]

  constructor(
    private postService:PostService,
    private router:Router,

    
  ) { }

  ngOnInit() {
    this.getPosts()
  }

  getPosts(){
    this.postService.getPosts().subscribe((posts) => (this.postList= posts))
  }
  navigateTo(){
    this.router.navigate(['posts/create'])
  }
  sort(arg){
    if (arg == "asc"){
      console.log(this.postList)
      this.postList.sort(this.sortAsc)
    }else {
      this.postList.sort(this.sortDesc)
    }
  }

  sortAsc(date1,date2){
    let a = date1.created
    let b = date2.created
    let dateA = new Date(a[0],a[1],a[2],a[3],a[4],a[5])
    let dateB = new Date(b[0],b[1],b[2],b[3],b[4],b[5])
    console.log(dateA)

    return dateA > dateB ? 1:-1;
  }

  sortDesc(date1,date2){
    let a = date1.created
    let b = date2.created
    let dateA = new Date(a[0],a[1],a[2],a[3],a[4],a[5])
    let dateB = new Date(b[0],b[1],b[2],b[3],b[4],b[5])
    return dateA < dateB ? 1:-1;  }




}
