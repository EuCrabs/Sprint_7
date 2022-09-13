package TestMethods;

import Courier.*;
import TestData.TestData;

import static io.restassured.RestAssured.given;

public class Courier {

    public static void courierDeletion(String login) {

        CourierLoginRequest request = Methods.Courier.courierLoginRequest(login, TestData.password);

        CourierLoginResponse response = given()
                .header("Content-type", "application/json")
                .body(request)
                .when()
                .post("/api/v1/courier/login")
                .then().log().all()
                .extract().body().as(CourierLoginResponse.class);

        given()
                .header("Content-type", "application/json")
                .when()
                .delete("/api/v1/courier/" + response.getId().toString())
                .then().log().all();
    }

    public static void courierCreation(String login) {
        CourierCreationRequest request = Methods.Courier.courierCreationRequest(login, TestData.password, TestData.firstName);

        given()
                .header("Content-type", "application/json")
                .body(request)
                .when()
                .post("/api/v1/courier");
    }
}
