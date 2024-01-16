import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TextEntriesListComponent } from './text-entries-list-component.component';

describe('TextEntriesListComponent', () => {
  let component: TextEntriesListComponent;
  let fixture: ComponentFixture<TextEntriesListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TextEntriesListComponent]
    });
    fixture = TestBed.createComponent(TextEntriesListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
