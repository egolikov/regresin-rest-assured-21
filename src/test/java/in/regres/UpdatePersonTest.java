package in.regres;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.empty;

public class UpdatePersonTest extends BaseTest {

    @Test
    void successfulUpdatePersonWithPutMethodTest() {
        String updatePersonData = "{ \"name\": \"mike\", \"job\": \"developer\" }";

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(updatePersonData)
                .when()
                .put("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("mike"),
                        "job", is("developer"),
                        "createdAt", is(not(empty())));
    }

    @Test
    void successfulUpdatePersonWithPatchMethodTest() {
        String updatePersonData = "{ \"name\": \"oleg\", \"job\": \"designer\" }";

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(updatePersonData)
                .when()
                .patch("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("oleg"),
                        "job", is("designer"),
                        "createdAt", is(not(empty())));
    }
}
