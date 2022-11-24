package ru.malyshev.coffeebot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.malyshev.coffeebot.models.Drink;
import ru.malyshev.coffeebot.models.Values;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DrinkDto implements DtoConverter<Drink> {

    private Long id;
    private String name;
    private String description;
    private Set<ValuesDto> values;

    @Override
    public Drink toModel() {
        Set<Values> valuesModel = new HashSet<>();
        for (ValuesDto dto : values) {
            valuesModel.add(dto.toModel());
        }
        return new Drink(id, name, description, valuesModel);
    }
}