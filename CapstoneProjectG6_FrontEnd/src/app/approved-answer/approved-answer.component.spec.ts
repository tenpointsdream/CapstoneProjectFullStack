import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApprovedAnswerComponent } from './approved-answer.component';

describe('ApprovedAnswerComponent', () => {
  let component: ApprovedAnswerComponent;
  let fixture: ComponentFixture<ApprovedAnswerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ApprovedAnswerComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ApprovedAnswerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
