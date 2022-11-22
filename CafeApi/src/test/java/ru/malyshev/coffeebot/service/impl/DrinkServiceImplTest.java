package ru.malyshev.coffeebot.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.malyshev.coffeebot.dto.DrinkDto;
import ru.malyshev.coffeebot.repo.DrinkRepo;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.malyshev.coffeebot.TestData.DRINK_1;
import static ru.malyshev.coffeebot.TestData.DRINK_LIST;

class DrinkServiceImplTest {

    private DrinkServiceImpl drinkService;

    private DrinkRepo drinkRepo = Mockito.mock(DrinkRepo.class);

    @BeforeEach
    void init() {
        drinkService = new DrinkServiceImpl(drinkRepo);
    }

    @Test
    void getAll() {
        Mockito.when(drinkRepo.findAll()).thenReturn(DRINK_LIST);
        List<DrinkDto> drinkDtoList = drinkService.getAll();

        assertEquals(DRINK_LIST.size(), drinkDtoList.size());
    }

    @Test
    void getById() {
        Long expected = DRINK_1.getId();
        Mockito.when(drinkRepo.findById(expected)).thenReturn(Optional.of(DRINK_1));
        DrinkDto drink = drinkService.getById(expected);

        assertEquals(expected, drink.getId());
    }
}