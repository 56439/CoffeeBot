package ru.malyshev.coffeebot.service;

import ru.malyshev.coffeebot.dto.CafeDto;

public interface CafeService extends BaseService<CafeDto> {

    CafeDto getByAddress(String address);
}