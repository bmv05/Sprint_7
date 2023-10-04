package order;

import courier.Spec;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class OrderAction extends Spec {
    public static final String ORDER_PATH = "/orders";

    public ValidatableResponse createNewOrder(OrderOut orderOut) {
        return spec()
                .body(orderOut)
                .when()
                .post(ORDER_PATH)
                .then().log().all();
    }

    public Response getListOrder() {
        return spec()
                .when()
                .get(ORDER_PATH);
    }
}


