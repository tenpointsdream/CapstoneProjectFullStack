import { Answer } from "./answer.entity";

export interface Question {
    id: number;
    descriptionQuestion: string;
    imageSrc: string;
    imageFile: File | null;
    status: boolean;
    topic: string;
    title: string;
    datetime: string;
    answers: Answer[];
    qcreated_by: string;
    qapproved_by: string;
}
