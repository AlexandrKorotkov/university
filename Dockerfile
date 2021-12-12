FROM maven:3.6.3-openjdk-17 AS build
COPY src /app/src
COPY pom.xml /app
WORKDIR /app/
RUN mvn clean package

FROM openjdk:17-jdk-alpine
COPY --from=build app/target/university-0.0.1-SNAPSHOT.jar lib/university-0.0.1-SNAPSHOT.jar
ENV PORT 8080
EXPOSE $PORT
CMD ["java", "-jar", "lib/university-0.0.1-SNAPSHOT.jar"]

