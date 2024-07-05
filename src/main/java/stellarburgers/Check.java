package stellarburgers;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static java.net.HttpURLConnection.*;
import static org.hamcrest.Matchers.is;
import static stellarburgers.Constants.*;

public class Check {

    @Step("Код ответа: 200 OK")
    public void code201andSuccess(ValidatableResponse response) {
        response.assertThat().statusCode(HTTP_OK)
                .body("success", is(true));
    }

    @Step("Сохранение токена авторизации")
    public String extractAccessToken(ValidatableResponse response) {
        return response.extract().path("accessToken");
    }

    @Step("Сохранение токена авторизации")
    public String extractRefreshToken(ValidatableResponse response) {
        return response.extract().path("refreshToken");
    }

    @Step("Код ответа: 202 ACCEPTED")
    public void code202andSuccess(ValidatableResponse response) {
        response.assertThat().statusCode(HTTP_ACCEPTED)
                .body("success", is(true));
    }

    @Step("Текст ответа: пользователь успешно удалён")
    public void userRemovedMessage(ValidatableResponse response) {
        response.body("message", is(USER_DELETED));
    }

}