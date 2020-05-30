FROM openjdk:8-jre
MAINTAINER renwei.han <slamhan1987@hotmail.com>

ADD ./target/admin.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/admin.jar"]

EXPOSE 8080