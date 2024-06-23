FROM openjdk:17-slim

WORKDIR /app

COPY ./build/libs/docker-app-0.0.1.jar /app/docker-app.jar

CMD ["java", "-Xms256m", "-Xmx512m", "-XX:+UseG1GC", "-jar", "docker-app.jar"]
