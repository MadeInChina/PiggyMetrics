FROM java:8-jre
MAINTAINER Alexander Lukyanchikov <sqshq@sqshq.com>

ADD ./target/registry-service.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/registry-service.jar"]

EXPOSE 8761