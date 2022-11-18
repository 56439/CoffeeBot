package ru.malyshev.coffeebot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.malyshev.coffeebot.models.Cafe;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CafeDto implements DtoConverter<Cafe> {

    private Long id;
    private String address;
    private String coordinates;

    @Override
    public Cafe toModel() {
        return new Cafe(id, address, coordinates);
    }
}