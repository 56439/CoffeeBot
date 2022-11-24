FROM openjdk:17
ARG JAR_FILE=target/CafeApi-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} cafe-api.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","cafe-api.jar"]