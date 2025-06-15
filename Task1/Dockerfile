FROM maven:3.9.6-openjdk-21-slim AS builder
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:21-jre-slim

EXPOSE 8080
CMD ["./start.sh"]