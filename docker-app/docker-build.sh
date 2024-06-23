#!/bin/bash

if [ -z "$1" ]; then
  echo "Usage: $0 Tag 정보를 입력해주세요"
  exit 1
fi

docker build -t yhy1045/docker-app:"$1" .

docker push yhy1045/docker-app:"$1"
