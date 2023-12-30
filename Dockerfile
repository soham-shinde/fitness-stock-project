FROM maven:3.8.3-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/fitsta-0.0.1-SNAPSHOT.jar fitsta.jar
COPY --from=build /src/main/resources /app/resources  # Add this line to copy resources
EXPOSE 8080
ENTRYPOINT ["java","-jar","fitsta.jar"]