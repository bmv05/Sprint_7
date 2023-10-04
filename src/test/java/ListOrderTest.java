import io.restassured.response.Response;
import order.OrderAssertions;
import order.OrderOut;
import order.OrderAction;
import order.OrdersIn;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;


public class ListOrderTest {

    private final OrderAction orderAction = new OrderAction();
    private final OrderAssertions check = new OrderAssertions();

    protected Object[][] getOrderTestData() {
        return new Object[][]{
                {
                        "Ника",
                        "Вишнева",
                        "Проспект мира, д.28",
                        "Электросила",
                        "89323454334",
                        4,
                        "2020-06-06",
                        "пусто",
                        new String[]{"BLACK"} //необязательное, множественное
                },
                {
                        "Кристина",
                        "Клубника",
                        "Ворошилова, д.33",
                        "Автово",
                        "89323454334",
                        6,
                        "2022-12-23",
                        "",
                        new String[]{"BLACK", "GREY"} //необязательное, множественное
                },
                {
                        "Нина",
                        "Клубника",
                        "Ворошилова, д.33",
                        "Автово",
                        "89323454334",
                        6,
                        "2022-07-23",
                        "пусто",
                        new String[]{} //необязательное, множественное
                },
        };
    }

    public void fillOrder() {
        Object[][] orders = this.getOrderTestData();
        for (int i = 0; i <= orders.length - 1; i++) {
            OrderOut orderOut = new OrderOut(orders[i]);
            orderAction.createNewOrder(orderOut);
        }
    }

    @Test
    public void getListOrder() {
        this.fillOrder();
        Response response = orderAction.getListOrder();
        OrdersIn orders = response
                .body()
                .as(OrdersIn.class);
        assertEquals(true, orders.getOrders().get(0).getId() > 0);
        check.assertSuccessfulGetListOrders(response.then());
    }
}