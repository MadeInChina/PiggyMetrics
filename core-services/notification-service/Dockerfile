FROM openjdk:8-jre-slim
MAINTAINER renwei.han <slamhan1987@hotmail.com>

ADD ./target/notification-service.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/notification-service.jar"]

EXPOSE 8000