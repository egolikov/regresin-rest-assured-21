package in.regres.tests;

import in.regres.models.CreatePersonBodyModel;
import in.regres.models.CreatePersonResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static in.regres.specs.CreatePersonSpec.createPersonRequestSpec;
import static in.regres.specs.CreatePersonSpec.createPersonResponseSpec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class CreatePersonTest {

    @Test
    @DisplayName("Проверка успешного создания сотрудника с Name и Job")
    void successfulCreatePersonTest() {

        CreatePersonBodyModel createPersonData = new CreatePersonBodyModel();
        createPersonData.setName("morpheus");
        createPersonData.setJob("leader");

        CreatePersonResponseModel response = step("Создание сотрудника с Именем и Должностью", () ->
                given(createPersonRequestSpec)
                        .body(createPersonData)
                        .when()
                        .post("/users")
                        .then()
                        .spec(createPersonResponseSpec)
                        .extract().as(CreatePersonResponseModel.class));

        step("Проверка ответа на запрос об успешном создании сотрудника", () -> {
            assertEquals("morpheus", response.getName());
            assertEquals("leader", response.getJob());
            assertNotNull(response.getId());
            assertNotNull(response.getCreatedAt());
        });
    }

    @Test
    @DisplayName("Проверка успешного создания сотрудника без Name")
    void createPersonWithOutNameTest() {

        CreatePersonBodyModel noneNameData = new CreatePersonBodyModel();
        noneNameData.setJob("leader");

        CreatePersonResponseModel response = step("Создание сотрудника без Имени", () ->
                given(createPersonRequestSpec)
                        .body(noneNameData)
                        .when()
                        .post("/users")
                        .then()
                        .spec(createPersonResponseSpec)
                        .extract().as(CreatePersonResponseModel.class));

        step("Проверка ответа на запрос об успешном создании сотрудника без Имени", () -> {
            assertNull(response.getName());
            assertEquals("leader", response.getJob());
            assertNotNull(response.getId());
            assertNotNull(response.getCreatedAt());
        });
    }

    @Test
    @DisplayName("Проверка успешного создания сотрудника без Job")
    void createPersonWithOutJobTest() {

        CreatePersonBodyModel noneJobData = new CreatePersonBodyModel();
        noneJobData.setName("morpheus");

        CreatePersonResponseModel response = step("Создание сотрудника без Работы", () ->
                given(createPersonRequestSpec)
                        .body(noneJobData)
                        .when()
                        .post("/users")
                        .then()
                        .spec(createPersonResponseSpec)
                        .extract().as(CreatePersonResponseModel.class));

        step("Проверка ответа на запрос об успешном создании сотрудника без Работы", () -> {
            assertEquals("morpheus", response.getName());
            assertNull(response.getJob());
            assertNotNull(response.getId());
            assertNotNull(response.getCreatedAt());
        });
    }
}
