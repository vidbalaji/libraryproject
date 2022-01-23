import { Book } from "./book";

export class Rentbook {


    rentBookId!: number;
    issueDate!: string;
    dueDate!: string;
    returnDate!: string;
    book!:Book;
}
