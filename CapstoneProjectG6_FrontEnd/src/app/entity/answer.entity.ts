import { Question } from "./question.entity";

export interface Answer {
  imageFile: File;
  id: number;
  description_answer: string;
  img_src: string;
  status: boolean;
  datetime: string;
  question: Question;
  approved_by: string;
  created_by: string;
}
