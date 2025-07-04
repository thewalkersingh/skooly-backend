## Setting 1
#FROM maven:3.8.5-openjdk-17 AS build
#COPY . .
#RUN mvn clean package -DskipTests
#
## Stage 2: Create the final Docker image using OpenJDK 21
#FROM openjdk:17.0.1-jdk-slim
#
## Copy the JAR from the build stage
#COPY --from=build /target/skooly-backend-0.0.1-SNAPSHOT.jar skooly.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","skooly.jar"]

## Setting 2

#FROM maven:3.8.5-openjdk-17 AS build
#WORKDIR /app
#
#COPY pom.xml mvnw ./
#COPY .mvn .mvn
#RUN chmod +x mvnw
#RUN ./mvnw dependency:go-offline -B
#
#COPY src ./src
#RUN ./mvnw clean package -DskipTests -B
#
#FROM openjdk:17.0.1-jdk-slim AS runtime
#WORKDIR /app
#
#COPY --from=build /app/target/skooly-backend-0.0.1-SNAPSHOT.jar skooly.jar
#
#EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "skooly.jar"]

## Setting 3
FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]