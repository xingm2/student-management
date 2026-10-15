package com.andy.studentmanagement.exception;

public class StudentNotFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4928773812607340395L;

	public StudentNotFoundException(Long studentId) {
        super("Student not found: " + studentId);
    }
}
