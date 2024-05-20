FROM maven:3.8.4-jdk-11 AS build

COPY src /app/src

COPY pom.xml /app

WORKDIR /app

RUN mvn clean install

FROM openjdk:8-jre-alpine

COPY --from=build /app/target/event-1.0.jar /app/ev.jar

EXPOSE 8080

CMD ["java", "-jar", "ev.jar"]