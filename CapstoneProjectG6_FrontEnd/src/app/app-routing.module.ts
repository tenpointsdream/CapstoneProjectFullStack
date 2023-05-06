import { AdminHomePageComponent } from './admin-home-page/admin-home-page.component';
import { UserToUserChatComponent } from './user-to-user-chat/user-to-user-chat.component';
import { UserHomePageComponent } from './user-home-page/user-home-page.component';
import { HomeComponent } from './home/home.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminLoginComponent } from './admin-login/admin-login.component';
import { AdminRegisterAndLoginComponent } from './admin-register-and-login/admin-register-and-login.component';
import { UserLoginComponent } from './user-login/user-login.component';
import { UserSignUpComponent } from './user-sign-up/user-sign-up.component';
import { ApprovedAnswerComponent } from './approved-answer/approved-answer.component';
import { CreatedQuestionComponent } from './created-question/created-question.component';
import { PendingQuestionComponent } from './pending-question/pending-question.component';
import { PendingAnswerComponent } from './pending-answer/pending-answer.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { SearchQuestionComponent } from './search-question/search-question.component';
import { CreateNewQuestionComponent } from './create-new-question/create-new-question.component';
import { QuestionDetailsPageComponent } from './question-details-page/question-details-page.component';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'home/adminlogin', component: AdminLoginComponent },
  { path: 'home/adminregister', component: AdminRegisterAndLoginComponent },
  { path: 'home/userlogin', component: UserLoginComponent },
  { path: 'home/usersignup', component: UserSignUpComponent },
  // user
  { path: 'userhomepage', component: UserHomePageComponent },
  { path: 'approvedanswer', component: ApprovedAnswerComponent },
  { path: 'createdquestion', component: CreatedQuestionComponent },
  { path: 'userhomepage/searchquestion', component: SearchQuestionComponent },
  { path: 'userhomepage/createquestion', component: CreateNewQuestionComponent },
  { path: 'userhomepage/createdquestion', component: CreatedQuestionComponent },
  { path: 'userhomepage/approvedquestion', component: ApprovedAnswerComponent },
  { path: 'userhomepage/searchquestion/details', component: QuestionDetailsPageComponent },
  // admin
  { path: 'adminhomepage', component: AdminHomePageComponent },
  { path: 'adminhomepage/pendingquestion', component: PendingQuestionComponent },
  { path: 'adminhomepage/pendinganswer', component: PendingAnswerComponent },
  // chat
  { path: 'userhomepage/usertouserchat', component: UserToUserChatComponent },

  { path: 'adminhomepage/usertouserchat', component: UserToUserChatComponent },
  { path: '**', component: PageNotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
