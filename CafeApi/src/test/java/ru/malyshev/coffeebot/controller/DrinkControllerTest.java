package ru.malyshev.coffeebot.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.malyshev.coffeebot.security.config.SecurityConfig;
import ru.malyshev.coffeebot.service.CafeService;
import ru.malyshev.coffeebot.service.DrinkService;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.malyshev.coffeebot.TestData.*;

@WebMvcTest(DrinkController.class)
@Import(SecurityConfig.class)
class DrinkControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DrinkService drinkService;

    @Test
    @WithMockUser(username = "adminTest", roles = "ADMIN")
    void saveDrink_Auth_Test() throws Exception {
        mvc.perform(
                post("/api/drink/save")
                        .contentType("application/json")
                        .characterEncoding("utf-8")
                        .content(objectMapper.writeValueAsString(DRINK_DTO_1))
        ).andExpect(status().isOk());
    }

    @Test
    void saveDrink_NoAuth_Test() throws Exception {
        mvc.perform(
                post("/api/drink/save")
                        .contentType("application/json")
                        .characterEncoding("utf-8")
                        .content(objectMapper.writeValueAsString(DRINK_DTO_1))
        ).andExpect(status().isFound());
    }

    @Test
    @WithMockUser(username = "adminTest", roles = "ADMIN")
    void getAllDrinks_Auth_Test() throws Exception {
        mvc.perform(post("/api/drink/delete/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllDrinks_NoAuth_Test() throws Exception {
        mvc.perform(post("/api/drink/delete/1"))
                .andExpect(status().isFound());
    }

    @Test
    @WithMockUser(username = "adminTest", roles = "ADMIN")
    void deleteDrink_Auth_Test() throws Exception {
        Mockito.when(drinkService.getAll()).thenReturn(DRINK_DTO_LIST);
        mvc.perform(get("/api/drink/all"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteDrink_NoAuth_Test() throws Exception {
        Mockito.when(drinkService.getAll()).thenReturn(DRINK_DTO_LIST);
        mvc.perform(get("/api/drink/all"))
                .andExpect(status().isOk());
    }
}