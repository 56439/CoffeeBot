package ru.malyshev.coffeebot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.malyshev.coffeebot.dto.OrderDto;
import ru.malyshev.coffeebot.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping(value = "/save", headers = "Accept=application/json")
    public OrderDto saveOrder(@RequestBody OrderDto order) {
        return orderService.save(order);
    }

    @GetMapping("/all")
    public List<OrderDto> getAllOrders() {
        return orderService.getAll();
    }

    @GetMapping("/{id}")
    public OrderDto getOrderById(@PathVariable Long id) {
        return orderService.getById(id);
    }

    @PostMapping("/delete/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteById(id);
    }
}