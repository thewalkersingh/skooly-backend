# Importing JDK and copying required files
FROM maven:3.9.5-openjdk-21 AS build
COPY . .

# Set execution permission for the Maven wrapper
RUN ./mvnw clean package -DskipTests

# Stage 2: Create the final Docker image using OpenJDK 21
FROM openjdk:21-jdk

# Copy the JAR from the build stage
COPY --from=build /target/skooly-backend-0.0.1-SNAPSHOT.jar skooly.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","skooly.jar"]

#################### Refined #####################
#
## Stage 1: Build with Maven + Java 21
#FROM maven:3.9.5-eclipse-temurin-21 AS build
#WORKDIR /app
#
## Copy Maven wrapper & config first
#COPY pom.xml mvnw ./
#COPY .mvn .mvn
#RUN chmod +x mvnw
#RUN ./mvnw dependency:go-offline -B
#
## Copy source code
#COPY src ./src
#RUN ./mvnw clean package -DskipTests -B
#
## Stage 2: Lightweight runtime
#FROM eclipse-temurin:21-jdk-jammy AS runtime
#WORKDIR /app
#
#COPY --from=build /app/target/skooly-backend-0.0.1-SNAPSHOT.jar skooly.jar
#
#EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "skooly.jar"]