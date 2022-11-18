package ru.malyshev.telegrambot.service.handlers;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

import java.io.Serializable;

public interface Handler<T> {

    BotApiMethod<? extends Serializable> handle(T handledType);
}
