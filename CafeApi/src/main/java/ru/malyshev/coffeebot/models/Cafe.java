package ru.malyshev.coffeebot.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.malyshev.coffeebot.dto.CafeDto;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cafe")
public class Cafe implements ModelConverter<CafeDto> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "coordinates", nullable = false)
    private String coordinates;

    @Override
    public CafeDto toDto() {
        return new CafeDto(id, address, coordinates);
    }
}
