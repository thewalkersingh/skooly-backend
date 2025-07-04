# Importing JDK and copying required files
FROM maven:3.9.5-eclipse-temurin-21 AS build
COPY . .

# Set execution permission for the Maven wrapper
RUN ./mvnw clean package -DskipTests

# Stage 2: Create the final Docker image using OpenJDK 21
FROM openjdk:21-jdk-slim


# Copy the JAR from the build stage
COPY --from=build /target/skooly-backend-0.0.1-SNAPSHOT.jar skooly.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","skooly.jar"]