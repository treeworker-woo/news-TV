FROM openjdk:11-jdk-slim

WORKDIR /app

# Copy Maven files
COPY pom.xml .
COPY src ./src

# Install Maven
RUN apt-get update && apt-get install -y maven

# Build the application
RUN mvn clean package -DskipTests

# Create non-root user for security
RUN groupadd -r texteditor && useradd -r -g texteditor texteditor
USER texteditor

# Expose any necessary ports (though this is a desktop app)
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "target/java-text-editor-1.0.0.jar"]