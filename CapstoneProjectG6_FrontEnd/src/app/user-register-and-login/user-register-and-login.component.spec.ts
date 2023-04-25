import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserRegisterAndLoginComponent } from './user-register-and-login.component';

describe('UserRegisterAndLoginComponent', () => {
  let component: UserRegisterAndLoginComponent;
  let fixture: ComponentFixture<UserRegisterAndLoginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserRegisterAndLoginComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserRegisterAndLoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
