package com.andy.studentmanagement.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;


/**
 * Student
 */
public class Student {

  @NotNull
  private Long id;

  @NotNull
  private String firstName;

  @NotNull
  private String lastName;

  @Email
  private String email;

  @NotNull
  private List<@Valid Course> courses = new ArrayList<>();



  /**
   * Get id
   * @return id
   */
  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }



  /**
   * Get firstName
   * @return firstName
   */
  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }


  /**
   * Get lastName
   * @return lastName
   */
  public String getLastName() {
    return this.lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }


  /**
   * Get email
   * @return email
   */
  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  /**
   * list of courses which the student is enrolled.
   * @return courses
   */
  public List<Course> getCourses() {
    return this.courses;
  }

  public void setCourses(List<Course> courses) {
    this.courses = courses;
  }


  // toString method for Student class
  @Override
  public String toString() {
    return "Student{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", courses=" + courses +
            '}';
  }

}

