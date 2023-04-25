import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatedQuestionComponent } from './created-question.component';

describe('CreatedQuestionComponent', () => {
  let component: CreatedQuestionComponent;
  let fixture: ComponentFixture<CreatedQuestionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreatedQuestionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreatedQuestionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
