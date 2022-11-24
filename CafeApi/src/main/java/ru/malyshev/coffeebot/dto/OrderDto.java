package ru.malyshev.coffeebot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.malyshev.coffeebot.models.Order;
import ru.malyshev.coffeebot.models.Volume;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto implements DtoConverter<Order> {

    private Long id;
    private DrinkDto drink;
    private String volume;
    private UserDto user;
    private CafeDto cafe;

    @Override
    public Order toModel() {
        return new Order(id, drink.toModel(), Volume.valueOf(volume), user.toModel(), cafe.toModel());
    }
}