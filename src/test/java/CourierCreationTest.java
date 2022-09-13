import Courier.*;
import Methods.*;
import TestData.TestData;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CourierCreationTest {

    public String login;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
        login = Courier.userLoginGenerator();
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
    public void isCourierCreated() {

        Integer code = 201;
        CourierCreationRequest request = Courier.courierCreationRequest(login, TestData.password, TestData.firstName);

        CourierCreationResponse response = given()
                .header("Content-type", "application/json")
                .body(request)
                .when()
                .post("/api/v1/courier")
                .then()
                .statusCode(code)
                .log().all()
                .extract().body().as(CourierCreationResponse.class);

        // Assert
        assertTrue(response.getOk());
    }

    @Test
    public void cantCreateTwoSimilarCouriers() {

        Integer code = 409;
        String message = "Этот логин уже используется. Попробуйте другой.";
        CourierCreationRequest request = Courier.courierCreationRequest(login, TestData.password, TestData.firstName);

        given()
                .header("Content-type", "application/json")
                .body(request)
                .when()
                .post("/api/v1/courier");
        CourierCreationResponse response = given()
                .header("Content-type", "application/json")
                .body(request)
                .when()
                .post("/api/v1/courier")
                .then()
                .statusCode(code)
                .log().all()
                .extract().body().as(CourierCreationResponse.class);

        // Assert
        assertEquals(code, response.getCode());
        assertEquals(message, response.getMessage());
    }

    @Test
    public void checkRequiredPasswordFields() {

        Integer code = 400;
        String message = "Недостаточно данных для создания учетной записи";
        CourierCreationRequest request = Courier.courierCreationWithoutPasswordRequest(login, TestData.firstName);

        CourierCreationResponse response = given()
                .header("Content-type", "application/json")
                .body(request)
                .when()
                .post("/api/v1/courier")
                .then()
                .statusCode(code)
                .log().all()
                .extract().body().as(CourierCreationResponse.class);

        // Assert
        assertEquals(code, response.getCode());
        assertEquals(message, response.getMessage());
    }
}
