package in.regres.tests;

import in.regres.models.AuthorizationBodyModel;
import in.regres.models.AuthorizationErrorModel;
import in.regres.models.AuthorizationResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static in.regres.specs.AuthorizationSpec.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthorizationTest {

    @Test
    @DisplayName("Проверка успешной авторизации с Email и Password")
    void successfulAuthorizationTest() {

        AuthorizationBodyModel authData = new AuthorizationBodyModel();
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("cityslicka");

        AuthorizationResponseModel response = step("Отправляем запрос на успешную Авторизацию", () ->
                given(authorizationRequestSpec)
                        .body(authData)
                        .when()
                        .post("/login")
                        .then()
                        .spec(authorizationResponseSpec)
                        .extract().as(AuthorizationResponseModel.class));

        step("Проверка ответа на запрос об успешной Авторизации", () ->
                assertEquals("QpwL5tke4Pnpja7X4", response.getToken()));
    }

    @Test
    @DisplayName("Проверка неуспешной авторизации без Email")
    void authorizationWithOutEmailTest() {

        AuthorizationBodyModel noneEmailData = new AuthorizationBodyModel();
        noneEmailData.setPassword("cityslicka");

        AuthorizationErrorModel response = step("Отправляем запрос без Email на авторизацию", () ->
                given(authorizationRequestSpec)
                        .body(noneEmailData)
                        .when()
                        .post("/login")
                        .then()
                        .spec(errorAuthorizationResponseSpec)
                        .extract().as(AuthorizationErrorModel.class));

        step("Проверка ответа с ошибкой на запрос Авторизации", () ->
                assertEquals("Missing email or username", response.getError()));
    }

    @Test
    @DisplayName("Проверка неуспешной авторизации без Password")
    void authorizationWithOutPasswordTest() {

        AuthorizationBodyModel nonePasswordData = new AuthorizationBodyModel();
        nonePasswordData.setEmail("eve.holt@reqres.in");

        AuthorizationErrorModel response = step("Отправляем запрос без Password на авторизацию", () ->
                given(authorizationRequestSpec)
                        .body(nonePasswordData)
                        .when()
                        .post("/login")
                        .then()
                        .spec(errorAuthorizationResponseSpec)
                        .extract().as(AuthorizationErrorModel.class));

        step("Проверка ответа с ошибкой на запрос Авторизации", () ->
                assertEquals("Missing password", response.getError()));
    }

    @Test
    @DisplayName("Проверка неуспешной авторизации с данными неизвестного пользователя")
    void undefinedUserAuthorizationTest() {

        AuthorizationBodyModel undefinedUserData = new AuthorizationBodyModel();
        undefinedUserData.setEmail("egolikov@gmail.com");
        undefinedUserData.setPassword("gogogo");

        AuthorizationErrorModel response = step("Отправляем запрос с неизвестным пользователем на авторизацию", () ->
                given(authorizationRequestSpec)
                        .body(undefinedUserData)
                        .when()
                        .post("/login")
                        .then()
                        .spec(errorAuthorizationResponseSpec)
                        .extract().as(AuthorizationErrorModel.class));

        step("Проверка ответа с ошибкой на запрос Авторизации", () ->
                assertEquals("user not found", response.getError()));
    }
}
