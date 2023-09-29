package in.regres.tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class DeletePersonTest extends BaseTest {

    @Test
    void successfulDeletePersonTest() {

        given()
                .log().uri()
                .log().method()
                .when()
                .delete("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }
}
