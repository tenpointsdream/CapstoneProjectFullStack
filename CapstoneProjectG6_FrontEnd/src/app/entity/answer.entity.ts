import { Question } from "./question.entity";
import { User } from "./user.entity";

export interface Answer {
    id: number;
    description_answer: string;
    img_src: string;
    status: boolean;
    datetime: string;
    question: Question;
    approved_by: string;
    created_by: string;
}
