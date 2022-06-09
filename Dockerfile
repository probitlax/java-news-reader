FROM openjdk:8-jdk-alpine
MAINTAINER org.example
COPY target/demo-project-0.0.1-SNAPSHOT.jar demo-project-0.0.1-SNAPSHOT.jar
EXPOSE 80
ENTRYPOINT ["java","-jar","/demo-project-0.0.1-SNAPSHOT.jar"]