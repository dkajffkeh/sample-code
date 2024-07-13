#!/bin/bash

docker run -itd \
 --name jenkins \
  -p 8080:8080 \
  -p 50000:50000 \
  -v /Users/patrickyoo/data/jenkins_home:/var/jenkins_home \
  -v /var/run/docker.sock:/var/run/docker.sock \
  -e TZ=Asia/Seoul \
  -u root jenkins/jenkins:latest

