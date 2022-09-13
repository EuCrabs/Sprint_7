import Courier.CourierDeletionResponse;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CourierDeletionTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    @Test
    public void deleteCourier() {
        CourierDeletionResponse response = given()
                .header("Content-type", "application/json")
                .queryParam( "id","104945")
                .when()
                .delete("/api/v1/courier")
                .then().log().all()
                .extract().body().as(CourierDeletionResponse.class);
    }
}
