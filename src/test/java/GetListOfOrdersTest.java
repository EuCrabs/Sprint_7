import io.qameta.allure.junit4.DisplayName;
import order.OrderClient;
import order.OrderResponse;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class GetListOfOrdersTest {
    OrderClient orderClient;

    @Before
    public void setup() {
        orderClient = new OrderClient();
    }

    @Test
    @DisplayName("Проверка что возвращается список всех заказов")
    public void getListOfOrders() {
        List<OrderResponse> listOfOrders = orderClient.getOrders()
                .statusCode(200)
                .extract().path("orders");

        assertTrue(listOfOrders.size() > 0);
    }
}
