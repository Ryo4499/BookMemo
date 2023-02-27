FROM openjdk:11

WORKDIR /srv
COPY . .
RUN ./gradlew build
ENTRYPOINT ["./gradlew","bootRun"]