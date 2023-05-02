import { Question } from "./question.entity";

export interface Answer {
  id: number;
  description_answer: string;
  img_src: string;
  imageFile: File;
  status: boolean;
  datetime: string;
  question: Question;
  approved_by: string;
  created_by: string;
}
