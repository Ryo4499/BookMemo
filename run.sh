#!/bin/bash
# export enviroment variables
export $(grep -v '#' <.env | xargs)
# run db
docker run --name bookmemodb -e POSTGRES_USER="$POSTGRES_USER" -e POSTGRES_DB="$POSTGRES_DB" -e POSTGRES_PASSWORD="$POSTGRES_PASSWORD" -p 5432:5432 -td postgres:14-alpine

if [ "${1,,}" = 'docker' ]; then
    # docker run
    docker build . -t bookmemo
    docker run --name bookmemoapp -e POSTGRES_USER="$POSTGRES_USER" -e POSTGRES_DB="$POSTGRES_DB" -e POSTGRES_PASSWORD="$POSTGRES_PASSWORD" -e DB_URL="$DB_URL" -td bookmemo
else
    # without docker
    ./gradlew build
    ./gradlew bootJar --continuous &
    ./gradlew bootRun 
    pkill java
fi
