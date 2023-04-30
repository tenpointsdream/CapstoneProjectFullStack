import { Question } from "./question.entity";
import { User } from "./user.entity";

export interface Answer {
    id: number;
    description_answer: string;
    img_src: string;
    status: string;
    datetime: string;
    question: Question;
    approved_by: User;
    created_by: User;
}