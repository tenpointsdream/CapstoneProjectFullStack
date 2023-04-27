import { Component } from '@angular/core';
import { Question } from '../entity/question.entity';
import { QuestionService } from '../service/question.service';
import { User } from '../entity/user.entity';

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
  constructor(private questionService: QuestionService) {
    this.searchedQuestions = [];
  }
  onSubmit() {
    this.questionService.searchQuestion(this.topic, this.title).subscribe((data: Question[]) => {
      console.log(data);
      this.searchedQuestions = data;
    });
    if (this.searchedQuestions != null) {
      this.onVisible = true;
    }
    else {
      this.searchedQuestions = [{
        'id': 0, 'descriptionQuestion': 'None',
        'imageSrc': 'None',
        'status': 'None',
        'topic': 'None',
        'title': 'None',
        'answers': [],
        'qcreated_by': new User(),
        'qapproved_by': new User()
      }]
    }
  }
  refresh() {
    window.location.reload();
  }
}
