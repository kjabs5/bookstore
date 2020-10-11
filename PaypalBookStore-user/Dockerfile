FROM openjdk:8u265-jdk-buster

MAINTAINER "Kishor Jabegu"

WORKDIR /usr/local/bin/

COPY ./target/PaypalBookStore-0.0.1-SNAPSHOT.war webapp.war

EXPOSE 8080

CMD ["java", "-jar","webapp.war"]

