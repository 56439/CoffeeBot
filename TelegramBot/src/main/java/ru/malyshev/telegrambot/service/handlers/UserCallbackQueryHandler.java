package ru.malyshev.telegrambot.service.handlers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.malyshev.telegrambot.api.CafeApi;
import ru.malyshev.telegrambot.dto.CafeDto;
import ru.malyshev.telegrambot.dto.DrinkDto;
import ru.malyshev.telegrambot.dto.OrderDto;
import ru.malyshev.telegrambot.dto.UserDto;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserCallbackQueryHandler implements Handler<CallbackQuery> {

    private static final String S = "S";
    private static final String M = "M";
    private static final String L = "L";
    private static final String ORDER_WILL_BE_READY_SOON = "Ваш заказ скоро будет готов";

    private final CafeApi api;

    @Override
    public EditMessageText handle(CallbackQuery query) {
        String queryText = query.getData();

        switch (queryText) {
            case S:
                return editMessage(query, S.charAt(0));
            case M:
                return editMessage(query, M.charAt(0));
            case L:
                return editMessage(query, L.charAt(0));
            default:
                return callBackHandler(query);
        }
    }

    private EditMessageText editMessage(CallbackQuery query, char drinkValue) {
        InlineKeyboardMarkup currentMessageKeyboard = query.getMessage().getReplyMarkup();
        String currentCallback = currentMessageKeyboard.getKeyboard().get(1).get(0).getCallbackData();
        char currentValueInCallback = currentCallback.charAt(currentCallback.length()-1);

        if (currentValueInCallback == drinkValue) return null;

        String newText = query.getMessage().getText().replace(currentValueInCallback, drinkValue);
        InlineKeyboardMarkup newKeyboard = editPayButton(currentMessageKeyboard, drinkValue);

        return EditMessageText.builder()
                .chatId(query.getMessage().getChatId())
                .messageId(query.getMessage().getMessageId())
                .text(newText)
                .replyMarkup(newKeyboard)
                .build();
    }

    private EditMessageText callBackHandler(CallbackQuery query) {
        String queryText = query.getData();

        if (queryText.startsWith("pay")) {
            Long chatId = query.getMessage().getChatId();
            UserDto user;

            if (!isUserExists(chatId)) {
                user = createUser(query);
            } else {
                user = api.getUserByChatId(chatId);
            }

            addOrder(queryText, user);

            String newTextMessage = query.getMessage().getText() + "\n\n" + ORDER_WILL_BE_READY_SOON;

            return EditMessageText.builder()
                    .messageId(query.getMessage().getMessageId())
                    .chatId(query.getMessage().getChatId())
                    .text(newTextMessage)
                    .build();
        } else {
            return null;
        }
    }

    private InlineKeyboardMarkup editPayButton(InlineKeyboardMarkup keyboard, char drinkValue) {
        InlineKeyboardButton payButton = keyboard.getKeyboard().get(1).get(0);
        String newCallback = changeSelectedValueInCallback(payButton.getCallbackData(), drinkValue);

        payButton.setCallbackData(newCallback);
        keyboard.getKeyboard().get(1).set(0, payButton);
        return keyboard;
    }

    private void addOrder(String callbackText, UserDto user) {
        String prepareToSplit = callbackText.replaceAll("pay_", "");
        String[] splited = prepareToSplit.split("_");

        Map<String, String> map = new HashMap<>();
        for (String s : splited) {
            String[] keyValue = s.split("-");
            map.put(keyValue[0], keyValue[1]);
        }

        DrinkDto drink = api.getDrink(Long.valueOf(map.get("drink")));
        String volume = map.get("value");
        CafeDto cafe = api.getCafe(Long.valueOf(map.get("cafe")));
        OrderDto newOrder = new OrderDto(drink, volume, user, cafe);

        api.createOrder(newOrder);
    }

    private String changeSelectedValueInCallback(String text, char newValue) {
        char callbackValue = text.charAt(text.length()-1);

        return text.replace(callbackValue, newValue);
    }

    private UserDto createUser(CallbackQuery query) {
        Long chatId = query.getMessage().getChatId();
        String name = query.getFrom().getFirstName();
        UserDto user = new UserDto(chatId, name);
        return api.createUser(user);
    }

    private boolean isUserExists(Long chatId) {
        return api.getUserByChatId(chatId) != null;
    }
}