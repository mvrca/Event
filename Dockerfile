FROM maven:3-openjdk-17 AS build

COPY src /app/src

COPY pom.xml /app

WORKDIR /app

RUN mvn clean install

FROM openjdk:17-oracle

COPY --from=build /app/target/event-1.0.jar /app/ev.jar

WORKDIR /app

EXPOSE 8080

CMD ["java", "-jar", "ev.jar"]