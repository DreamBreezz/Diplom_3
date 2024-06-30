package stellarburgers.tests;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import stellarburgers.Check;
import stellarburgers.DriverRule;
import stellarburgers.jsons.CreateUserRequestJson;
import stellarburgers.jsons.generators.CreateUserJsonGenerator;
import stellarburgers.pages.MainPage;
import stellarburgers.pages.ProfilePage;
import stellarburgers.rests.UserRests;

/**
 *  * Проверь переход по клику на «Личный кабинет».
 *  * Проверь переход по клику на «Конструктор» и на логотип Stellar Burgers.
 *  * Проверь выход по кнопке «Выйти» в личном кабинете.
 *
 */
public class AuthorizedUserNavigationTests {
    private static final CreateUserJsonGenerator userJson = new CreateUserJsonGenerator();
    private static final UserRests userRest = new UserRests();
    private static final Check check = new Check();

    public static String accessToken;
    public static String refreshToken;
    private static boolean isUserCreated;

    @ClassRule
    public static DriverRule driverRule = new DriverRule();

    @Before
    @Step("Создание пользователя, логин и авторизация")
    public void createUserAndOpenLoginPage() {
        CreateUserRequestJson newUser = userJson.random();  // генерация json рандомного пользователя
        ValidatableResponse createUserResponse = userRest.create(newUser);  // создание пользователя через API,
        check.code201andSuccess(createUserResponse);  // чтобы не создавать через страницу регистрации
        isUserCreated = true;
        refreshToken = check.extractRefreshToken(createUserResponse);
        accessToken = check.extractAccessToken(createUserResponse);

        new MainPage(driverRule.getDriver())
                .openPage()  // сначала открытие страницы без параметров, т.к. в Local Storage нельзя записывать,
                .waitForLoadingPage()  // пока открыта страница data:.
                .setLocalStorage(refreshToken, accessToken)
                .refresh()
                .waitForLoadingPageAuthUser();
    }

    @After
    @Step("Удаление пользователя через API")
    public void deleteUser() {
        if (isUserCreated) {
            ValidatableResponse creationResponse = userRest.delete(accessToken);
            check.code202andSuccess(creationResponse);
            check.userRemovedMessage(creationResponse);
            accessToken = null;
            refreshToken = null;
            isUserCreated = false;
        }
    }

    @Test
    @DisplayName("Переход в личный кабинет")
    public void toProfileFromMainPage() {
        new MainPage(driverRule.getDriver())
                .clickAccountButton();
        new ProfilePage(driverRule.getDriver())
                .waitForLoadingPage();
    }
}
