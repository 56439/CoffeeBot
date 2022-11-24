package ru.malyshev.coffeebot.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CafePageController {

    @GetMapping("/cafe")
    public String listDrinks() {
        return "cafe";
    }

    @GetMapping("/addCafe")
    public String addCafe() {
        return "addCafe";
    }

    @GetMapping("/editCafe/{cafeId}")
    public String editCafe(@PathVariable Long cafeId, Model model) {
        model.addAttribute("id", cafeId);
        return "editCafe";
    }
}