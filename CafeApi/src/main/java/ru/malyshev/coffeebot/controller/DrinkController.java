package ru.malyshev.coffeebot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.malyshev.coffeebot.dto.DrinkDto;
import ru.malyshev.coffeebot.service.DrinkService;

import java.util.List;

@RestController
@RequestMapping("/api/drink")
@RequiredArgsConstructor
public class DrinkController {

    private final DrinkService drinkService;

    @PostMapping(value = "/save", headers = "Accept=application/json")
    public DrinkDto saveDrink(@RequestBody DrinkDto drink) {
        return drinkService.save(drink);
    }

    @GetMapping("/all")
    public List<DrinkDto> getAllDrinks() {
        return drinkService.getAll();
    }

    @GetMapping("/{id}")
    public DrinkDto getDrinkById(@PathVariable Long id) {
        return drinkService.getById(id);
    }

    @PostMapping("/delete/{id}")
    public void deleteDrink(@PathVariable Long id) {
        drinkService.deleteById(id);
    }
}