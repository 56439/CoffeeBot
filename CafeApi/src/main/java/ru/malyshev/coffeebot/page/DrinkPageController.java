package ru.malyshev.coffeebot.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DrinkPageController {

    @GetMapping("/")
    public String listDrinks() {
        return "drinks";
    }

    @GetMapping("/addDrink")
    public String addDrink() {
        return "addDrink";
    }

    @GetMapping("/editDrink/{drinkId}")
    public String editDrink(@PathVariable Long drinkId, Model model) {
        model.addAttribute("id", drinkId);
        return "editDrink";
    }
}
