package in.regres;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class ListResourcesTest extends BaseTest {

    @Test
    void successfulFetchListResourceTest() {

        given()
                .log().uri()
                .log().method()
                .when()
                .get("/unknown")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("page", is(1),
                        "per_page", is(6),
                        "total", is(12),
                        "total_pages", is(2),
                        "data[0].id", is(1),
                        "data[0].name", is("cerulean"),
                        "data[0].year", is(2000),
                        "data[0].color", is("#98B2D1"),
                        "data[0].pantone_value", is("15-4020"),
                        "data[1].id", is(2),
                        "data[1].name", is("fuchsia rose"),
                        "data[1].year", is(2001),
                        "data[1].color", is("#C74375"),
                        "data[1].pantone_value", is("17-2031"),
                        "data[2].id", is(3),
                        "data[2].name", is("true red"),
                        "data[2].year", is(2002),
                        "data[2].color", is("#BF1932"),
                        "data[2].pantone_value", is("19-1664"),
                        "data[3].id", is(4),
                        "data[3].name", is("aqua sky"),
                        "data[3].year", is(2003),
                        "data[3].color", is("#7BC4C4"),
                        "data[3].pantone_value", is("14-4811"),
                        "data[4].id", is(5),
                        "data[4].name", is("tigerlily"),
                        "data[4].year", is(2004),
                        "data[4].color", is("#E2583E"),
                        "data[4].pantone_value", is("17-1456"),
                        "data[5].id", is(6),
                        "data[5].name", is("blue turquoise"),
                        "data[5].year", is(2005),
                        "data[5].color", is("#53B0AE"),
                        "data[5].pantone_value", is("15-5217"),
                        "support.url", is("https://reqres.in/#support-heading"),
                        "support.text", is("To keep ReqRes free, contributions towards server costs are appreciated!")
                );
    }
}
