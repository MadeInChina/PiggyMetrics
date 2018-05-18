FROM openjdk:8-jre-slim
MAINTAINER renwei.han <slamhan1987@hotmail.com>

ADD ./target/gateway.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/gateway.jar"]

EXPOSE 4000