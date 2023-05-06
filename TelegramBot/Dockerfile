FROM openjdk:17
ARG JAR_FILE=target/TelegramBot-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} telegram-bot.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","telegram-bot.jar"]