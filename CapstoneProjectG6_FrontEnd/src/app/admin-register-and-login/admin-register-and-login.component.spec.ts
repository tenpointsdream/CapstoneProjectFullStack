import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminRegisterAndLoginComponent } from './admin-register-and-login.component';

describe('AdminRegisterAndLoginComponent', () => {
  let component: AdminRegisterAndLoginComponent;
  let fixture: ComponentFixture<AdminRegisterAndLoginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminRegisterAndLoginComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminRegisterAndLoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
