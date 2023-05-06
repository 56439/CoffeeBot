package ru.malyshev.telegrambot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DrinkDto {

    private Long id;
    private String name;
    private String description;
    private Set<ValuesDto> values;
}