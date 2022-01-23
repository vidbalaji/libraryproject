import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BooklistComponent } from './booklist/booklist.component';
import { RentbooklistComponent } from './rentbooklist/rentbooklist.component';
import { UserlistComponent } from './userlist/userlist.component';

const routes: Routes = [{ path: 'books', component: BooklistComponent },
{path: 'rentBook/:id', component: RentbooklistComponent},
{ path: 'userList', component: UserlistComponent },
{ path: '', redirectTo: '/books', pathMatch: 'full' },
{ path: '**', redirectTo: '/books', pathMatch: 'full' }];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
