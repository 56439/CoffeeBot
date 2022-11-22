package ru.malyshev.coffeebot.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.malyshev.coffeebot.dto.OrderDto;
import ru.malyshev.coffeebot.repo.OrderRepo;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.malyshev.coffeebot.TestData.ORDER_1;
import static ru.malyshev.coffeebot.TestData.ORDER_LIST;

class OrderServiceImplTest {

    private OrderServiceImpl orderService;

    private OrderRepo orderRepo = Mockito.mock(OrderRepo.class);

    @BeforeEach
    void init() {
        orderService = new OrderServiceImpl(orderRepo);
    }

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