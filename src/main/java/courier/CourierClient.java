package courier;

import config.BaseClient;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

public class CourierClient extends BaseClient {
    private final String ROOT = "/courier";
    private static final String COURIER = "/courier/{courierId}";
    private final String LOGIN = ROOT + "/login";

    @Step("Удаляем курьера {0}")
    public ValidatableResponse delete(int courierId) {
        return getSpec()
                .pathParam("courierId", courierId)
                .when()
                .delete(COURIER)
                .then().log().all();
    }

    @Step("Создание курьера")
    public ValidatableResponse create(Courier courier) {
        return getSpec()
                .body(courier)
                .when()
                .post(ROOT)
                .then().log().all();
    }

    @Step("Логин курьера в системе")
    public ValidatableResponse login(CourierCredentials creds) {
        return getSpec()
                .body(creds)
                .when()
                .post(LOGIN)
                .then().log().all();
    }
}
