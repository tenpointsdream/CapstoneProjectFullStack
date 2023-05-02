import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Question } from '../entity/question.entity';
import { CookieService } from 'ngx-cookie-service';
import { Answer } from '../entity/answer.entity';
@Injectable({
  providedIn: 'root'
})
export class QuestionService {
  private baseUrl: string = "http://localhost:8080/customer/question";
  constructor(private httpClient: HttpClient, private cookieService: CookieService) { }


  addQuestion(question: Question):Observable<Question> {
    const url = `http://localhost:8080/question/addquestion`;
    const formData:any = new FormData();
    formData.append("title", question.title);
    formData.append("topic", question.topic);
    formData.append("desc", question.descriptionQuestion);
    formData.append("file", question.imageFile);
    return this.httpClient.post<Question>(url, formData, { headers: { Authorization: `Bearer ${this.cookieService.get('jwtToken')}` } });
    //return this.httpClient.post(addUrl,{} { headers: { Authorization: `Bearer ${this.cookieService.get('jwtToken')}` } });
  }
  searchQuestion(topic: string, title: string): Observable<Question[]> {
    const url = `${this.baseUrl}/searchquestions/${topic}/${title}`;
    return this.httpClient.get<Question[]>(url, { headers: { Authorization: `Bearer ${this.cookieService.get('jwtToken')}` } });
  }

  getQuestions(): Observable<Question[]> {
    return this.httpClient.get<Question[]>(`${this.baseUrl}`);
  }

  getQuestionById(id: number): Observable<Question> {
    const url = `${this.baseUrl}/getquestionbyid/${id}`;
    return this.httpClient.get<Question>(url, { headers: { Authorization: `Bearer ${this.cookieService.get('jwtToken')}` } });
  }

  getPendingQuestion(): Observable<Question[]> {
    const url = `${this.baseUrl}/getquestionbystatus/false`;
    return this.httpClient.get<Question[]>(url, { headers: { Authorization: `Bearer ${this.cookieService.get('jwtToken')}` } });
  }

  updateQuestion(id: number, question: Question): Observable<Question> {
    const url = `${this.baseUrl}/updatequestion/${id}`;
    return this.httpClient.put<Question>(url, question, { headers: { Authorization: `Bearer ${this.cookieService.get('jwtToken')}` } });
  }

  deleteQuestion(id: number) {
    const url = `${this.baseUrl}/deletequestionbyid/${id}`;
    return this.httpClient.delete(url, { headers: { Authorization: `Bearer ${this.cookieService.get('jwtToken')}` } });
  }

  addAnswerToQuestion(question_id: number, answer: Answer):Observable<any> {
    const url = `http://localhost:8080/question/addanswertoquestion/${question_id}`;
    return this.httpClient.post(url, answer, { headers: { Authorization: `Bearer ${this.cookieService.get('jwtToken')}` } });
  }
  getQuestionByStatus(status: boolean): Observable<Question[]> {
    const url = `http://localhost:8080/question/getquestionbystatus/${status}`;
    return this.httpClient.get<Question[]>(url, { headers: { Authorization: `Bearer ${this.cookieService.get('jwtToken')}` } });
  }
}
