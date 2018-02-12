#!/usr/bin/env bash

#Script will build and add jar to the lib folder
./gradlew clean build

echo "##### Building uptake-noc-tasks-api image #####"
docker build -t rks/shopping-cart .

docker run  -p 8080:8080 rks/shopping-cart