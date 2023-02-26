#!/bin/sh
docker build . -t bookmemo
docker run --name bookmemoapp -e POSTGRES_USER=$POSTGRES_USER -e POSTGRES_DB=POSTGRES_DB -e POSTGRES_PASSWORD=POSTGRES_PASSWORD -e DB_URL=DB_URL -td bookmemo