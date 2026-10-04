# student-management

A "Student Management" app where users can manage students and their enrolled courses.

Each student can be enrolled in multiple courses.

The app allows users to create, read, update, and delete both students and courses.

## To set up Spring and H2

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

## To set up Angular

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
