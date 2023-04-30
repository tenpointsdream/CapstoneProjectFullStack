import { Answer } from "./answer.entity";
import { User } from "./user.entity";

export interface Question {
    id: number;
    description: string;
    imageSrc: string;
    status: string;
    topic: string;
    title: string;
    datetime: string;
    answers: Answer[];
    qcreated_by: User;
    qapproved_by: User;
}
