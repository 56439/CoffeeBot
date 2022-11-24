package ru.malyshev.coffeebot.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.malyshev.coffeebot.dto.UserDto;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements ModelConverter<UserDto> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "chat_id", nullable = false, unique = true)
    private Long chatId;

    @Column(name = "name", nullable = false)
    private String name;

    @Override
    public UserDto toDto() {
        return new UserDto(id, chatId, name);
    }
}
