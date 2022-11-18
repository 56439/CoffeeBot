package ru.malyshev.telegrambot;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.inlinequery.InlineQuery;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.malyshev.telegrambot.config.BotConfig;
import ru.malyshev.telegrambot.service.handlers.Handler;

import java.io.Serializable;

@Slf4j
@Component
@RequiredArgsConstructor
public class Bot extends TelegramLongPollingBot {

    private final BotConfig botConfig;
    private final Handler<Message> messageHandler;
    private final Handler<InlineQuery> inlineQueryHandler;
    private final Handler<CallbackQuery> callbackQueryHandler;

    @Override
    public void onUpdateReceived(Update update) {
        log.info("-- ЗАПРОС ПОЛУЧЕН --");

        if (update.hasMessage() && update.getMessage().hasText()) {
            sendReply(messageHandler.handle(update.getMessage()));
        }
        if (update.hasInlineQuery()) {
            sendReply(inlineQueryHandler.handle(update.getInlineQuery()));
        }
        if (update.hasCallbackQuery()) {
            sendReply(callbackQueryHandler.handle(update.getCallbackQuery()));
        }
    }

    private void sendReply(BotApiMethod<? extends Serializable> answer) {
        if (answer == null) return;
        try {
            execute(answer);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        log.info("Ответ отправлен");
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getBotToken();
    }
}