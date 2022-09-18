import io.qameta.allure.junit4.DisplayName;
import order.Order;
import order.OrderClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

public class OrderTest {
    Order order;
    OrderClient orderClient;
    private final String[] color = new String[] {"BLACK", "GREY"};

    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }

//    @After
//    public void teardown() {
//        if (courierId != 0) {
//            courierClient.delete(courierId)
//                    .statusCode(200);
//        }
//    }

    @Test
    @DisplayName("Создание заказа")
    public void orderCreation() {

        order = Order.getRandomOrder(color);

        int track = orderClient.create(order)
                .statusCode(201)
                .extract().path("track");

        assertNotEquals(0, track);
    }
}
