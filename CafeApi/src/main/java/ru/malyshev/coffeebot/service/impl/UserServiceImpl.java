package ru.malyshev.coffeebot.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.malyshev.coffeebot.dto.UserDto;
import ru.malyshev.coffeebot.models.User;
import ru.malyshev.coffeebot.repo.UserRepo;
import ru.malyshev.coffeebot.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public UserDto save(UserDto dto) {
        User user = userRepo.save(dto.toModel());
        return user.toDto();
    }

    @Override
    public List<UserDto> getAll() {
        List<User> userList = userRepo.findAll();
        return toDtoList(userList);
    }

    @Override
    public UserDto getById(long id) {
        Optional<User> user = userRepo.findById(id);
        if (user.isEmpty()) return null;
        return user.get().toDto();
    }

    @Override
    public void deleteById(long id) {
        userRepo.deleteById(id);
    }

    @Override
    public UserDto getByChatId(Long chatId) {
        Optional<User> user = userRepo.findByChatId(chatId);
        if (user.isEmpty()) return null;
        return user.get().toDto();
    }

    private List<UserDto> toDtoList(List<User> userList) {
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : userList) {
            userDtoList.add(user.toDto());
        }
        return userDtoList;
    }

}