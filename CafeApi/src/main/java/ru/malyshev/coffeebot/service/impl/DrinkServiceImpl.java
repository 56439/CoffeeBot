package ru.malyshev.coffeebot.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.malyshev.coffeebot.dto.DrinkDto;
import ru.malyshev.coffeebot.models.Drink;
import ru.malyshev.coffeebot.repo.DrinkRepo;
import ru.malyshev.coffeebot.service.DrinkService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DrinkServiceImpl implements DrinkService {

    private final DrinkRepo drinkRepo;

    @Override
    public DrinkDto save(DrinkDto dto) {
        Drink drink = drinkRepo.save(dto.toModel());
        return drink.toDto();
    }

    @Override
    public List<DrinkDto> getAll() {
        List<Drink> cafeList = drinkRepo.findAll();
        return toDtoList(cafeList);
    }

    @Override
    public DrinkDto getById(long id) {
        Optional<Drink> drink = drinkRepo.findById(id);
        if (drink.isEmpty()) return null;
        return drink.get().toDto();
    }

    @Override
    public void deleteById(long id) {
        drinkRepo.deleteById(id);
    }

    private List<DrinkDto> toDtoList(List<Drink> drinkList) {
        List<DrinkDto> drinkDtoList = new ArrayList<>();
        for (Drink drink : drinkList) {
            drinkDtoList.add(drink.toDto());
        }
        return drinkDtoList;
    }
}