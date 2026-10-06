import { Component, OnInit, inject } from '@angular/core';
import { Router } from '@angular/router';
import { StudentService } from '../../services/student.service';
import { Student } from '../../models/student';
import { StudentSearchComponent, StudentSearchCriteria } from './student-search.component';

@Component({
  selector: 'app-student-list',
  imports: [StudentSearchComponent],
  standalone: true,
  templateUrl: './student-list.component.html'
})
export class StudentListComponent implements OnInit {

  private readonly studentService = inject(StudentService);
  private readonly router = inject(Router); // inject the Angular Router for navigation

  students: Student[] = [];
  filteredStudents: Student[] = [];

  showResults = false;
  errorMessage = '';

  ngOnInit(): void {
    this.loadStudents();
  }

  // Load all students from the service and handle errors
  loadStudents(): void {
    this.errorMessage = '';
    this.studentService.getAllStudents().subscribe({
      // if next is called, it means the students were successfully retrieved
      next: students => {
        this.students = students;
        this.filteredStudents = students;
      },
      // if error is called, it means there was an issue retrieving the students
      error: error => {
        console.error('[StudentList] Unable to load students:', error);
        this.errorMessage = 'Unable to load students.';
      }
    });
  }

  search(criteria: StudentSearchCriteria): void {
    const firstName = criteria.firstName.trim().toLowerCase();
    const lastName = criteria.lastName.trim().toLowerCase();
    const email = criteria.email.trim().toLowerCase();

    this.filteredStudents = this.students.filter(student => {
      const matchesFirstName =
        !firstName ||
        student.firstName.toLowerCase().includes(firstName);

      const matchesLastName =
        !lastName ||
        student.lastName.toLowerCase().includes(lastName);

      const matchesEmail =
        !email ||
        student.email.toLowerCase().includes(email);

      return (
        matchesFirstName &&
        matchesLastName &&
        matchesEmail
      );
    });
    
    this.showResults = true;
  }

  clearSearch(): void {
    this.filteredStudents = [];
    this.showResults = false;
  }

  viewStudent(studentId: number): void {
    this.router.navigate(['/students', studentId]);
  }

  createStudent(): void {
    this.router.navigate(['/students/new']);
  }
}