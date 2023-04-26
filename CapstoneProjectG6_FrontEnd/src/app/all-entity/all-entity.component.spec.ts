import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllEntityComponent } from './all-entity.component';

describe('AllEntityComponent', () => {
  let component: AllEntityComponent;
  let fixture: ComponentFixture<AllEntityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllEntityComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AllEntityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
