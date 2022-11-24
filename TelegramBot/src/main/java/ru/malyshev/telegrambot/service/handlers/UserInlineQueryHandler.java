package ru.malyshev.telegrambot.service.handlers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.meta.api.objects.inlinequery.InlineQuery;
import ru.malyshev.telegrambot.service.messages.CustomMessage;

@Component
@RequiredArgsConstructor
public class UserInlineQueryHandler implements Handler<InlineQuery> {

    private final CustomMessage customMessage;

    @Override
    public AnswerInlineQuery handle(InlineQuery query) {
        String queryText = query.getQuery();

        if (queryText.startsWith("cafe")) {
            return customMessage.menuList(query);
        } else {
            return null;
        }
    }
}