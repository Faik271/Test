# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine


ARG DB_URL
ARG DB_USERNAME
ARG DB_PASSWORD

ENV DB_URL=${DB_URL}
ENV DB_USERNAME=${DB_USERNAME}
ENV DB_PASSWORD=${DB_PASSWORD}


# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file into the container (renaming it to Test.jar)
COPY target/Test-0.0.1-SNAPSHOT.jar /app/Test.jar

# Expose the default port for Spring Boot
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "Test.jar"]
