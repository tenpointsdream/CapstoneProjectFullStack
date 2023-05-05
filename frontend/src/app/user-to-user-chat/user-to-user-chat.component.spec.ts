import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserToUserChatComponent } from './user-to-user-chat.component';

describe('UserToUserChatComponent', () => {
  let component: UserToUserChatComponent;
  let fixture: ComponentFixture<UserToUserChatComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserToUserChatComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserToUserChatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
