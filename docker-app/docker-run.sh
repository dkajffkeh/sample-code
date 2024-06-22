#!/bin/bash

# Docker 컨테이너 실행 스크립트
docker stop docker-app

docker rm docker-app

docker run -d --name docker-app \
    -v /Users/patrickyoo/data/logs/docker-app:/log/docker-app \
    -p 8877:8080 \
    docker-app:1.0.0
