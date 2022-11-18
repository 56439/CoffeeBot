package ru.malyshev.coffeebot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.malyshev.coffeebot.models.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements DtoConverter<User> {

    private Long id;
    private Long chatId;
    private String name;

    @Override
    public User toModel() {
        return new User(id, chatId, name);
    }
}