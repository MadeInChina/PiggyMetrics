FROM openjdk:8-jre-slim
MAINTAINER renwei.han <slamhan1987@hotmail.com>

ADD ./target/registry.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/registry.jar"]

EXPOSE 8761