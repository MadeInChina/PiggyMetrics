FROM openjdk:8-jre-slim
MAINTAINER renwei.han <slamhan1987@hotmail.com>


ADD ./target/account-service.jar /app/

ENV JAVA_HOME=/usr/jdk1.8.0_131
ENV PATH=$PATH:$JAVA_HOME/bin

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Xmx200m -jar /app/account-service.jar" ]
#CMD ["java $JAVA_OPTS", "-Xmx200m", "-jar", "/app/account-service.jar"]
EXPOSE 6000