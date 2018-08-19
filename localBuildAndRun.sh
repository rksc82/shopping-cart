#!/usr/bin/env bash

#Script will build and add jar to the lib folder
./gradlew clean build


#building the image and running the container
docker-compose build --no-cache
docker-compose up
