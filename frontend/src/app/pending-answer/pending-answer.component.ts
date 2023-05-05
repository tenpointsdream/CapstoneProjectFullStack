import { Component, OnInit } from '@angular/core';
import { Answer } from "../entity/answer.entity";
import { QuestionService } from "../service/question.service";
import { AnswerService } from "../service/answer.service";
import { Question } from "../entity/question.entity";
import { CookieService } from 'ngx-cookie-service';
import { UserService } from '../service/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-pending-answer',
  templateUrl: './pending-answer.component.html',
  styleUrls: ['./pending-answer.component.css']
})
export class PendingAnswerComponent implements OnInit {
  pendingAnswers: Answer[];
  answerToUpdate = {} as Answer;
  constructor(
    private answerService: AnswerService,
    private cookieService: CookieService,
    private userService: UserService,
    private questionService: QuestionService,
    private router: Router
  ) {
    this.pendingAnswers = []
  }
  ngOnInit(): void {
    this.answerService.getPendingAnswers().subscribe((data: Answer[]) => {
      console.log(data);
      this.pendingAnswers = data;
    })
  }
  approveAnswer(id: number) {
    if (confirm('Are you sure you want to approve this question?'))
      this.answerService.getAnswerById(id).subscribe((answer: Answer) => {
        let questionId = -1;
        console.log(answer);
        this.answerToUpdate = answer;
        this.answerToUpdate.status = true;
        this.answerToUpdate.approved_by = this.cookieService.get('username');
        console.log(this.answerToUpdate);
        questionId = this.answerToUpdate.question.id;
        console.log("Answer adding to question id: ", questionId);

        console.log(this.cookieService.get('username'));
        console.log("Answer before to approve: ", this.answerToUpdate);
        this.answerService.updateAnswer(id, this.answerToUpdate).subscribe((updatedAnswer: Answer) => {
          console.log("Updated Answer: ", updatedAnswer);
          this.questionService.addAnswerToQuestion(questionId, updatedAnswer).subscribe((updatedQuestion: Question) => {
            console.log(updatedQuestion);
            console.log("This is a list of answers: ", updatedQuestion.answers);
            // this.refresh();
            // this.goToPendingAnswer();
          })
        })
      });
  }
  goToPendingAnswer() {
    this.router.navigate(['/adminhomepage/pendinganswer'])
  }

  removeAnswer(id: number) {
    if (confirm('Are you sure you want to delete this question?'))
      this.answerService.deleteAnswer(id).subscribe(() => {
        console.log("Answer is deleted, ID: ", id);
        this.refresh();
      });
  }
  sign_out() {
    this.cookieService.delete("username");
    this.cookieService.delete('jwtToken');
    this.router.navigate(["/home/adminlogin"]);
  }
  refresh(): void {
    window.location.reload();
  }

}
