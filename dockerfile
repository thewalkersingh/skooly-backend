# Build stage using Maven + Java 21
FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Runtime stage using a slim Eclipse Temurin Java 21 JRE
FROM eclipse-temurin:21-jre-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Expose the Spring Boot port
EXPOSE 8080

# Launch the app
ENTRYPOINT ["java", "-jar", "app.jar"]