import { Rentbook } from "./rentbook";

export class User {
   
    id!:number;
    user!:string;
    gender!:string;
    password!:string;
    userRentBookList: Rentbook[] = [];
}
