package ru.malyshev.coffeebot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.malyshev.coffeebot.dto.UserDto;
import ru.malyshev.coffeebot.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/save", headers = "Accept=application/json")
    public UserDto saveUser(@RequestBody UserDto user) {
        return userService.save(user);
    }

    @GetMapping("/all")
    public List<UserDto> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @PostMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
    }

    @GetMapping
    public UserDto getUserByChatId(@RequestParam String chatId) {
        return userService.getByChatId(Long.valueOf(chatId));
    }
}