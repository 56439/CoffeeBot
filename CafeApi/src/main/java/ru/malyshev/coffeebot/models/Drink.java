package ru.malyshev.coffeebot.models;

import lombok.*;
import ru.malyshev.coffeebot.dto.DrinkDto;
import ru.malyshev.coffeebot.dto.ValuesDto;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "drinks")
@ToString
public class Drink implements ModelConverter<DrinkDto> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "drink_volumes", joinColumns = @JoinColumn(name = "drink_id"))
    private Set<Values> values;

    @Override
    public DrinkDto toDto() {
        Set<ValuesDto> valuesDtos = new HashSet<>();
        for (Values model : values) {
            valuesDtos.add(model.toDto());
        }
        return new DrinkDto(id, name, description, valuesDtos);
    }
}