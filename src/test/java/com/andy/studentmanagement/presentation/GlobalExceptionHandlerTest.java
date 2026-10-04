package com.andy.studentmanagement.presentation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.andy.studentmanagement.domain.CourseNotFoundException;

class GlobalExceptionHandlerTest {

    @Test
    void handleCourseNotFound_shouldReturnNotFound() {
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        CourseNotFoundException exception = new CourseNotFoundException(42L);

        ResponseEntity<Map<String, String>> response =
                handler.handleCourseNotFound(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(Map.of("error", "course not found: 42"), response.getBody());
    }
}
