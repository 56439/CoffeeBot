package ru.malyshev.coffeebot.service.impl;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.malyshev.coffeebot.dto.CafeDto;
import ru.malyshev.coffeebot.repo.CafeRepo;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.malyshev.coffeebot.TestData.CAFE_1;
import static ru.malyshev.coffeebot.TestData.CAFE_LIST;

@SpringBootTest
@Disabled
public class CafeServiceImplTest {

    @Autowired
    private CafeServiceImpl cafeService;

    @MockBean
    private CafeRepo cafeRepo;

    @Test
    void getAll() {
        Mockito.when(cafeRepo.findAll()).thenReturn(CAFE_LIST);
        List<CafeDto> cafeDtoList = cafeService.getAll();

        assertEquals(CAFE_LIST.size(), cafeDtoList.size());
    }

    @Test
    void getById() {
        Long expected = CAFE_1.getId();
        Mockito.when(cafeRepo.findById(expected)).thenReturn(Optional.of(CAFE_1));
        CafeDto cafe = cafeService.getById(expected);

        assertEquals(expected, cafe.getId());
    }
}