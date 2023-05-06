package ru.malyshev.coffeebot.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.malyshev.coffeebot.dto.CafeDto;
import ru.malyshev.coffeebot.repo.CafeRepo;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.malyshev.coffeebot.TestData.CAFE_1;
import static ru.malyshev.coffeebot.TestData.CAFE_LIST;

public class CafeServiceImplTest {

    private CafeServiceImpl cafeService;

    private CafeRepo cafeRepo = Mockito.mock(CafeRepo.class);

    @BeforeEach
    void init() {
        cafeService = new CafeServiceImpl(cafeRepo);
    }

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

    @Test
    void getByAddress() {
        String expected = CAFE_1.getAddress();
        Mockito.when(cafeRepo.findByAddress(expected)).thenReturn(Optional.of(CAFE_1));
        CafeDto cafe = cafeService.getByAddress(expected);

        assertEquals(expected, cafe.getAddress());
    }
}