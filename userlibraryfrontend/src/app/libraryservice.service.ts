import { KeyValue } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { Book } from './book';
import { Rentbook } from './rentbook';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class LibraryserviceService {



  private baseUrl = '/libraryservice/';
  //private bookUrl = 'http://localhost:8765/libraryservice1/books';

  private bookUrl = this.baseUrl + 'books';
  private userUrl = this.baseUrl + 'users';
  users!: User[];
  userMap!: Map<number, string>;
  userName!:any;
  constructor(private httpClient: HttpClient) { }
  ngInit() {
    this.userName = '';
  
    this.getUserList();
   
  }
  getBookList(): Observable<Book[]> {
    return this.httpClient.get<Book[]>(this.bookUrl);
  }
  getUserList(): Observable<User[]> {
    return this.httpClient.get<User[]>(this.userUrl);
  }

  getRentBookList(id: number): Observable<Rentbook[]> {
    return this.httpClient.get<Rentbook[]>(this.userUrl + '/rentbooks/' + id);
  }


  setUserList(users: User[]) {
    this.users = users;
    this.userMap = new Map<number, string>();
    for (let i = 0; i < users.length; i++) {
      this.userMap.set(users[i].id,users[i].user);
    }
    console.log("User " + this.userMap.get(2));
  }

  findUserNameFromUserId(currentId:number): string{
    
      this.userName = this.userMap.get(currentId);
   console.log("second time" +  currentId); console.log(this.userMap);
   let cNo = currentId;
   

   this.userMap.forEach((value: string, key: number) => {
    console.log(key, value);
    if(currentId==key){
      this.userName = value;
     
    }
  });
    return this.userName!;
  }


}

interface GetResponse {
  _embedded: {
    books: Book[];
  }
}

