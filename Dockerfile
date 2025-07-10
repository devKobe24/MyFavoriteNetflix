# Use a base image with Java 17 (or your project's Java version)
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file into the container
# Assuming your build output is in build/libs/myfavoritenetflix-*.jar
COPY build/libs/*.jar app.jar

# Expose the port your Spring Boot application runs on (9007 as per your application.yml)
EXPOSE 9007

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]