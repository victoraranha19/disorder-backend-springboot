FROM maven:latest AS java
WORKDIR /app
COPY pom.xml /app/
COPY src /app/src
RUN mvn clean package

FROM eclipse-temurin:25-jdk
WORKDIR /app
COPY --from=java /app/target/disorder-backend-springboot.jar /app/disorder-backend-springboot.jar
ENTRYPOINT ["java","-jar","/app/disorder-backend-springboot.jar"]

# To build the Docker image, use:
# docker build -t victoraranha19/disorder-backend-springboot:latest .
# docker push victoraranha19/disorder-backend-springboot:latest