import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { GroupService } from 'src/app/service';
import { ConfigService } from 'src/app/service';
import { UserService } from 'src/app/service';

@Component({
  selector: 'app-group-list',
  templateUrl: './group-list.component.html',
  styleUrls: ['./group-list.component.css']
})
export class GroupListComponent implements OnInit {
  @Input() groups: any[]
  editing = false;
  editingGroup


  constructor(
    private groupService: GroupService,
    private router: Router
    
  ) {}

  ngOnInit() {}

  deleteGroup(groupId: number) {
    console.log(groupId)
    
    this.groupService.delete(groupId).subscribe((groups) => {this.groupService.getGroups()});
  }
  editGroup(groupId: number) {
    this.editing = true;
    this.editingGroup = groupId
  }

  onSubmit(){

  }


}
