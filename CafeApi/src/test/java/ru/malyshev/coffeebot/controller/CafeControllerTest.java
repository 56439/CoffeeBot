package ru.malyshev.coffeebot.controller;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.malyshev.coffeebot.TestData.CAFE_DTO_1;
import static ru.malyshev.coffeebot.TestData.CAFE_DTO_LIST;

@WebMvcTest(CafeController.class)
@Import(SecurityConfig.class)
class CafeControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CafeService cafeService;

    @Test
    @WithMockUser(username = "adminTest", roles = "ADMIN")
    void saveCafe_Auth_Test() throws Exception {
        mvc.perform(
                post("/api/cafe/save")
                        .contentType("application/json")
                        .characterEncoding("utf-8")
                        .content(objectMapper.writeValueAsString(CAFE_DTO_1))
        ).andExpect(status().isOk());
    }

    @Test
    void saveCafe_NoAuth_Test() throws Exception {
        mvc.perform(
                post("/api/cafe/save")
                        .contentType("application/json")
                        .characterEncoding("utf-8")
                        .content(objectMapper.writeValueAsString(CAFE_DTO_1))
        ).andExpect(status().isFound());
    }

    @Test
    @WithMockUser(username = "adminTest", roles = "ADMIN")
    void deleteCafe_Auth_Test() throws Exception {
        mvc.perform(post("/api/cafe/delete/1"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteCafe_NoAuth_Test() throws Exception {
        mvc.perform(post("/api/cafe/delete"))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    @WithMockUser(username = "adminTest", roles = "ADMIN")
    void getAllCafe_Auth_Test() throws Exception {
        Mockito.when(cafeService.getAll()).thenReturn(CAFE_DTO_LIST);
        mvc.perform(get("/api/cafe/all"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllCafe_NoAuth_Test() throws Exception {
        Mockito.when(cafeService.getAll()).thenReturn(CAFE_DTO_LIST);
        mvc.perform(get("/api/cafe/all"))
                .andExpect(status().isOk());
    }
}