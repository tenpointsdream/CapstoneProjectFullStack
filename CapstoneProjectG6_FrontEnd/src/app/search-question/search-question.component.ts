import { Component } from '@angular/core';
import { Question } from '../entity/question.entity';
import { QuestionService } from '../service/question.service';

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
    else
      this.onVisible = false;
  }
  refresh() {
    window.location.reload();
  }
}
