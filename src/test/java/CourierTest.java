import courier.*;
import courier.Courier;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CourierTest {
    Courier courier;
    CourierClient courierClient;
    private int courierId;

    @Before
    public void setUp() {
        courier = Courier.getRandomCourier();
        courierClient = new CourierClient();
    }

    @After
    public void teardown() {
        if (courierId != 0) {
            courierClient.delete(courierId)
                    .statusCode(200);
        }
    }

    @Test
    @DisplayName("Проверка создание курьера")
    public void checkCourierCanBeCreated() {

        boolean isOk = courierClient.create(courier)
                .statusCode(201)
                .extract().path("ok");
        courierId = courierClient.login(CourierCredentials.from(courier))
                .statusCode(200)
                .extract().path("id");

        assertTrue(isOk);
        assertNotEquals(0, courierId);
    }

    @Test
    @DisplayName("Проверка создяния курьера на уникальность")
    public void checkCantCreateTwoSimilarCouriers() {

        courierClient.create(courier);
        courierId = courierClient.login(CourierCredentials.from(courier))
                .statusCode(200)
                .extract().path("id");

        String message = courierClient.create(courier)
                .statusCode(409)
                .extract().path("message");

        assertEquals("Этот логин уже используется. Попробуйте другой.", message);
    }

    @Test
    @DisplayName("Проверка полей при создании курьера")
    public void checkRequiredPasswordField() {

        courier.setPassword("");
        courierId = 0;

        String message = courierClient.create(courier)
                .statusCode(400)
                .extract().path("message");

        assertEquals("Недостаточно данных для создания учетной записи", message);
    }
}
