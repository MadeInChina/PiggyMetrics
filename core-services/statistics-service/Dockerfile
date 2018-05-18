FROM openjdk:8-jre-slim
MAINTAINER renwei.han <slamhan1987@hotmail.com>

ADD ./target/statistics-service.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/statistics-service.jar"]

EXPOSE 7000