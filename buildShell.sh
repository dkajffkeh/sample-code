#!/bin/bash

# Check if a parameter is provided
if [ -z "$1" ]; then
  echo "Usage: $0 모듈명을 입력 해주세요 ex) docker-app"
  exit 1
fi

MODULE_NAME=$1

# Execute the Gradle command
echo "Building module: $MODULE_NAME"
gradle clean -x test :"$MODULE_NAME":build

# Check if the Gradle command was successful
if [ $? -ne 0 ]; then
  echo "Gradle build failed for module: $MODULE_NAME"
  exit 1
fi

echo "Gradle build succeeded for module: $MODULE_NAME"
