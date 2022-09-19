import courier.Courier;
import courier.CourierClient;
import courier.CourierCredentials;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginTest {
    Courier courier;
    CourierClient courierClient;
    private int courierId;

    @Before
    public void setUp() {
        courier = Courier.getRandomCourier();
        courierClient = new CourierClient();
        courierClient.create(courier);
    }

    @After
    public void teardown() {
        if (courierId != 0) {
            courierClient.delete(courierId)
                    .statusCode(200);
        }
    }

    @Test
    @DisplayName("Логин под существующим курьером")
    public void loginCourier() {

        courierId = courierClient.login(CourierCredentials.from(courier))
                .statusCode(200)
                .extract().path("id");

        assertNotEquals(0, courierId);
    }

    @Test
    @DisplayName("Логин без пароля")
    public void loginCourierWithoutPassword() {

        // логин курьера, убеждаемся что все ок
        courierId = courierClient.login(CourierCredentials.from(courier))
                .statusCode(200)
                .extract().path("id");

        courier.setPassword("");

        // логин без пароля
        String message = courierClient.login(CourierCredentials.from(courier))
                .statusCode(400)
                .extract().path("message");

        assertEquals("Недостаточно данных для входа", message);
    }

    @Test
    @DisplayName("Логин под несуществующим курьером")
    public void loginCourierNotFound() {

        courier = new Courier("testCourier", "testPassword", "TestName");
        courierId = 0;

        String message = courierClient.login(CourierCredentials.from(courier))
                .statusCode(404)
                .extract().path("message");

        assertEquals("Учетная запись не найдена", message);
    }
}
