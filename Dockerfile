FROM openjdk:8-jdk-alpine
MAINTAINER Mayukh Das
VOLUME /tmp
EXPOSE 8080
ADD target/url-shortener-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"] 