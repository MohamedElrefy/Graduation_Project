# Use OpenJDK 21 instead of 17
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the entire project first
COPY . .

# Grant execute permissions to the Maven wrapper
RUN chmod +x mvnw

# Build the application using Maven wrapper
RUN ./mvnw clean package -DskipTests

# Expose the application port (change if needed)
EXPOSE 8080

# Run the application
CMD ["sh", "-c", "java -jar target/*.jar"]
