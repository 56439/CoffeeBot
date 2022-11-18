package ru.malyshev.coffeebot;

import ru.malyshev.coffeebot.dto.*;
import ru.malyshev.coffeebot.models.*;

import java.util.List;
import java.util.Set;

public class TestData {

    private final static Volume VOLUME_S = Volume.S;
    private final static Volume VOLUME_M = Volume.M;
    private final static Set<Values> VALUES = Set.of(
            new Values(VOLUME_S, 111),
            new Values(VOLUME_M, 222)
    );

    private final static Set<ValuesDto> VALUES_DTO = Set.of(
            new ValuesDto(VOLUME_S.toString(), 111),
            new ValuesDto(VOLUME_M.toString(), 222)
    );

    // DTO
    public final static CafeDto CAFE_DTO_1 = new CafeDto(1L, "address_1", "coordinates_1");
    public final static DrinkDto DRINK_DTO_1 = new DrinkDto(1L, "drink_1", "descr_1", VALUES_DTO);
    public final static UserDto USER_DTO_1 = new UserDto(1L, 123L, "user_1");
    public final static OrderDto ORDER_DTO_1 = new OrderDto(1L, DRINK_DTO_1, VOLUME_S.toString(), USER_DTO_1, CAFE_DTO_1);

    // MODELS
    public final static Cafe CAFE_1 = new Cafe(1L, "address_1", "coordinates_1");
    public final static Cafe CAFE_2 = new Cafe(2L, "address_2", "coordinates_2");
    public final static List<Cafe> CAFE_LIST = List.of(CAFE_1, CAFE_2);

    public final static Drink DRINK_1 = new Drink(1L, "drink_1", "descr_1", VALUES);
    public final static Drink DRINK_2 = new Drink(2L, "drink_2", "descr_2", VALUES);
    public final static List<Drink> DRINK_LIST = List.of(DRINK_1, DRINK_2);

    public final static User USER_1 = new User(1L, 123L, "user_1");
    public final static User USER_2 = new User(2L, 456L, "user_2");
    public final static List<User> USER_LIST = List.of(USER_1, USER_2);

    public final static Order ORDER_1 = new Order(1L, DRINK_1, VOLUME_S, USER_1, CAFE_1);
    public final static Order ORDER_2 = new Order(2L, DRINK_2, VOLUME_M, USER_2, CAFE_2);
    public final static List<Order> ORDER_LIST = List.of(ORDER_1, ORDER_2);
}