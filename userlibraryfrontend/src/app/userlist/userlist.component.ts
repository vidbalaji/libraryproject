import { Component, OnInit } from '@angular/core';
import { LibraryserviceService } from '../libraryservice.service';
import { User } from '../user';

@Component({
  selector: 'app-userlist',
  templateUrl: './userlist.component.html',
  styleUrls: ['./userlist.component.css']
})
export class UserlistComponent implements OnInit {

  users!: User[];
  constructor(private libraryService: LibraryserviceService) { }

  ngOnInit(): void {
    this.listUsers();
  }

  listUsers() {
    this.libraryService.getUserList().subscribe(  data => {
      this.users = data;
      this.libraryService.setUserList(this.users);
    });
  }
  

}
