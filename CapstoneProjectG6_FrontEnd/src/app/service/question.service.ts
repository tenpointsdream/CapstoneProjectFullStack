import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Question } from '../entity/question.entity';
import { CookieService } from 'ngx-cookie-service';
@Injectable({
  providedIn: 'root'
})
export class QuestionService {
  private baseUrl: string = "http://localhost:8080/customer";
  constructor(private httpClient: HttpClient, private cookieService: CookieService) { }


  addQuestion(questionform: Question) {
    const addUrl = `${this.baseUrl}/question/addquestion`;
    return this.httpClient.post(addUrl, questionform, { headers: { Authorization: `Bearer ${this.cookieService.get('jwtToken')}` } });
  }
  searchQuestion(topic: string, title: string): Observable<Question[]> {
    const url = `${this.baseUrl}/question/searchquestions/${topic}/${title}`;
    return this.httpClient.get<Question[]>(url, { headers: { Authorization: `Bearer ${this.cookieService.get('jwtToken')}` } });
  }

  getQuestions():Observable<Question[]> {
    return this.httpClient.get<Question[]>(`${this.baseUrl}`);
  }
}
