package ru.malyshev.telegrambot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.malyshev.telegrambot.run.TelegramBot;

@SpringBootApplication
public class TelegramBotApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(TelegramBotApplication.class, args);
        TelegramBot.run(context.getBean(Bot.class));
    }

}
