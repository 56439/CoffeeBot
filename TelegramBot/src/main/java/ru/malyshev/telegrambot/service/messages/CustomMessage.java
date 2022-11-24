package ru.malyshev.telegrambot.service.messages;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendLocation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.inlinequery.InlineQuery;
import org.telegram.telegrambots.meta.api.objects.inlinequery.inputmessagecontent.InputTextMessageContent;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResultArticle;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import ru.malyshev.telegrambot.api.CafeApi;
import ru.malyshev.telegrambot.dto.CafeDto;
import ru.malyshev.telegrambot.dto.DrinkDto;
import ru.malyshev.telegrambot.dto.ValuesDto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomMessage {

    private static final String MAIN_MENU = "Главное меню";
    private static final String CAFE_LIST = "Список кофеен";
    private final static String SEPARATOR = ", ";
    private static final String CAFE = "Кофейни";
    private static final String MENU = "Меню";

    private final CafeApi api;

    public SendMessage mainMenuMessage(Long chatId) {
        ReplyKeyboardMarkup keyboard = mainMenuKeyboard();

        SendMessage message = SendMessage.builder()
                .chatId(chatId)
                .text(MAIN_MENU)
                .build();

        message.setReplyMarkup(keyboard);
        return message;
    }

    public SendMessage cafeListMessage(Long chatId) {
        ReplyKeyboardMarkup keyboard = cafeListKeyboard();

        SendMessage message = SendMessage.builder()
                .chatId(chatId)
                .text(CAFE_LIST)
                .build();

        message.setReplyMarkup(keyboard);
        return message;
    }

    public SendLocation cafeLocationMessage(Long chatId, String address) {
        CafeDto cafe = api.getCafeByAddress(address);

        Double[] coordinates = getLatitudeAndLongitude(cafe.getCoordinates());

        SendLocation location = SendLocation.builder()
                .chatId(chatId)
                .latitude(coordinates[0])
                .longitude(coordinates[1])
                .build();

        InlineKeyboardMarkup keyboard = cafeMenuInlineKeyboard(cafe.getAddress());

        location.setReplyMarkup(keyboard);
        return location;
    }

    public AnswerInlineQuery menuList(InlineQuery query) {
        List<DrinkDto> drinks = api.getDrinks();
        List<InlineQueryResultArticle> results = new ArrayList<>();

        for (DrinkDto drink : drinks) {
            results.add(menuElement(drink, query.getQuery()));
        }

        return AnswerInlineQuery.builder()
                .inlineQueryId(query.getId())
                .results(results)
                .build();
    }

    public SendMessage selectedDrink(Long chatId, Long drinkId, String cafeAddress) {
        DrinkDto drink = api.getDrink(drinkId);
        CafeDto cafe = api.getCafeByAddress(cafeAddress);

        InlineKeyboardMarkup keyboard = selectedDrinkKeyboard(drink, cafe.getId());
        String messageText = cafe.getAddress() + "\n\n" + drink.getName() + " - S";

        SendMessage message = SendMessage.builder()
                .chatId(chatId)
                .text(messageText)
                .build();

        message.setReplyMarkup(keyboard);
        return message;
    }


    private ReplyKeyboardMarkup mainMenuKeyboard() {
        KeyboardButton button = KeyboardButton.builder()
                .text(CAFE)
                .build();

        KeyboardRow row = new KeyboardRow(List.of(button));

        return ReplyKeyboardMarkup.builder()
                .keyboard(List.of(row))
                .resizeKeyboard(true)
                .oneTimeKeyboard(true)
                .inputFieldPlaceholder(MAIN_MENU)
                .build();
    }

    private ReplyKeyboardMarkup cafeListKeyboard() {
        List<CafeDto> cafeList = api.getCafeList();
        List<KeyboardRow> rowList = new ArrayList<>();

        for (CafeDto cafe : cafeList) {
            KeyboardButton button = KeyboardButton.builder()
                    .text(cafe.getAddress())
                    .build();

            KeyboardRow row = new KeyboardRow(List.of(button));

            rowList.add(row);
        }

        return ReplyKeyboardMarkup.builder()
                .keyboard(rowList)
                .resizeKeyboard(true)
                .oneTimeKeyboard(true)
                .inputFieldPlaceholder(CAFE_LIST)
                .build();
    }

    private Double[] getLatitudeAndLongitude(String coordinates) {
        String[] coordinatesSplited = coordinates.split(SEPARATOR);
        double latitude = Double.parseDouble(coordinatesSplited[0]);
        double longitude = Double.parseDouble(coordinatesSplited[1]);

        return new Double[]{latitude, longitude};
    }

    private InlineKeyboardMarkup cafeMenuInlineKeyboard(String address) {
        InlineKeyboardButton button = InlineKeyboardButton.builder()
                .text(MENU)
                .switchInlineQueryCurrentChat("cafe-" + address)
                .build();

        return InlineKeyboardMarkup.builder()
                .keyboard(List.of(List.of(button)))
                .build();
    }

    private InlineQueryResultArticle menuElement(DrinkDto drink, String queryText) {
        InputTextMessageContent textMessageContent = InputTextMessageContent.builder()
                .messageText("#drink-" + drink.getId() + "_" + queryText)
                .build();

        return InlineQueryResultArticle.builder()
                .id(String.valueOf(drink.getId()))
                .title(drink.getName())
                .description(drink.getDescription())
                .inputMessageContent(textMessageContent)
                .build();
    }

    private InlineKeyboardMarkup selectedDrinkKeyboard(DrinkDto drink, Long cafeId) {
        List<ValuesDto> valuesList = drink.getValues().stream()
                .sorted(Comparator.comparing(ValuesDto::getPrice)).toList();

        List<InlineKeyboardButton> firstRow = new ArrayList<>();
        for (ValuesDto value : valuesList) {
            InlineKeyboardButton button = InlineKeyboardButton.builder()
                    .text(value.getVolume())
                    .callbackData(value.getVolume())
                    .build();
            firstRow.add(button);
        }

        InlineKeyboardButton payButton = InlineKeyboardButton.builder()
                .text("Оплатить")
                .callbackData("pay_cafe-" + cafeId + "_drink-" + drink.getId() + "_value-S")
                .build();
        List<InlineKeyboardButton> secondRow = List.of(payButton);

        return InlineKeyboardMarkup.builder()
                .keyboard(List.of(firstRow, secondRow))
                .build();
    }
}