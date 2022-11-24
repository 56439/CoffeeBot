package ru.malyshev.telegrambot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long id;
    private DrinkDto drink;
    private String volume;
    private UserDto user;
    private CafeDto cafe;

    public OrderDto(DrinkDto drink, String volume, UserDto user, CafeDto cafe) {
        this.drink = drink;
        this.volume = volume;
        this.user = user;
        this.cafe = cafe;
    }
}