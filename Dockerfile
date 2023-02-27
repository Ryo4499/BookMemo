FROM openjdk:11

WORKDIR /srv
COPY . .
RUN ./gradlew build
EXPOSE 8080
ENTRYPOINT ["./gradlew","bootRun"]