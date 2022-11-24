package ru.malyshev.coffeebot.models;

public interface ModelConverter<T> {

    T toDto();
}