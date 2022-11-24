package ru.malyshev.coffeebot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.malyshev.coffeebot.dto.CafeDto;
import ru.malyshev.coffeebot.service.CafeService;

import java.util.List;

@RestController
@RequestMapping("/api/cafe")
@RequiredArgsConstructor
public class CafeController {

    private final CafeService cafeService;

    @PostMapping(value = "/save", headers = "Accept=application/json")
    public CafeDto saveCafe(@RequestBody CafeDto cafe) {
        return cafeService.save(cafe);
    }

    @GetMapping("/all")
    public List<CafeDto> getAllCafe() {
        return cafeService.getAll();
    }

    @GetMapping("/{id}")
    public CafeDto getCafeById(@PathVariable Long id) {
        return cafeService.getById(id);
    }

    @PostMapping("/delete/{id}")
    public void deleteCafe(@PathVariable Long id) {
        cafeService.deleteById(id);
    }

    @GetMapping
    public CafeDto getCafeByAddress(@RequestParam String address) {
        return cafeService.getByAddress(address);
    }
}