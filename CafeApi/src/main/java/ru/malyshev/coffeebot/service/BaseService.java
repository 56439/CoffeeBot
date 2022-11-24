package ru.malyshev.coffeebot.service;

import java.util.List;

public interface BaseService<T>{

    T save(T dto);

    List<T> getAll();

    T getById(long id);

    void deleteById(long id);
}