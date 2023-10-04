import courier.*;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;

public class СreateСourierTest {
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

    @Test //курьера можно создать
    public void successfulAccountCreation() {
        Courier courier = CourierGenerator.random();

        ValidatableResponse response = client.createNewCourier(courier);
        check.assertSuccessfulCreation(response);

        CourierCredentials courierCredentials = CourierCredentials.from(courier);

        ValidatableResponse loginResponse = client.LoginCourier(courierCredentials);
        courierId = check.assertSuccessfulLogin(loginResponse);

        assert courierId != 0;
    }

    @Test //нельзя создать двух одинаковых курьеров
    public void requestWithDuplicateLogin() {
        Courier courier = CourierGenerator.random();

        ValidatableResponse response = client.createNewCourier(courier);
        check.assertSuccessfulCreation(response);

        CourierCredentials courierCredentials = CourierCredentials.from(courier);
        ValidatableResponse loginResponse = client.LoginCourier(courierCredentials);
        courierId = check.assertSuccessfulLogin(loginResponse);

        ValidatableResponse duplicateResponse = client.createNewCourier(courier);
        check.assertConflictCreation(duplicateResponse);
    }

    @Test //чтобы создать курьера, нужно передать в ручку все обязательные поля (нет логина)
    public void requestWithoutLogin() {
        Courier courier = CourierGenerator.withoutLogin();
        ValidatableResponse response = client.createNewCourier(courier);
        check.assertBadRequestCreation(response);
    }

    @Test //чтобы создать курьера, нужно передать в ручку все обязательные поля (нет пароля)
    public void requestWithoutPassword() {
        Courier courier = CourierGenerator.withoutPassword();
        ValidatableResponse response = client.createNewCourier(courier);
        check.assertBadRequestCreation(response);
    }
}
