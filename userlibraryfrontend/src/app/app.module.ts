import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { BooklistComponent } from './booklist/booklist.component';
import { UserlistComponent } from './userlist/userlist.component';
import { RentbooklistComponent } from './rentbooklist/rentbooklist.component';
@NgModule({
  declarations: [
    AppComponent,
    BooklistComponent,
    UserlistComponent,
    RentbooklistComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
