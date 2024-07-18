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
import stellarburgers.pages.LoginPage;
import stellarburgers.pages.MainPage;
import stellarburgers.pages.ProfilePage;
import stellarburgers.rests.UserRests;

/**
 * Проверь переход по клику на «Личный кабинет».
 * Проверь переход по клику на «Конструктор» и на логотип Stellar Burgers.
 * Проверь выход по кнопке «Выйти» в личном кабинете.
 */

@DisplayName("Навигация по страницам – авторизованным пользователем")
public class AuthorizedUserNavigationTests {
    private static final CreateUserJsonGenerator USER_JSON = new CreateUserJsonGenerator();
    private static final UserRests USER_REST = new UserRests();
    private static final Check CHECK = new Check();

    public static String accessToken;
    public static String refreshToken;

    @ClassRule
    public static DriverRule driverRule = new DriverRule();

    @Before
    @Step("Создание пользователя через API, логин и авторизация")
    public void createUserAndOpenLoginPage() {
        CreateUserRequestJson newUser = USER_JSON.random();  // генерация json рандомного пользователя
        ValidatableResponse createUserResponse = USER_REST.create(newUser);  // создание пользователя через API,
        CHECK.code201andSuccess(createUserResponse);                  // чтобы не создавать через страницу регистрации
        refreshToken = CHECK.extractRefreshToken(createUserResponse);
        accessToken = CHECK.extractAccessToken(createUserResponse);

        new MainPage(driverRule.getDriver())
                .openPage()            // сначала открытие страницы без параметров, т.к. в Local Storage
                .waitForLoadingPage()  // нельзя записывать, пока открыта страница data:,
                .setTokensToLocalStorage(refreshToken, accessToken)
                .refresh()
                .waitForLoadingPageAuthUser();
    }

    @After
    @Step("Удаление пользователя через API")
    public void deleteUser() {
        ValidatableResponse creationResponse = USER_REST.delete(accessToken);
        CHECK.code202andSuccess(creationResponse);
        CHECK.userRemovedMessage(creationResponse);
        accessToken = null;
        refreshToken = null;
    }

    @Test
    @DisplayName("Переход в личный кабинет")
    public void toProfileFromMainPage() {
        new MainPage(driverRule.getDriver())
                .clickAccountButton();
        new ProfilePage(driverRule.getDriver())
                .waitForLoadingPage();
    }

    @Test
    @DisplayName("Переход на главную страницу по кнопке 'Конструктор'")
    public void toMainPageFromProfileViaConstructorButton() {
        toProfileFromMainPage();
        new ProfilePage(driverRule.getDriver())
                .clickConstructorButton();
        new MainPage(driverRule.getDriver())
                .waitForLoadingPageAuthUser();
    }

    @Test
    @DisplayName("Переход на главную страницу по клику на логотип")
    public void toMainPageFromProfileViaLogo() {
        toProfileFromMainPage();
        new ProfilePage(driverRule.getDriver())
                .clickLogo();
        new MainPage(driverRule.getDriver())
                .waitForLoadingPageAuthUser();
    }

    @Test
    @DisplayName("Выход по кнопке 'Выход'")
    public void logoutViaLogoutButton() {
        toProfileFromMainPage();
        new ProfilePage(driverRule.getDriver())
                .clickLogoutLink();
        new LoginPage(driverRule.getDriver())
                .waitForLoadingPage();
    }
}
