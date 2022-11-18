package ru.malyshev.coffeebot.dto;

public interface DtoConverter<T> {

    T toModel();
}