package ru.malyshev.coffeebot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.malyshev.coffeebot.models.Drink;

public interface DrinkRepo extends JpaRepository<Drink, Long> {
}
