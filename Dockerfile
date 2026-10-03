# Build WAR
# This stage compiles the application and packages it into a WAR file using Gradle.

# Starts a build stage using an image with JDK 21. AS build names this stage build, so a later stage can copy files from it.
#FROM eclipse-temurin:21-jdk AS build 
FROM gradle:8.14-jdk21 AS build
# Sets the working directory inside the container to /app. All subsequent commands will be run from this directory.
WORKDIR /app

# Copies the entire project into the working directory inside the container.
COPY . .

# Makes the Gradle wrapper executable.
#RUN chmod +x gradlew
# Cleans any previous builds and packages the application into a WAR file without using the Gradle daemon.
#RUN ./gradlew clean war --no-daemon
RUN gradle clean war --no-daemon

# Run WAR in Tomcat
# This stage sets up a Tomcat server and deploys the WAR file generated in the build stage.
FROM tomcat:11-jdk21-temurin

# Removes the default web applications from Tomcat to ensure only the new WAR is deployed.
RUN rm -rf /usr/local/tomcat/webapps/*

# Copies the WAR file from the build stage into the Tomcat webapps directory as ROOT.war.
COPY --from=build /app/build/libs/*.war \
    /usr/local/tomcat/webapps/ROOT.war

# Exposes port 8080 to allow access to the Tomcat server from outside the container.
EXPOSE 8080

# Starts the Tomcat server.
CMD ["catalina.sh", "run"]