FROM java:8-jre

ADD ./target/turbine-service.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/turbine-service.jar"]

EXPOSE 8989