import io.qameta.allure.junit4.DisplayName;
import order.Order;
import order.OrderClient;
import order.OrderTestData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.junit.Assert.assertNotEquals;

@RunWith(Parameterized.class)
public class OrderTest {
    Order order;
    OrderClient orderClient;
    private List<String> color;

    public OrderTest(List<String> color) {
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] getColor() {
        return new Object[][] {
                {OrderTestData.getEmptyList()},
                {OrderTestData.getBlackOnly()},
                {OrderTestData.getBothColor()}
        };
    }

    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }

    @Test
    @DisplayName("Создание заказа")
    public void orderCreation() {

        order = Order.getRandomOrder(color);

        int trackOrder = orderClient.create(order)
                .statusCode(201)
                .extract().path("track");

        assertNotEquals(0, trackOrder);
    }
}
