import { Component, OnInit } from '@angular/core';
import { Book } from '../book';
import { LibraryserviceService } from '../libraryservice.service';

@Component({
  selector: 'app-booklist',
  templateUrl: './booklist.component.html',
  styleUrls: ['./booklist.component.css']
})
export class BooklistComponent implements OnInit {
  books!: Book[];
  constructor(private libraryService: LibraryserviceService) { }

  ngOnInit(): void {
    this.listBooks();
  }

   listBooks() {
    this.libraryService.getBookList().subscribe(  data => {
      this.books = data;
    });
  }
  
 

}


