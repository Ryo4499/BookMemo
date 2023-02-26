#!/bin/bash
# .env
source .env
docker run --name bookmemodb -e POSTGRES_USER=$POSTGRES_USER -e POSTGRES_DB=POSTGRES_DB -e POSTGRES_PASSWORD=POSTGRES_PASSWORD -p 5432:5432 -td postgres:14
docker build . -t bookmemo
docker run --name bookmemoapp -e POSTGRES_USER=$POSTGRES_USER -e POSTGRES_DB=POSTGRES_DB -e POSTGRES_PASSWORD=POSTGRES_PASSWORD -td bookmemo