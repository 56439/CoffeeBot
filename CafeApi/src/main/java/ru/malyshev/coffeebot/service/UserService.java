package ru.malyshev.coffeebot.service;

import ru.malyshev.coffeebot.dto.UserDto;

public interface UserService extends BaseService<UserDto> {

    UserDto getByChatId(Long chatId);
}