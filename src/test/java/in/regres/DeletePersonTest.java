package in.regres;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class DeletePersonTest {

    @Test
    void successfulDeletePersonTest() {

        given()
                .log().uri()
                .log().method()
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }
}
