FROM maven:latest AS build

WORKDIR /app

COPY pom.xml /app/

COPY src /app/src

RUN mvn clean package

FROM eclipse-temurin:25-jdk

WORKDIR /app

COPY --from=build /app/target/disorder-backend-springboot.jar /app/disorder-backend-springboot.jar

ENTRYPOINT ["java","-jar","/app/disorder-backend-springboot.jar"]