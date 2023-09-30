package in.regres.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static in.regres.specs.DeletePersonSpec.deletePersonRequestSpec;
import static in.regres.specs.DeletePersonSpec.deletePersonResponseSpec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

public class DeletePersonTest {

    @Test
    @DisplayName("Проверка успешного удаления сотрудника")
    void successfulDeletePersonTest() {

        step("Удаление сотрудника", () -> {
            given()
                    .spec(deletePersonRequestSpec)
                    .delete("/users/2")
                    .then()
                    .spec(deletePersonResponseSpec);
        });
    }
}
