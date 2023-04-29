import { HomeComponent } from './home/home.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminLoginComponent } from './admin-login/admin-login.component';
import { AdminRegisterAndLoginComponent } from './admin-register-and-login/admin-register-and-login.component';
import UserLoginComponent from './user-login/user-login.component';
import { UserSignUpComponent } from './user-sign-up/user-sign-up.component';
import { ApprovedAnswerComponent } from './approved-answer/approved-answer.component';
import { CreatedQuestionComponent } from './created-question/created-question.component';
import { PendingQuestionComponent } from './pending-question/pending-question.component';
import { PendingAnswerComponent } from './pending-answer/pending-answer.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'home', component: HomeComponent},
  {path: 'adminlogin', component: AdminLoginComponent},
  {path: 'adminregister', component: AdminRegisterAndLoginComponent},
  {path: 'userlogin', component: UserLoginComponent},
  {path: 'usersignup', component: UserSignUpComponent},
  {path: 'approvedanswer', component: ApprovedAnswerComponent},
  {path: 'createdquestion', component: CreatedQuestionComponent},
  {path: 'pendingquestion', component: PendingQuestionComponent},
  {path: 'pendinganswer', component: PendingAnswerComponent},
  {path: '**', component: PageNotFoundComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
