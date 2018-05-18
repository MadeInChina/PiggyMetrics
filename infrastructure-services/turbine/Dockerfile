FROM java:8-jre

ADD ./target/turbine.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/turbine.jar"]

EXPOSE 8989