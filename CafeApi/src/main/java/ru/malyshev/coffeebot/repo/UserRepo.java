package ru.malyshev.coffeebot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.malyshev.coffeebot.models.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByChatId(Long chatId);
}