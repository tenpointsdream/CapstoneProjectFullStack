import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Answer } from "../entity/answer.entity";
import { CookieService } from 'ngx-cookie-service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AnswerService {
  private baseUrl: string = "http://localhost:8080/answer";
  constructor(private httpClient: HttpClient,
    private cookieService: CookieService) { }

  addAnswer(answer: Answer):Observable<Answer>{
    const url = `${this.baseUrl}/addanswer`;
    const formData:any = new FormData();
    formData.append("desc", answer.description_answer);
    formData.append("file", answer.imageFile);
    return this.httpClient.post<Answer>(url, formData, { headers: { Authorization: `Bearer ${this.cookieService.get('jwtToken')}` } });
  }
  getAnswers(): Observable<Answer[]> {
    return this.httpClient.get<Answer[]>(`${this.baseUrl}/getallanswers`, { headers: { Authorization: `Bearer ${this.cookieService.get('jwtToken')}` } });
  }
  getPendingAnswers(): Observable<Answer[]> {
    const url = `${this.baseUrl}/getpendinganswers`;
    return this.httpClient.get<Answer[]>(url, { headers: { Authorization: `Bearer ${this.cookieService.get('jwtToken')}` } });
  }
  getAnswerById(id: number): Observable<Answer> {
    const url = `${this.baseUrl}/getanswerbyid/${id}`;
    return this.httpClient.get<Answer>(url, { headers: { Authorization: `Bearer ${this.cookieService.get('jwtToken')}` } });
  }
  getAnswerByQuestionId(id:number): Observable<Answer[]> {
    const url =`${this.baseUrl}/getanswersbyquestionid/${id}`;
    return this.httpClient.get<Answer[]>(url, { headers: { Authorization: `Bearer ${this.cookieService.get('jwtToken')}` } });
  }

  updateAnswer(id: number, answer:Answer): Observable<Answer> {
    const url = `${this.baseUrl}/updateanswer/${id}`;
    return this.httpClient.put<Answer>(url, answer, { headers: { Authorization: `Bearer ${this.cookieService.get('jwtToken')}` } });
  }

  deleteAnswer(id: number) {
    const url = `${this.baseUrl}/deleteanswerbyid/${id}`;
    return this.httpClient.delete(url, { headers: { Authorization: `Bearer ${this.cookieService.get('jwtToken')}` } });
  }

}
