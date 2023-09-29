package in.regres.tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.empty;

public class CreatePersonTest extends BaseTest {

    @Test
    void successfulCreatePersonTest() {
        String createPersonData = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(createPersonData)
                .when()
                .post("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"),
                        "job", is("leader"),
                        "id", is(not(empty())),
                        "createdAt", is(not(empty())));
    }

    @Test
    void createPersonWithOutNameTest() {
        String noneNameData = "{ \"job\": \"leader\" }";

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(noneNameData)
                .when()
                .post("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("job", is("leader"),
                        "id", is(not(empty())),
                        "createdAt", is(not(empty())));
    }

    @Test
    void createPersonWithOutJobTest() {
        String noneJobData = "{ \"name\": \"morpheus\" }";

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(noneJobData)
                .when()
                .post("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"),
                        "id", is(not(empty())),
                        "createdAt", is(not(empty())));
    }
}
