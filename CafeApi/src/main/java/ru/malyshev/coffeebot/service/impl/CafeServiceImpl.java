package ru.malyshev.coffeebot.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.malyshev.coffeebot.dto.CafeDto;
import ru.malyshev.coffeebot.models.Cafe;
import ru.malyshev.coffeebot.repo.CafeRepo;
import ru.malyshev.coffeebot.service.CafeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CafeServiceImpl implements CafeService {

    private final CafeRepo cafeRepo;

    @Override
    public CafeDto save(CafeDto dto) {
        Cafe cafe = cafeRepo.save(dto.toModel());
        return cafe.toDto();
    }

    @Override
    public List<CafeDto> getAll() {
        List<Cafe> cafeList = cafeRepo.findAll();
        return toDtoList(cafeList);
    }

    @Override
    public CafeDto getById(long id) {
        Optional<Cafe> cafe = cafeRepo.findById(id);
        if (cafe.isEmpty()) return null;
        return cafe.get().toDto();
    }

    @Override
    public void deleteById(long id) {
        cafeRepo.deleteById(id);
    }

    @Override
    public CafeDto getByAddress(String address) {
        Optional<Cafe> cafe = cafeRepo.findByAddress(address);
        if (cafe.isEmpty()) return null;
        return cafe.get().toDto();
    }

    private List<CafeDto> toDtoList(List<Cafe> cafeList) {
        List<CafeDto> cafeDtoList = new ArrayList<>();
        for (Cafe cafe : cafeList) {
            cafeDtoList.add(cafe.toDto());
        }
        return cafeDtoList;
    }
}