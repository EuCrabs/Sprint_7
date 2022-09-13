import Courier.*;
import Methods.Courier;
import TestData.TestData;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class CourierLoginTest {

    public String login;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
        login = Courier.userLoginGenerator();
        TestMethods.Courier.courierCreation(login);
    }

    @After
    public void teardown() {
        try {
            TestMethods.Courier.courierDeletion(login);
        } catch (Exception e) {
            System.out.println("Учетная запись не найдена");
        }
    }

    @Test
    public void isCourierLoggedIn() {

        Integer code = 200;
        CourierLoginRequest request = Courier.courierLoginRequest(login, TestData.password);

        CourierLoginResponse response = given()
                .header("Content-type", "application/json")
                .body(request)
                .when()
                .post("/api/v1/courier/login")
                .then()
                .statusCode(code)
                .log().all()
                .extract().body().as(CourierLoginResponse.class);

        // Assert
        assertNotNull(response.getId());
    }

    @Test
    public void loginWithInvalidPassword() {

        Integer code = 404;
        String password = "Invalid";
        String message = "Учетная запись не найдена";
        CourierLoginRequest request = Courier.courierLoginRequest(login, password);

        CourierLoginResponse response = given()
                .header("Content-type", "application/json")
                .body(request)
                .when()
                .post("/api/v1/courier/login")
                .then()
                .statusCode(code)
                .log().all()
                .extract().body().as(CourierLoginResponse.class);

        // Assert
        assertEquals(code, response.getCode());
        assertEquals(message, response.getMessage());
    }

    @Test
    public void loginWithNonExistUser() {

        String nonExistUser = "NotExist";
        Integer code = 404;
        String message = "Учетная запись не найдена";
        CourierLoginRequest request = Courier.courierLoginRequest(nonExistUser, TestData.password);

        CourierLoginResponse response = given()
                .header("Content-type", "application/json")
                .body(request)
                .when()
                .post("/api/v1/courier/login")
                .then()
                .statusCode(code)
                .log().all()
                .extract().body().as(CourierLoginResponse.class);

        // Assert
        assertEquals(code, response.getCode());
        assertEquals(message, response.getMessage());
    }

    @Test
    public void loginWithoutPassword() {

        Integer code = 400;
        String message = "Недостаточно данных для входа";
        CourierLoginRequest request = Courier.courierLoginWithoutPasswordRequest(login);

        CourierLoginResponse response = given()
                .header("Content-type", "application/json")
                .body(request)
                .when()
                .post("/api/v1/courier/login")
                .then()
                .statusCode(code)
                .log().all()
                .extract().body().as(CourierLoginResponse.class);

        // Assert
        assertEquals(code, response.getCode());
        assertEquals(message, response.getMessage());
    }
}
