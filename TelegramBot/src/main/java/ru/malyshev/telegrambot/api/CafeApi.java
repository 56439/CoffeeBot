package ru.malyshev.telegrambot.api;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.malyshev.telegrambot.config.EurekaConfig;
import ru.malyshev.telegrambot.dto.CafeDto;
import ru.malyshev.telegrambot.dto.DrinkDto;
import ru.malyshev.telegrambot.dto.OrderDto;
import ru.malyshev.telegrambot.dto.UserDto;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class CafeApi {

    private static final String CAFE_API_SERVICE_NAME = "cafe-api";
    private static final String CAFE_API_URL = "/api/cafe";
    private static final String DRINK_API_URL = "/api/drink";
    private static final String USER_API_URL = "/api/user";
    private static final String ORDER_API_URL = "/api/order";

    private final String eurekaUrl;
    private final RestTemplate restTemplate;

    public CafeApi(EurekaConfig eurekaConfig, RestTemplate restTemplate) {
        this.eurekaUrl = eurekaConfig.getDefaultZone();
        this.restTemplate = restTemplate;
    }

    public List<CafeDto> getCafeList() {
        String url = cafeApiClientUrl();
        String requestUrl = url + CAFE_API_URL + "/all";
        ResponseEntity<CafeDto[]> cafeCollection = restTemplate.getForEntity(requestUrl, CafeDto[].class);

        if (!cafeCollection.hasBody()) return null;

        return List.of(Objects.requireNonNull(cafeCollection.getBody()));
    }

    public CafeDto getCafe(Long id) {
        String url = cafeApiClientUrl();
        String requestUrl = url + CAFE_API_URL + "/" + id;
        ResponseEntity<CafeDto> cafe = restTemplate.getForEntity(requestUrl, CafeDto.class);

        if (!cafe.hasBody()) return null;

        return cafe.getBody();
    }

    public CafeDto getCafeByAddress(String address) {
        String url = cafeApiClientUrl();
        String requestUrl = url + CAFE_API_URL + "?address={address}";
        Map<String, String> params = Map.of("address", address);

        ResponseEntity<CafeDto> cafe = restTemplate.getForEntity(requestUrl, CafeDto.class, params);

        if (!cafe.hasBody()) return null;

        return cafe.getBody();
    }

    public List<DrinkDto> getDrinks() {
        String url = cafeApiClientUrl();
        String requestUrl = url + DRINK_API_URL + "/all";
        ResponseEntity<DrinkDto[]> drinksCollection = restTemplate.getForEntity(requestUrl, DrinkDto[].class);

        if (!drinksCollection.hasBody()) return null;

        return List.of(Objects.requireNonNull(drinksCollection.getBody()));
    }

    public DrinkDto getDrink(Long id) {
        String url = cafeApiClientUrl();
        String requestUrl = url + DRINK_API_URL + "/" + id;
        ResponseEntity<DrinkDto> drink = restTemplate.getForEntity(requestUrl, DrinkDto.class);

        if (!drink.hasBody()) return null;

        return drink.getBody();
    }

    public UserDto getUserByChatId(Long chatId) {
        String url = cafeApiClientUrl();
        String requestUrl = url + USER_API_URL + "?chatId={chatId}";
        Map<String, Long> params = Map.of("chatId", chatId);
        ResponseEntity<UserDto> user = restTemplate.getForEntity(requestUrl, UserDto.class, params);

        if (!user.hasBody()) return null;

        return user.getBody();
    }

    public UserDto createUser(UserDto userDto) {
        String url = cafeApiClientUrl();
        String requestUrl = url + USER_API_URL + "/save";
        ResponseEntity<UserDto> user = restTemplate.postForEntity(requestUrl, userDto, UserDto.class);

        if (!user.hasBody()) return null;

        return user.getBody();
    }

    public OrderDto createOrder(OrderDto orderDto) {
        String url = cafeApiClientUrl();
        String requestUrl = url + ORDER_API_URL + "/save";
        ResponseEntity<OrderDto> order = restTemplate.postForEntity(requestUrl, orderDto, OrderDto.class);

        if (!order.hasBody()) return null;

        return order.getBody();
    }

    private String cafeApiClientUrl() {
        RestTemplate restTemplate = new RestTemplate();
        String requestUrl = eurekaUrl + "/apps/" + CAFE_API_SERVICE_NAME;

        ResponseEntity<String> client = restTemplate.getForEntity(requestUrl, String.class);

        JSONObject jsonClient = new JSONObject(Objects.requireNonNull(client.getBody()));
        JSONObject clientInstance = (JSONObject) jsonClient.getJSONObject("application").getJSONArray("instance").get(0);

        String url = clientInstance.getString("hostName");
        int port = clientInstance.getJSONObject("port").getInt("$");

        return "http://" + url + ":" + port;
    }
}
