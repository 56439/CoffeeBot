package ru.malyshev.coffeebot.models;

import lombok.*;
import ru.malyshev.coffeebot.dto.OrderDto;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
@ToString
public class Order implements ModelConverter<OrderDto> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "drink_id")
    private Drink drink;

    @Column(name = "volume", nullable = false)
    private Volume volume;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "cafe_id")
    private Cafe cafe;

    @Override
    public OrderDto toDto() {
        return new OrderDto(id, drink.toDto(), volume.toString(), user.toDto(), cafe.toDto());
    }
}
