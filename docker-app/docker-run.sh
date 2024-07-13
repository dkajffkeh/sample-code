#!/bin/bash

if [ -z "$1" ]; then
  echo "Usage: $0 컨테이너 이름을 입력 해주세요 ex) docker-app"
  exit 1
fi

MODULE_NAME=$1

# Docker 컨테이너 실행 스크립트
docker stop "$MODULE_NAME"

docker rm "$MODULE_NAME"

docker run -d --name "$MODULE_NAME" \
    -v /Users/patrickyoo/data/logs/docker-app:/log/docker-app \
    -e HELLO_CONFIG="Hello Config!!" \
    -e SPRING_PROFILE="production" \
    -e ACTIVE_MQ_HOST="activeMq" \
    -e TZ=Asia/Seoul \
    --network my-network \
    -p 8877:8080 \
    yhy1045/docker-app:1.0.0
