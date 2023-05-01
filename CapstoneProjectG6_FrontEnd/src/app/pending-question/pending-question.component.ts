import {Component, OnInit} from '@angular/core';
import {Question} from "../entity/question.entity";
import {QuestionService} from "../service/question.service";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-pending-question',
  templateUrl: './pending-question.component.html',
  styleUrls: ['./pending-question.component.css']
})
export class PendingQuestionComponent implements OnInit {
  questions:Question[];

  constructor(private questionService:QuestionService, private http: HttpClient) {
    this.questions = []
  }
  ngOnInit(): void {
    this.questionService.getQuestions().subscribe((data:Question[])=>{
      console.log(data);
      this.questions=data;
    })
  }

  approveQuestion(id: number) {
    if (confirm('Are you sure you want to approve this question?')) {
      this.http.post('http://localhost:4200/adminhomepage/pendingquestion', {id: id, approvedStatus: true}).subscribe(
        (response) => {
          console.log('Status updated successfully:', response);
        }, (error) => {
          console.error('Error updating status:', error);
        }
      );
    }
  }

  removeQuestion(id: number) {
    if (confirm('Are you sure you want to delete this question?')) {
      this.questionService.deleteQuestion(id).subscribe(()=> {
          // remove the deleted question from the questions array
          this.questions = this.questions.filter((q) => q.id !== id);
        },
        (error) => {
          console.error(error);
        }
      );
    }
  }
}

export { QuestionService } ;
