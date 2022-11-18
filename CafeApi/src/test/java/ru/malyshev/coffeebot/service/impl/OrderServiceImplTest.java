package ru.malyshev.coffeebot.service.impl;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.malyshev.coffeebot.dto.OrderDto;
import ru.malyshev.coffeebot.repo.OrderRepo;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.malyshev.coffeebot.TestData.ORDER_1;
import static ru.malyshev.coffeebot.TestData.ORDER_LIST;

@SpringBootTest
@Disabled
class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    @MockBean
    private OrderRepo orderRepo;

    @Test
    void getAll() {
        Mockito.when(orderRepo.findAll()).thenReturn(ORDER_LIST);
        List<OrderDto> orderDtoList = orderService.getAll();

        assertEquals(ORDER_LIST.size(), orderDtoList.size());
    }

    @Test
    void getById() {
        Long expected = ORDER_1.getId();;
        Mockito.when(orderRepo.findById(expected)).thenReturn(Optional.of(ORDER_1));
        OrderDto order = orderService.getById(expected);

        assertEquals(expected, order.getId());
    }
}