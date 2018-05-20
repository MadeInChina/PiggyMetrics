FROM openjdk:8-jre
MAINTAINER renwei.han <slamhan1987@hotmail.com>

ADD ./target/registry.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/registry.jar"]

HEALTHCHECK --interval=10s --timeout=60s CMD curl -f  http://localhost:8761/actuator/health || exit 1

EXPOSE 8761