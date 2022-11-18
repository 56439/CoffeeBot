package ru.malyshev.coffeebot.service.impl;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.malyshev.coffeebot.dto.UserDto;
import ru.malyshev.coffeebot.repo.UserRepo;
import ru.malyshev.coffeebot.service.impl.UserServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.malyshev.coffeebot.TestData.USER_1;
import static ru.malyshev.coffeebot.TestData.USER_LIST;

@SpringBootTest
@Disabled
class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @MockBean
    private UserRepo userRepo;

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
}