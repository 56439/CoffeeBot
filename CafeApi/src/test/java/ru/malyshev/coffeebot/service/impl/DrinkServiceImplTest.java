package ru.malyshev.coffeebot.service.impl;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.malyshev.coffeebot.dto.DrinkDto;
import ru.malyshev.coffeebot.repo.DrinkRepo;
import ru.malyshev.coffeebot.service.impl.DrinkServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.malyshev.coffeebot.TestData.DRINK_1;
import static ru.malyshev.coffeebot.TestData.DRINK_LIST;

@SpringBootTest
@Disabled
class DrinkServiceImplTest {

    @Autowired
    private DrinkServiceImpl drinkService;

    @MockBean
    private DrinkRepo drinkRepo;

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