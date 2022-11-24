package ru.malyshev.coffeebot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.malyshev.coffeebot.models.Cafe;

import java.util.Optional;

public interface CafeRepo extends JpaRepository<Cafe, Long> {

    Optional<Cafe> findByAddress(String address);
}