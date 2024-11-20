FROM maven:3.9.5-eclipse-temurin-23 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:23-jdk-jammy

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

COPY .env .env

ENTRYPOINT ["java", "-jar", "app.jar"]