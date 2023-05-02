import { Answer } from "./answer.entity";
import { User } from "./user.entity";

export interface Question {
    id: number;
    descriptionQuestion: string;
    imageSrc: string;
    status: boolean;
    topic: string;
    title: string;
    datetime: string;
    answers: Answer[];
    qcreated_by: string;
    qapproved_by: string;
}
