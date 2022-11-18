package ru.malyshev.coffeebot.dto;

import lombok.*;
import ru.malyshev.coffeebot.models.Values;
import ru.malyshev.coffeebot.models.Volume;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ValuesDto implements DtoConverter<Values>{

    private String volume;

    private Integer price;

    @Override
    public Values toModel() {
        return new Values(Volume.valueOf(volume), price);
    }
}
