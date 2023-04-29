import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatedAnswerComponent } from './created-answer.component';

describe('CreatedAnswerComponent', () => {
  let component: CreatedAnswerComponent;
  let fixture: ComponentFixture<CreatedAnswerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreatedAnswerComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreatedAnswerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
