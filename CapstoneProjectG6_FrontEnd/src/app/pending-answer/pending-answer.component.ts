import { Component, OnInit } from '@angular/core';
import { Answer } from "../entity/answer.entity";
import { QuestionService } from "../service/question.service";
import { AnswerService } from "../service/answer.service";
import { Question } from "../entity/question.entity";
import { UserProfile } from '../entity/userprofile.entity';
import { CookieService } from 'ngx-cookie-service';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-pending-answer',
  templateUrl: './pending-answer.component.html',
  styleUrls: ['./pending-answer.component.css']
})
export class PendingAnswerComponent implements OnInit {
  pendingAnswers: Answer[];
  answerToUpdate = {} as Answer;
  currentUser = {} as UserProfile;
  currentQuestion = {} as Question;
  constructor(
    private answerService: AnswerService,
    private cookieService: CookieService,
    private userService: UserService,
    private questionService: QuestionService) {
    this.pendingAnswers = []
  }
  ngOnInit(): void {
    this.answerService.getPendingAnswers().subscribe((data: Answer[]) => {
      console.log(data);
      this.pendingAnswers = data;
    })
  }
  approveAnswer(id: number) {

    if (confirm('Are you sure you want to approve this question?')) {
      let questionId = -1;
      this.answerService.getAnswerById(id).subscribe((answer: Answer) => {
        console.log(answer);
        this.answerToUpdate = answer;
        this.answerToUpdate.status = true;
        console.log(this.answerToUpdate);
        questionId = this.answerToUpdate.question.id;
        console.log("Answer adding to question id: ", questionId);
        this.userService.getUser(this.cookieService.get('username')).subscribe((user: UserProfile) => {
          console.log(user);
          this.answerToUpdate.approved_by = user;  
          console.log("Question before to approve: ", this.answerToUpdate);
          this.answerService.updateAnswer(id, this.answerToUpdate).subscribe((updatedAnswer: Answer) => {
            console.log(updatedAnswer);
            this.questionService.addAnswerToQuestion(questionId, updatedAnswer).subscribe((updatedQuestion: Question) => {
              console.log(updatedQuestion);
              console.log("This is a list of answers: ", updatedQuestion.answers);
              this.refresh();
            })
          })
        });
      });
    }
  }

  removeAnswer(id: number) {
    if (confirm('Are you sure you want to delete this question?')) {
      this.answerService.deleteAnswer(id).subscribe(() => {
        console.log("Answer is deleted, ID: ", id);
      },
      );
    }
  }

  refresh(): void {
    window.location.reload();
  }

}
