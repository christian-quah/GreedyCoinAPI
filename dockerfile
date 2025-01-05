# Use an OpenJDK base image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the Maven project into the container
COPY . /app

# Install Maven in the container
RUN apt-get update && apt-get install -y maven

# Compile the application
RUN mvn clean compile

# Expose the application's port
EXPOSE 8080

# Command to run the application
CMD ["mvn", "exec:java", "-Dexec.mainClass=com.christianquah.greedycoinapi.GreedyCoinAPIApplication"]

