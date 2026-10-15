package com.andy.studentmanagement.domain;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.lang.Nullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * StudentRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-10-05T19:59:03.835616900-05:00[America/Chicago]", comments = "Generator version: 7.15.0")
public class StudentRequest {

  private @Nullable Long id;

  private String firstName;

  private String lastName;

  private String email;

  @Valid
  private List<Long> courseIds = new ArrayList<>();

  public StudentRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public StudentRequest(String firstName, String lastName, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  public StudentRequest id(@Nullable Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */
  
  @Schema(name = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public @Nullable Long getId() {
    return id;
  }

  public void setId(@Nullable Long id) {
    this.id = id;
  }

  public StudentRequest firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * Get firstName
   * @return firstName
   */
  @NotNull @Size(min = 1) 
  @Schema(name = "firstName", example = "John", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("firstName")
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public StudentRequest lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  /**
   * Get lastName
   * @return lastName
   */
  @NotNull @Size(min = 1) 
  @Schema(name = "lastName", example = "Smith", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("lastName")
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public StudentRequest email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
   */
  @NotNull @jakarta.validation.constraints.Email 
  @Schema(name = "email", example = "john.smith@example.com", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("email")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public StudentRequest courseIds(List<Long> courseIds) {
    this.courseIds = courseIds;
    return this;
  }

  public StudentRequest addCourseIdsItem(Long courseIdsItem) {
    if (this.courseIds == null) {
      this.courseIds = new ArrayList<>();
    }
    this.courseIds.add(courseIdsItem);
    return this;
  }

  /**
   * Get courseIds
   * @return courseIds
   */
  
  @Schema(name = "courseIds", example = "[1,2]", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("courseIds")
  public List<Long> getCourseIds() {
    return courseIds;
  }

  public void setCourseIds(List<Long> courseIds) {
    this.courseIds = courseIds;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StudentRequest studentRequest = (StudentRequest) o;
    return Objects.equals(this.id, studentRequest.id) &&
        Objects.equals(this.firstName, studentRequest.firstName) &&
        Objects.equals(this.lastName, studentRequest.lastName) &&
        Objects.equals(this.email, studentRequest.email) &&
        Objects.equals(this.courseIds, studentRequest.courseIds);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, email, courseIds);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StudentRequest {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    courseIds: ").append(toIndentedString(courseIds)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

