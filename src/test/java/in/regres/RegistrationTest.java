package in.regres;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class RegistrationTest extends BaseTest {

    @Test
    void successfulRegistrationTest() {
        String regData = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\" }";

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(regData)
                .when()
                .post("/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("id", is(4),
                        "token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void registrationWithOutEmailTest() {
        String noneEmailData = "{ \"password\": \"pistol\" }";

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(noneEmailData)
                .when()
                .post("/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing email or username"));
    }

    @Test
    void registrationWithOutPasswordTest() {
        String nonePasswordData = "{ \"email\": \"eve.holt@reqres.in\" }";

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(nonePasswordData)
                .when()
                .post("/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    void undefinedUserRegistrationTest() {
        String undefinedUserData = "{ \"email\": \"egolikov@gmail.com\", \"password\": \"gogogo\" }";

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(undefinedUserData)
                .when()
                .post("/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Note: Only defined users succeed registration"));
    }
}
