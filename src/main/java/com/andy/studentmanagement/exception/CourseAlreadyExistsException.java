package com.andy.studentmanagement.exception;

public class CourseAlreadyExistsException extends RuntimeException {
    public CourseAlreadyExistsException(String name) {
        super("Course already exists: " + name);
    }
}
