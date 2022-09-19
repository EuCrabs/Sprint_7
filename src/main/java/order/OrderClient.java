package order;

import config.BaseClient;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

public class OrderClient extends BaseClient {
    private final String ROOT = "/orders";
    @Step("Создание заказа")
    public ValidatableResponse create(Order order) {
        return getSpec()
                .body(order)
                .when()
                .post(ROOT)
                .then().log().all();
    }

    @Step("Получение списка заказов")
    public ValidatableResponse getOrders() {
        return getSpec()
                .when()
                .get(ROOT)
                .then().log().all();
    }
}
