import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Answer} from "../entity/answer.entity";

@Injectable({
  providedIn: 'root'
})
export class AnswerService {
  private baseUrl: string = "http://localhost:8080/customer";
  constructor(private httpClient: HttpClient) { }

  getAnswers() {
    return this.httpClient.get<Answer[]>(`${this.baseUrl}`);
  }
}
