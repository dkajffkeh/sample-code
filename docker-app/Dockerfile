FROM openjdk:17-slim

WORKDIR /app

COPY ./build/libs/docker-app-0.0.1.jar /app/docker-app.jar
COPY ./src/main/resources/application.yml /app/application.yml
COPY ./src/main/resources/application-local.yml /app/application-local.yml
COPY ./src/main/resources/application-develop.yml /app/application-develop.yml
COPY ./src/main/resources/application-production.yml /app/application-production.yml
COPY ./src/main/resources/logback-spring.xml /app/logback-spring.xml

CMD ["java", "-Xms256m", "-Xmx512m", "-XX:+UseG1GC", "-jar", "docker-app.jar"]
