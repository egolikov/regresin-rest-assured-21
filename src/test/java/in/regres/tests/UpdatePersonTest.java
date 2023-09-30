package in.regres.tests;

import in.regres.models.UpdatePersonBodyModel;
import in.regres.models.UpdatePersonResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static in.regres.specs.UpdatePersonSpec.updatePersonRequestSpec;
import static in.regres.specs.UpdatePersonSpec.updatePersonResponseSpec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UpdatePersonTest {

    @Test
    @DisplayName("Проверка успешного обновления данных пользователя через метод PUT")
    void successfulUpdatePersonWithPutMethodTest() {

        UpdatePersonBodyModel updatePersonData = new UpdatePersonBodyModel();
        updatePersonData.setName("mike");
        updatePersonData.setJob("developer");

        UpdatePersonResponseModel response = step("Обновление Имени и Должности сотрудника", () ->
                given(updatePersonRequestSpec)
                        .body(updatePersonData)
                        .when()
                        .put("/users/2")
                        .then()
                        .spec(updatePersonResponseSpec)
                        .extract().as(UpdatePersonResponseModel.class));

        step("Проверка ответа на запрос об успешном обновлении сотрудника", () -> {
            assertEquals("mike", response.getName());
            assertEquals("developer", response.getJob());
            assertNotNull(response.getUpdatedAt());
        });
    }

    @Test
    @DisplayName("Проверка успешного обновления данных пользователя через метод PATCH")
    void successfulUpdatePersonWithPatchMethodTest() {

        UpdatePersonBodyModel updatePersonData = new UpdatePersonBodyModel();
        updatePersonData.setName("oleg");
        updatePersonData.setJob("designer");

        UpdatePersonResponseModel response = step("Обновление Имени и Должности сотрудника", () ->
                given(updatePersonRequestSpec)
                        .body(updatePersonData)
                        .when()
                        .patch("/users/2")
                        .then()
                        .spec(updatePersonResponseSpec)
                        .extract().as(UpdatePersonResponseModel.class));

        step("Проверка ответа на запрос об успешном обновлении сотрудника", () -> {
            assertEquals("oleg", response.getName());
            assertEquals("designer", response.getJob());
            assertNotNull(response.getUpdatedAt());
        });
    }
}
