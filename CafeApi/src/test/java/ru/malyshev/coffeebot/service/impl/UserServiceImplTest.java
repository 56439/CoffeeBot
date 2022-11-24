package ru.malyshev.coffeebot.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.malyshev.coffeebot.dto.UserDto;
import ru.malyshev.coffeebot.repo.UserRepo;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.malyshev.coffeebot.TestData.USER_1;
import static ru.malyshev.coffeebot.TestData.USER_LIST;

class UserServiceImplTest {

    private UserServiceImpl userService;

    private UserRepo userRepo = Mockito.mock(UserRepo.class);

    @BeforeEach
    void init() {
        userService = new UserServiceImpl(userRepo);
    }

    @Test
    void getAll() {
        Mockito.when(userRepo.findAll()).thenReturn(USER_LIST);
        List<UserDto> userDtoList = userService.getAll();

        assertEquals(USER_LIST.size(), userDtoList.size());
    }

    @Test
    void getById() {
        Long expected = USER_1.getId();
        Mockito.when(userRepo.findById(expected)).thenReturn(Optional.of(USER_1));
        UserDto user = userService.getById(expected);

        assertEquals(expected, user.getId());
    }

    @Test
    void getByChatId() {
        Long expected = USER_1.getChatId();
        Mockito.when(userRepo.findByChatId(expected)).thenReturn(Optional.of(USER_1));
        UserDto user = userService.getByChatId(expected);

        assertEquals(expected, user.getChatId());
    }
}