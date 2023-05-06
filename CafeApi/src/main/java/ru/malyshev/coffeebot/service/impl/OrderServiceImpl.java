package ru.malyshev.coffeebot.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.malyshev.coffeebot.dto.OrderDto;
import ru.malyshev.coffeebot.models.Order;
import ru.malyshev.coffeebot.repo.OrderRepo;
import ru.malyshev.coffeebot.service.OrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;

    @Override
    public OrderDto save(OrderDto dto) {
        Order order = orderRepo.save(dto.toModel());
        return order.toDto();
    }

    @Override
    public List<OrderDto> getAll() {
        List<Order> orderList = orderRepo.findAll();
        return toDtoList(orderList);
    }

    @Override
    public OrderDto getById(long id) {
        Optional<Order> order = orderRepo.findById(id);
        if (order.isEmpty()) return null;
        return order.get().toDto();
    }

    @Override
    public void deleteById(long id) {
        orderRepo.deleteById(id);
    }

    private List<OrderDto> toDtoList(List<Order> orderList) {
        List<OrderDto> orderDtoList = new ArrayList<>();
        for (Order order : orderList) {
            orderDtoList.add(order.toDto());
        }
        return orderDtoList;
    }
}