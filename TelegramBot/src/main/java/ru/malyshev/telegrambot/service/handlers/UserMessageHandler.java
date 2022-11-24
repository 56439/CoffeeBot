package ru.malyshev.telegrambot.service.handlers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.malyshev.telegrambot.service.messages.CustomMessage;

@Component
@RequiredArgsConstructor
public class UserMessageHandler implements Handler<Message> {

    private static final String START = "/start";
    private static final String CAFE = "Кофейни";
    private static final String SPLITTER_UNDERSCORE = "_";
    private static final String SPLITTER_DASH = "-";

    private final CustomMessage customMessage;

    @Override
    public BotApiMethodMessage handle(Message message) {
        Long chatId = message.getChatId();
        String messageText = message.getText();

        switch (messageText) {
            case START:
                return customMessage.mainMenuMessage(chatId);
            case CAFE:
                return customMessage.cafeListMessage(chatId);
            default:
                if (messageText.startsWith("#")) {
                    String[] messageTextSplited = messageText.split(SPLITTER_UNDERSCORE);
                    String drinkWithId = messageTextSplited[0];
                    String cafeWithAddress = messageTextSplited[1];

                    Long drinkId = Long.valueOf(drinkWithId.split(SPLITTER_DASH)[1]);
                    String cafeAddress = cafeWithAddress.split(SPLITTER_DASH)[1];

                    return customMessage.selectedDrink(chatId, drinkId, cafeAddress);
                } else
                    return customMessage.cafeLocationMessage(chatId, messageText);
        }
    }

}