package com.andy.studentmanagement.presentation;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.andy.studentmanagement.domain.Student;
import com.andy.studentmanagement.domain.StudentRequest;
import com.andy.studentmanagement.exception.GlobalExceptionHandler;
import com.andy.studentmanagement.exception.StudentExistException;
import com.andy.studentmanagement.service.StudentService;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {
	
	@Mock
	private StudentService studentService;

	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		
		StudentController controller =
                new StudentController(studentService);


		mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
	}

	
	@Test
	void getAllStudents_returnAll() throws Exception {
	    Student student = new Student();
	    student.setId(1L);
	    student.setFirstName("Jane");
	    student.setLastName("doe");
	    student.setEmail("janedoe@gmail.com");
	    when(studentService.getAllStudents())
	            .thenReturn(List.of(student));

	    mockMvc.perform(get("/students")
	                .accept(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$[0].email").value("janedoe@gmail.com"));

	}
	
	

	@Test
    void createStudent_Success() throws Exception {
		String requestJson = """
		        {
		            "firstName": "Alice",
		            "lastName": "Smith",
		            "email" : "abc@gmail.com"
		        }
		        """;

		    mockMvc.perform(post("/students")
		            .contentType(MediaType.APPLICATION_JSON)
		            .content(requestJson))
		        .andExpect(status().isNoContent());
		   
    }
    
    @Test
    void createStudent_duplicateEmail() throws Exception {
    	String requestJson = """
		        {
		            "firstName": "Jane",
		            "lastName": "Doe",
		            "email" : "jane.doe@example.com"
		        }
		        """;

     
    	doThrow(new StudentExistException())
        .when(studentService)
        .createStudent(any(StudentRequest.class));

    mockMvc.perform(post("/students")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestJson))
        .andExpect(status().isConflict());
    }

    
    @Test
    void deleteStudent_success() throws Exception {
        Long studentId = 1L;

        mockMvc.perform(
                org.springframework.test.web.servlet.request.MockMvcRequestBuilders
                    .delete("/students/{id}", studentId))
            .andExpect(status().isNoContent());

        verify(studentService, times(1)).deleteStudent(studentId);
    }
    
    
    @Test
    void updateStudent_success() throws Exception {
        Long studentId = 1L;

        String requestJson = """
            {
                "firstName": "Jane",
                "lastName": "Smith",
                "email": "jane.smith@example.com"
            }
            """;

        mockMvc.perform(
                org.springframework.test.web.servlet.request.MockMvcRequestBuilders
                    .put("/students/{id}", studentId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestJson))
            .andExpect(status().isNoContent());
    }
    
    @Test
    void enrollCourses_success() throws Exception {
        Long studentId = 1L;

        mockMvc.perform(post("/students/{studentId}/courses", studentId)
                .contentType(MediaType.APPLICATION_JSON)
                .content("[101, 102]"))
            .andExpect(status().isNoContent());

       
    }
}
