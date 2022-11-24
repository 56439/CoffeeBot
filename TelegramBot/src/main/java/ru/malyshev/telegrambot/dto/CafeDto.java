package ru.malyshev.telegrambot.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CafeDto {

    private Long id;
    private String address;
    private String coordinates;
}