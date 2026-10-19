# student-management

A "Student Management" app where users can manage students and their enrolled courses.

Each student can be enrolled in multiple courses.

The app allows users to create, read, update, and delete both students and courses.

## 1. To set up Spring and H2

### Prerequisites

- Install JDK 21.
- Install Apache Tomcat 11.

### Build and run the backend

1. From the project root, build the WAR with the Gradle wrapper:

   ```sh
   # Windows
   .\gradlew clean build
   ```

   Gradle generates the OpenAPI interfaces and packages the backend as a WAR under `build/libs/`.
2. Deploy the generated WAR to Tomcat. To serve the API at the root context (for example, `http://localhost:8080/students`), copy the WAR into Tomcat's `webapps` directory as `ROOT.war`. Alternatively, keep its generated filename and use the corresponding application context path in the URL.
3. Start Tomcat and wait for it to finish deploying the application. The port is configured by your Tomcat installation; the default is usually `8080`.

The application uses an in-memory H2 database configured in `src/main/resources/application.properties`. The database is initialized from `schema.sql` and `data.sql` on startup, so no separate H2 server or database setup is needed. Because it is in memory, its contents are reset when the application stops.

## 2. To set up Angular

1. Install Node.js and npm.
2. Open a terminal in the project directory:

   ```sh
   cd src/angular
   ```
3. Install the dependencies:

   ```sh
   npm install
   ```
4. Start the Angular development server:

   ```sh
   npm start
   ```
5. Open [http://localhost:4200](http://localhost:4200) in your browser.

## 3. Project Overview

```text
.
├── build.gradle                  # Gradle build and WAR packaging configuration
├── gradlew / gradlew.bat         # Gradle wrapper scripts
├── src/
│   ├── main/
│   │   ├── java/com/andy/studentmanagement/
│   │   │   ├── domain/           # Student and course domain models
│   │   │   ├── persistence/      # Data access objects
│   │   │   ├── service/          # Business logic
│   │   │   ├── presentation/     # REST controllers and exception handling
│   │   │   ├── security/         # Request authorization
│   │   │   └── exception/        # Application-specific exceptions
│   │   ├── resources/            # Spring, database, and OpenAPI configuration
│   │   └── webapp/WEB-INF/       # Servlet web application descriptor
│   ├── test/java/                # Backend unit and controller tests
│   └── angular/
│       └── src/app/
│           ├── students/         # Student pages and course registration
│           ├── courses/          # Course pages
│           ├── services/         # API clients
│           └── models/           # Frontend data models
└── Dockerfile                    # Container image configuration
```


## 4. REST API

The OpenAPI definition is in `src/main/resources/student-management-api.yaml`. Its configured base URL is `http://localhost:8080/api`; the actual context path depends on how the WAR is deployed. Requests and responses use JSON.

| Method | Endpoint | Description |
| --- | --- | --- |
| `GET` | `/students` | List all students, including their enrolled courses. |
| `POST` | `/students` | Create a student. |
| `GET` | `/students/{studentId}` | Get a student by ID. |
| `PUT` | `/students/{studentId}` | Update a student. |
| `DELETE` | `/students/{studentId}` | Delete a student. |
| `POST` | `/students/{studentId}/courses` | Enroll the student in one or more courses. |
| `DELETE` | `/students/{studentId}/courses` | Remove one or more course enrollments. |
| `GET` | `/courses` | List all courses. |
| `POST` | `/courses` | Create a course. |
| `GET` | `/courses/{courseId}` | Get a course by ID. |
| `PUT` | `/courses/{courseId}` | Update a course. |
| `DELETE` | `/courses/{courseId}` | Delete a course. |

For student creation or updates, send `firstName`, `lastName`, and `email` in the JSON request body. For course creation or updates, send `name` and `description`. Course enrollment endpoints accept a JSON array of course IDs, for example `[1, 2]`.

## 5. Functionalities

- Manage students: create, view, update, and delete student records.
- Manage courses: create, view, update, and delete courses.
- Enroll students in multiple courses and remove course enrollments.
- View each student's enrolled courses alongside their student details.
- Validate API requests and return structured error responses for invalid requests or missing records.
- Use an in-memory H2 database initialized with the project schema and sample data at startup.
