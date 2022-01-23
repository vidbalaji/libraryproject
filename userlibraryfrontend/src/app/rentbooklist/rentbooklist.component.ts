import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { LibraryserviceService } from '../libraryservice.service';
import { Rentbook } from '../rentbook';

@Component({
  selector: 'app-rentbooklist',
  templateUrl: './rentbooklist.component.html',
  styleUrls: ['./rentbooklist.component.css']
})
export class RentbooklistComponent implements OnInit {
  rentBooks!: Rentbook[];
  userName! : string;
  currentId!: any;
  constructor(private libraryService: LibraryserviceService,private route : ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {
      this.listRentBooks();
    });
  }

  listRentBooks() {
    const hasId: boolean = this.route.snapshot.paramMap.has('id');

    if (hasId) {
      // get the "id" param string. convert string to a number using the "+" symbol
      this.currentId = this.route.snapshot.paramMap.get('id');
    }
    else {
      // not category id available ... default to category id 1
      this.currentId = 1;
    }

    this.userName = this.libraryService.findUserNameFromUserId(this.currentId );
    console.log(this.userName);
    this.libraryService.getRentBookList(this.currentId ).subscribe(  data => {
      this.rentBooks = data;
    });
  }
}
