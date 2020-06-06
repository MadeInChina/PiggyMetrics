FROM openjdk:8-jre-slim
MAINTAINER renwei.han <slamhan1987@hotmail.com>

ADD ./target/turbine.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/turbine.jar"]

EXPOSE 8080