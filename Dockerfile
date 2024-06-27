FROM gradle:7.6.0-jdk17 AS build

WORKDIR /app

COPY build.gradle settings.gradle gradlew /app/
COPY gradle /app/gradle
COPY src /app/src
RUN ./gradlew build -x test --no-daemon

FROM openjdk:17-jdk-slim
WORKDIR /app

COPY --from=build /app/build/libs/*.jar order-system-hawa.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "order-system-hawa.jar"]