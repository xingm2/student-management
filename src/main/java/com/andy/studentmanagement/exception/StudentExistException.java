package com.andy.studentmanagement.exception;

public class StudentExistException extends RuntimeException {

    

	/**
	 * 
	 */
	private static final long serialVersionUID = 3236395197315275680L;

	public StudentExistException() {
        super("A student with this email already exists");
    }

    public StudentExistException(String message) {
        super(message);
    }
}

