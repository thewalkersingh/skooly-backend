# Stage 1: Build your app with Maven on Java 21
FROM maven:3.9.5-eclipse-temurin-21 AS build
WORKDIR /app

# Cache dependencies: copy wrapper, pom, then download
COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline -B

# Now copy source and build
COPY src ./src
RUN ./mvnw clean package -DskipTests -B

# Stage 2: Lightweight runtime with Java 21 JDK
FROM eclipse-temurin:21-jdk-jammy AS runtime
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]