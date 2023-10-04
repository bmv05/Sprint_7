package courier;

import io.restassured.response.ValidatableResponse;

import java.util.Map;

public class CourierActions extends Spec {

    public static final String COURIER_PATH = "/courier";


    public ValidatableResponse createNewCourier(Courier courier) {
        return spec()
                .body(courier)
                .when()
                .post(COURIER_PATH)
                .then().log().all();
    }

    public ValidatableResponse LoginCourier(CourierCredentials courierCredentials) {
        return spec()
                .body(courierCredentials)
                .when()
                .post(COURIER_PATH + "/login")
                .then().log().all();
    }

    public ValidatableResponse delete(int courierId) {
        return spec()
                .body(Map.of("id", courierId))
                .when()
                .delete(COURIER_PATH + "/" + courierId)
                .then().log().all();
    }
}
