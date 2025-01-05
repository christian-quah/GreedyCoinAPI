# Use OpenJDK as the base image
FROM openjdk:17-jdk-slim

# Set working directory inside the container
WORKDIR /app

# Copy the project files into the container
COPY target/GreedyCoinAPI-0.0.1-SNAPSHOT.jar ./app.jar
COPY config.yml ./config.yml

# Expose the port your application listens on
EXPOSE 8080

# Define the command to run the application
CMD ["java", "-jar", "app.jar", "server", "config.yml"]

