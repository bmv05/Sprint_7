package courier;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Spec {
    public static final String BASE_PATH = "/api/v1";
    public static final String BASE_URI = "https://qa-scooter.praktikum-services.ru/";

    public RequestSpecification spec() {
        return given().log().all()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .basePath(BASE_PATH);
    }
}
