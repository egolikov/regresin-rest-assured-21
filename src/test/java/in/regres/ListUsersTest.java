package in.regres;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class ListUsersTest {

    @Test
    void successfulFetchListUsersTest() {

        given()
                .log().uri()
                .log().method()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("page", is(2),
                        "per_page", is(6),
                        "total", is(12),
                        "total_pages", is(2),
                        "data[0].id", is(7),
                        "data[0].email", is("michael.lawson@reqres.in"),
                        "data[0].first_name", is("Michael"),
                        "data[0].last_name", is("Lawson"),
                        "data[0].avatar", is("https://reqres.in/img/faces/7-image.jpg"),
                        "data[1].id", is(8),
                        "data[1].email", is("lindsay.ferguson@reqres.in"),
                        "data[1].first_name", is("Lindsay"),
                        "data[1].last_name", is("Ferguson"),
                        "data[1].avatar", is("https://reqres.in/img/faces/8-image.jpg"),
                        "data[2].id", is(9),
                        "data[2].email", is("tobias.funke@reqres.in"),
                        "data[2].first_name", is("Tobias"),
                        "data[2].last_name", is("Funke"),
                        "data[2].avatar", is("https://reqres.in/img/faces/9-image.jpg"),
                        "data[3].id", is(10),
                        "data[3].email", is("byron.fields@reqres.in"),
                        "data[3].first_name", is("Byron"),
                        "data[3].last_name", is("Fields"),
                        "data[3].avatar", is("https://reqres.in/img/faces/10-image.jpg"),
                        "data[4].id", is(11),
                        "data[4].email", is("george.edwards@reqres.in"),
                        "data[4].first_name", is("George"),
                        "data[4].last_name", is("Edwards"),
                        "data[4].avatar", is("https://reqres.in/img/faces/11-image.jpg"),
                        "data[5].id", is(12),
                        "data[5].email", is("rachel.howell@reqres.in"),
                        "data[5].first_name", is("Rachel"),
                        "data[5].last_name", is("Howell"),
                        "data[5].avatar", is("https://reqres.in/img/faces/12-image.jpg"),
                        "support.url", is("https://reqres.in/#support-heading"),
                        "support.text", is("To keep ReqRes free, contributions towards server costs are appreciated!")
                );
    }
}
