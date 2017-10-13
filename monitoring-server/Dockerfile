FROM java:8-jre
MAINTAINER Alexander Lukyanchikov <sqshq@sqshq.com>

ADD ./target/monitoring-server.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/monitoring-server.jar"]

EXPOSE 8989 8080