package com.andy.studentmanagement.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.andy.studentmanagement.domain.Student;
import com.andy.studentmanagement.domain.StudentRequest;
import com.andy.studentmanagement.exception.StudentExistException;
import com.andy.studentmanagement.exception.StudentNotFoundException;
import com.andy.studentmanagement.persistence.StudentDAO;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @Mock
    private StudentDAO studentDAO;

    @InjectMocks
    private StudentServiceImpl studentService;

    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student();
        student.setId(1L);
        student.setFirstName("Jane");
        student.setLastName("Doe");
        student.setEmail("jane.doe@example.com");
    }

    @Test
    void createStudent_success() {
    	StudentRequest request = new StudentRequest("Jane","DOe","janeDone@gmail.com");
    	studentService.createStudent(request);

        verify(studentDAO).create(request);
    }
    
	@Test
	void createStudent_duplicateEmail_throwsStudentExistException() {
		StudentRequest request = new StudentRequest();
		request.setFirstName("Jane");
		request.setLastName("Doe");
		request.setEmail("jane.doe@example.com");

		doThrow(new StudentExistException()).when(studentDAO).create(any(StudentRequest.class));

		assertThrows(StudentExistException.class, () -> studentService.createStudent(request));
	}

    @Test
    void getAllStudents_shouldReturnStudents() {
        List<Student> students = List.of(student);
        when(studentDAO.findAll()).thenReturn(students);

        assertSame(students, studentService.getAllStudents());

        verify(studentDAO).findAll();
    }

   

    @Test
    void getStudent_shouldThrowWhenNotFound() {
        when(studentDAO.findById(999L)).thenReturn(null);

        assertThrows(StudentNotFoundException.class,
                () -> studentService.getStudent(999L));
    }

    @Test
    void deleteStudent_shouldDeleteWhenFound() {
        when(studentDAO.findById(1L)).thenReturn(student);

        studentService.deleteStudent(1L);

        verify(studentDAO).delete(1L);
    }

    
}
