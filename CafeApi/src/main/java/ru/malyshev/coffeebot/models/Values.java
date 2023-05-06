package ru.malyshev.coffeebot.models;

import lombok.*;
import ru.malyshev.coffeebot.dto.ValuesDto;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Values implements ModelConverter<ValuesDto> {

    @NotNull
    @Enumerated(EnumType.STRING)
    private Volume volume;

    @NotNull
    private Integer price;

    @Override
    public ValuesDto toDto() {
        return new ValuesDto(volume.toString(), price);
    }
}