FROM openjdk:8-jre-slim
MAINTAINER renwei.han <slamhan1987@hotmail.com>

ADD ./target/config.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/config.jar"]

HEALTHCHECK --interval=10s --timeout=3s CMD curl -f http://localhost:8888/health || exit 1

EXPOSE 8888