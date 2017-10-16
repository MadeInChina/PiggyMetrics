FROM java:8-jre
MAINTAINER slamhan <slamhan1987@hotmail.com>

ADD ./target/tracing.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/tracing.jar"]

EXPOSE 9000