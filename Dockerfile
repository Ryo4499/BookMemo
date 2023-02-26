FROM openjdk:11

WORKDIR /srv
COPY . .
RUN ./gradlew build
EXPOSE 80
ENTRYPOINT ["./gradlew","bootRun"]