import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Question } from '../entity/question.entity';
@Injectable({
  providedIn: 'root'
})
export class QuestionService {

  private baseUrl: string = "http://localhost:8080/customer"
  constructor(private httpClient: HttpClient) { }
  searchQuestion(topic: string, title: string): Observable<Question[]> {
    const url = `${this.baseUrl}/question/searchquestions/${topic}/${title}`;
    return this.httpClient.get<Question[]>(url);
  }
}
