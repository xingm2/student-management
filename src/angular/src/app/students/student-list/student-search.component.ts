import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';

export interface StudentSearchCriteria {
  firstName: string;
  lastName: string;
  email: string;
}

@Component({
  selector: 'app-student-search',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './student-search.component.html'
})
export class StudentSearchComponent {
  @Input() studentCount = 0;
  @Output() readonly searchRequested = new EventEmitter<StudentSearchCriteria>();
  @Output() readonly clearRequested = new EventEmitter<void>();

  firstName = '';
  lastName = '';
  email = '';

  search(): void {
    this.searchRequested.emit({
      firstName: this.firstName,
      lastName: this.lastName,
      email: this.email
    });
  }

  clear(): void {
    this.firstName = '';
    this.lastName = '';
    this.email = '';
    this.clearRequested.emit();
  }
}