package ru.malyshev.coffeebot.models;

import lombok.Getter;

@Getter
public enum Volume {

    S(200),
    M(300),
    L(400);

    private final int value;

    Volume(int value) {
        this.value = value;
    }
}