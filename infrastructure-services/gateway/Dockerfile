FROM registry.homelab.org/openjdk:8-jre-slim-skywalking-8.0.1
MAINTAINER renwei.han <slamhan1987@hotmail.com>

ADD ./target/gateway.jar /app/

CMD ["java", "-javaagent:/app/skywalking-agent.jar", "-Xmx200m", "-jar", "/app/gateway.jar"]
EXPOSE 4000