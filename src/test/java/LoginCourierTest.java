import courier.*;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;

public class LoginCourierTest {
    private int courierId;
    private final CourierActions client = new CourierActions();
    private final CourierAssertions check = new CourierAssertions();

    @After
    public void deleteCourier() {
        if (courierId > 0) {
            ValidatableResponse delete = client.delete(courierId);
            check.successfulDelete(delete);
        }
    }

    @Test //курьер может авторизоваться
    public void successfulAuthorizationCourier() {
        Courier courier = CourierGenerator.random();
        client.createNewCourier(courier);
        CourierCredentials courierCredentials = CourierCredentials.from(courier);
        ValidatableResponse loginResponse = client.LoginCourier(courierCredentials);
        courierId = check.assertSuccessfulLogin(loginResponse);
        assert courierId != 0;
    }

    @Test // курьер не может авторизоваться (нет логина)
    public void authorizationWithoutLogin() {
        Courier courier = CourierGenerator.withoutLogin();
        client.createNewCourier(courier);
        CourierCredentials courierCredentials = CourierCredentials.from(courier);
        ValidatableResponse loginResponse = client.LoginCourier(courierCredentials);
        check.assertBadRequestLogin(loginResponse);
    }

    @Test //курьер не может авторизоваться (нет пароля)
    public void requestWithoutPassword() {
        Courier courier = CourierGenerator.withoutPassword();
        client.createNewCourier(courier);
        CourierCredentials courierCredentials = CourierCredentials.from(courier);
        ValidatableResponse loginResponse = client.LoginCourier(courierCredentials);
        check.assertBadRequestLogin(loginResponse);
    }

    @Test //курьер не может авторизоваться (нет логина и пароля)
    public void authorizationNonExistentCourier() {
        Courier courier = CourierGenerator.random();
        CourierCredentials courierCredentials = CourierCredentials.from(courier);
        ValidatableResponse loginResponse = client.LoginCourier(courierCredentials);
        check.assertNotFoundLogin(loginResponse);
    }


}