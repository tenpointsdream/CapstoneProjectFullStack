import { Component } from '@angular/core';

@Component({
  selector: 'app-approved-answer',
  templateUrl: './approved-answer.component.html',
  styleUrls: ['./approved-answer.component.css']
})
export class ApprovedAnswerComponent {
approvedAnswers: string[] = [
    'Yes',
    'No',
    'Maybe',
    'I don\'t know',
  ];
}
