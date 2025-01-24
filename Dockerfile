FROM openjdk:17-jdk-alpine
MAINTAINER xdx505.ru

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY .env .

ENTRYPOINT ["java","-jar","app.jar"]