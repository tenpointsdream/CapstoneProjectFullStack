import { Component } from '@angular/core';
import { Question } from '../entity/question.entity';
import { QuestionService } from '../service/question.service';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-search-question',
  templateUrl: './search-question.component.html',
  styleUrls: ['./search-question.component.css']
})
export class SearchQuestionComponent {

  onVisible: boolean = false;
  topic!: string;
  title!: string;
  searchedQuestions: Question[];
  constructor(
    private questionService: QuestionService,
    private cookieService: CookieService,
    private router: Router) {
    this.searchedQuestions = [];
  }
  onSubmit() {
    //alert(this.cookieService.get('jwtToken'));
    this.questionService.searchQuestion(this.topic, this.title).subscribe((data: Question[]) => {
      console.log(data);
      this.searchedQuestions = data;
    });
    if (this.searchedQuestions != null) {
      this.onVisible = true;
    }
    else {
      this.searchedQuestions = [{
        'id': 0,
        'descriptionQuestion': 'None',
        'imageSrc': 'None',
        'imageFile' : null,
        'status': false,
        'topic': 'None',
        'title': 'None',
        'datetime': 'None',
        'answers': [],
        'qcreated_by': 'None',
        'qapproved_by': 'None'
      }]
    }
  }

  showDetails(id: number, username: string) {
    this.router.navigate(['userhomepage/searchquestion/details']);
    localStorage.setItem('questionId', id.toString());
    localStorage.setItem('username', username);
  }
  refresh() {
    window.location.reload();
  }

  sign_out(){
    this.cookieService.deleteAll();
  }
}
