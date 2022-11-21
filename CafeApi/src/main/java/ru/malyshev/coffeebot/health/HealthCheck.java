package ru.malyshev.coffeebot.health;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;
import ru.malyshev.coffeebot.repo.DrinkRepo;

@Component
@RequiredArgsConstructor
public class HealthCheck implements HealthIndicator {

    private final DrinkRepo drinkRepo;

    @Override
    public Health health() {
        if (drinkRepo.findAll().isEmpty()) {
            return Health.down()
                    .status(Status.DOWN)
                    .withDetail("message", "Сервер наелся и спит :(")
                    .build();
        } else {
            return Health.up().build();
        }
    }
}