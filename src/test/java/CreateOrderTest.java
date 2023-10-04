import order.OrderOut;
import order.OrderAction;
import order.OrderAssertions;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class CreateOrderTest {
    private final OrderAction orderAction = new OrderAction();
    private final OrderAssertions check = new OrderAssertions();
    OrderOut orderOut;

    public CreateOrderTest(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment, String[] color) {
        this.orderOut = new OrderOut(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
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

    @Test
    public void createOrder() {
        ValidatableResponse response = orderAction.createNewOrder(orderOut);
        int idOrder = check.assertSuccessfulOrder(response);
        assert idOrder != 0;
    }
}
