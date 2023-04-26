import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class QuestionService {

  private baseUrl:string = "http://localhost:8080/customer/questions/searchquestions"
  constructor(private httpClient: HttpClient) { }
  searchQuestion(topic:string, title:string){
    const url = `${this.baseUrl}/${topic}/${title}`;
  }
}
