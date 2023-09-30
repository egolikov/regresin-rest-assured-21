package in.regres.tests;

import in.regres.models.ListResourcesDataResponseModel;
import in.regres.models.ListResourcesResponseModel;
import in.regres.models.ListResourcesSupportResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static in.regres.specs.ListResourcesSpec.listResourcesRequestSpec;
import static in.regres.specs.ListResourcesSpec.listResourcesResponseSpec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListResourcesTest {

    @Test
    @DisplayName("Проверка успешного получения списка ресурсов")
    void successfulFetchListResources() {

        ListResourcesResponseModel response = step("Получение списка Ресурсов", () ->
                given()
                        .spec(listResourcesRequestSpec)
                        .when()
                        .get("/unknown")
                        .then()
                        .spec(listResourcesResponseSpec)
                        .extract()
                        .as(ListResourcesResponseModel.class));

        step("Проверка информации о странице в ответе", () -> {
            assertEquals(1, response.getPage());
            assertEquals(6, response.getPerPage());
            assertEquals(12, response.getTotal());
            assertEquals(2, response.getTotalPages());
        });

        step("Проверка информации о первом объекте в ответе", () -> {
            List<ListResourcesDataResponseModel> data = response.getData();
            assertEquals(1, data.get(0).getId());
            assertEquals("cerulean", data.get(0).getName());
            assertEquals(2000, data.get(0).getYear());
            assertEquals("#98B2D1", data.get(0).getColor());
            assertEquals("15-4020", data.get(0).getPantoneValue());
        });

        step("Проверка информации о втором объекте в ответе", () -> {
            List<ListResourcesDataResponseModel> data = response.getData();
            assertEquals(2, data.get(1).getId());
            assertEquals("fuchsia rose", data.get(1).getName());
            assertEquals(2001, data.get(1).getYear());
            assertEquals("#C74375", data.get(1).getColor());
            assertEquals("17-2031", data.get(1).getPantoneValue());
        });

        step("Проверка информации о третьем объекте в ответе", () -> {
            List<ListResourcesDataResponseModel> data = response.getData();
            assertEquals(3, data.get(2).getId());
            assertEquals("true red", data.get(2).getName());
            assertEquals(2002, data.get(2).getYear());
            assertEquals("#BF1932", data.get(2).getColor());
            assertEquals("19-1664", data.get(2).getPantoneValue());
        });

        step("Проверка информации о четвертом объекте в ответе", () -> {
            List<ListResourcesDataResponseModel> data = response.getData();
            assertEquals(4, data.get(3).getId());
            assertEquals("aqua sky", data.get(3).getName());
            assertEquals(2003, data.get(3).getYear());
            assertEquals("#7BC4C4", data.get(3).getColor());
            assertEquals("14-4811", data.get(3).getPantoneValue());
        });

        step("Проверка информации о пятом объекте в ответе", () -> {
            List<ListResourcesDataResponseModel> data = response.getData();
            assertEquals(5, data.get(4).getId());
            assertEquals("tigerlily", data.get(4).getName());
            assertEquals(2004, data.get(4).getYear());
            assertEquals("#E2583E", data.get(4).getColor());
            assertEquals("17-1456", data.get(4).getPantoneValue());
        });

        step("Проверка информации о шестом объекте в ответе", () -> {
            List<ListResourcesDataResponseModel> data = response.getData();
            assertEquals(6, data.get(5).getId());
            assertEquals("blue turquoise", data.get(5).getName());
            assertEquals(2005, data.get(5).getYear());
            assertEquals("#53B0AE", data.get(5).getColor());
            assertEquals("15-5217", data.get(5).getPantoneValue());
        });

        step("Проверка информации о поддержке в ответе", () -> {
            ListResourcesSupportResponseModel support = response.getSupport();
            assertEquals("https://reqres.in/#support-heading", support.getUrl());
            assertEquals("To keep ReqRes free, contributions towards server costs are appreciated!", support.getText());
        });


    }
}
