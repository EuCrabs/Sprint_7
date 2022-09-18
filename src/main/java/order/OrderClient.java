package order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

public class OrderClient extends BaseClient {
    private final String ROOT = "/orders";
    private static final String CANCEL = "/cancel";
    private final String LOGIN = ROOT + "/login";
    @Step("Создание заказа")
    public ValidatableResponse create(Order order) {
        return getSpec()
                .body(order)
                .when()
                .post(ROOT)
                .then().log().all();
    }

//    @Step("Отмена заказа")
//    public ValidatableResponse cancel(Order order) {
//        return getSpec()
//                .body(order)
//                .when()
//                .post(ROOT)
//                .then().log().all();
//    }
}
