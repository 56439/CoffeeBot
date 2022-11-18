package ru.malyshev.coffeebot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.malyshev.coffeebot.models.Order;

public interface OrderRepo extends JpaRepository<Order, Long> {
}
