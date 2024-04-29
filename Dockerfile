#FROM openjdk:11
FROM gradle:jdk11

USER gradle
WORKDIR /home/gradle/app
COPY --chown=gradle:gradle . .
RUN ./gradlew build