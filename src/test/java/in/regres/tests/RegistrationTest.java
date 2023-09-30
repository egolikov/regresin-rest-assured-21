package in.regres.tests;

import in.regres.models.RegistrationBodyModel;
import in.regres.models.RegistrationErrorModel;
import in.regres.models.RegistrationResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static in.regres.specs.RegistrationSpec.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationTest {

    @DisplayName("Проверка успешной регистрации с Email и Password")
    @Test
    void successfulRegistrationTest() {

        RegistrationBodyModel regData = new RegistrationBodyModel();
        regData.setEmail("eve.holt@reqres.in");
        regData.setPassword("pistol");

        RegistrationResponseModel response = step("Отправляем запрос на успешную Регистрацию", () ->
                given(registrationRequestSpec)
                        .body(regData)
                        .when()
                        .post("/register")
                        .then()
                        .spec(registrationResponseSpec)
                        .extract().as(RegistrationResponseModel.class));

        step("Проверка ответа на запрос об успешной Регистрации", () -> {
            assertEquals(4, response.getId());
            assertEquals("QpwL5tke4Pnpja7X4", response.getToken());
        });
    }

    @Test
    @DisplayName("Проверка неспешной регистрации без Email")
    void registrationWithOutEmailTest() {

        RegistrationBodyModel noneEmailData = new RegistrationBodyModel();
        noneEmailData.setPassword("pistol");

        RegistrationErrorModel response = step("Отправляем запрос без Email на регистрацию", () ->
                given(registrationRequestSpec)
                        .body(noneEmailData)
                        .when()
                        .post("/register")
                        .then()
                        .spec(errorRegistrationResponseSpec)
                        .extract().as(RegistrationErrorModel.class));

        step("Проверка ответа с ошибкой на запрос Регистрации", () -> {
            assertEquals("Missing email or username", response.getError());
        });
    }

    @Test
    @DisplayName("Проверка неспешной регистрации без Password")
    void registrationWithOutPasswordTest() {

        RegistrationBodyModel nonePasswordData = new RegistrationBodyModel();
        nonePasswordData.setEmail("eve.holt@reqres.in");

        RegistrationErrorModel response = step("Отправляем запрос без Password на регистрацию", () ->
                given(registrationRequestSpec)
                        .body(nonePasswordData)
                        .when()
                        .post("/register")
                        .then()
                        .spec(errorRegistrationResponseSpec)
                        .extract().as(RegistrationErrorModel.class));

        step("Проверка ответа с ошибкой на запрос Регистрации", () -> {
            assertEquals("Missing password", response.getError());
        });
    }

    @Test
    @DisplayName("Проверка неуспешной регистрации с данными неизвестного пользователя")
    void undefinedUserRegistrationTest() {

        RegistrationBodyModel undefinedUserData = new RegistrationBodyModel();
        undefinedUserData.setEmail("egolikov@gmail.com");
        undefinedUserData.setPassword("gogogo");

        RegistrationErrorModel response = step("Отправляем запрос с неизвестным Юзером на регистрацию", () ->
                given(registrationRequestSpec)
                        .body(undefinedUserData)
                        .when()
                        .post("/register")
                        .then()
                        .spec(errorRegistrationResponseSpec)
                        .extract().as(RegistrationErrorModel.class));

        step("Проверка ответа с ошибкой на запрос Регистрации", () -> {
            assertEquals("Note: Only defined users succeed registration", response.getError());
        });
    }
}
