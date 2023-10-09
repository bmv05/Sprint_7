package order;

import io.restassured.response.ValidatableResponse;

import java.net.HttpURLConnection;

public class OrderAssertions {
    public int assertSuccessfulOrder(ValidatableResponse orderResponse) {

        int idOrder = orderResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_CREATED)
                .extract()
                .path("track");
        return idOrder;
    }

    public void assertSuccessfulGetListOrders(ValidatableResponse orderResponse) {
        orderResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK);
    }
}
