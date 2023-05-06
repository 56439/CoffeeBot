package ru.malyshev.telegrambot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    private Long chatId;
    private String name;

    public UserDto(Long chatId, String name) {
        this.chatId = chatId;
        this.name = name;
    }
}