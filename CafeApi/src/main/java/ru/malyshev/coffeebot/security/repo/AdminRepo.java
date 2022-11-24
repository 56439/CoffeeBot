package ru.malyshev.coffeebot.security.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.malyshev.coffeebot.security.models.Admin;

public interface AdminRepo extends JpaRepository<Admin, Long> {

    Admin findByUsername(String username);
}