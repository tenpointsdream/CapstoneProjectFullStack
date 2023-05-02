import { Component } from '@angular/core';
import { Question } from '../entity/question.entity';
import { QuestionService } from '../service/question.service';
import { User } from '../entity/user.entity';
import { Answer } from '../entity/answer.entity';
import { CookieService } from 'ngx-cookie-service';


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
    private cookieService: CookieService) {
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
        'status': false,
        'topic': 'None',
        'title': 'None',
        'datetime': 'None',
        'answers': [],
        'qcreated_by': '',
        'qapproved_by': ''
      }]
    }
  }
  refresh() {
    window.location.reload();
  }
}
