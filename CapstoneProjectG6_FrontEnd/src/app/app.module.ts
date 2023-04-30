import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AdminLoginComponent } from './admin-login/admin-login.component';
import { AdminSignUpComponent } from './admin-sign-up/admin-sign-up.component';
import { ApprovedAnswerComponent } from './approved-answer/approved-answer.component';
import { ChatComponent } from './chat/chat.component';
import { CreateNewQuestionComponent } from './create-new-question/create-new-question.component';
import { CreatedAnswerComponent } from './created-answer/created-answer.component';
import { CreatedQuestionComponent } from './created-question/created-question.component';
import { AllEntityComponent } from './all-entity/all-entity.component';
import { HomeComponent } from './home/home.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { PendingAnswerComponent } from './pending-answer/pending-answer.component';
import { PendingQuestionComponent } from './pending-question/pending-question.component';
import { SearchQuestionComponent } from './search-question/search-question.component';
import { UserLoginComponent } from './user-login/user-login.component';
import { UserSignUpComponent } from './user-sign-up/user-sign-up.component';
import { UserToUserChatComponent } from './user-to-user-chat/user-to-user-chat.component';
import { UserRegisterAndLoginComponent } from './user-register-and-login/user-register-and-login.component';
import { AdminRegisterAndLoginComponent } from './admin-register-and-login/admin-register-and-login.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
@NgModule({
  declarations: [
    AppComponent,
    AdminLoginComponent,
    AdminSignUpComponent,
    ApprovedAnswerComponent,
    ChatComponent,
    CreateNewQuestionComponent,
    CreatedAnswerComponent,
    CreatedQuestionComponent,
    AllEntityComponent,
    HomeComponent,
    PageNotFoundComponent,
    PendingAnswerComponent,
    PendingQuestionComponent,
    SearchQuestionComponent,
    UserLoginComponent,
    UserSignUpComponent,
    UserToUserChatComponent,
    UserRegisterAndLoginComponent,
    AdminRegisterAndLoginComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    RouterModule,
  ],
  providers: [CookieService],
  bootstrap: [AppComponent]
})
export class AppModule { }
