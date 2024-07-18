package stellarburgers.tests;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.*;
import stellarburgers.Check;
import stellarburgers.jsons.CreateUserRequestJson;
import stellarburgers.jsons.generators.CreateUserJsonGenerator;
import stellarburgers.jsons.generators.LoginUserJsonGenerator;
import stellarburgers.rests.UserRests;
import stellarburgers.DriverRule;
import stellarburgers.pages.LoginPage;
import stellarburgers.pages.MainPage;
import stellarburgers.pages.RegisterPage;

/** Проверь:
 * Успешную регистрацию.
 * Ошибку для некорректного пароля. Минимальный пароль — шесть символов.
 */

@DisplayName("Регистрация пользователя")
public class RegistrationTests {

    private static final CreateUserJsonGenerator USER_JSON = new CreateUserJsonGenerator();
    private static final UserRests USER_REST = new UserRests();
    private static final LoginUserJsonGenerator LOGIN_JSON = new LoginUserJsonGenerator();
    private static final Check CHECK = new Check();

    private static CreateUserRequestJson newUser;

    @ClassRule
    public static DriverRule driverRule = new DriverRule();

    @Before
    @Step("Генерация пользовательских данных, открытие главной страницы и открытие страницы логина")
    public void openPageAndNavigate() {
        newUser = USER_JSON.random();
        new MainPage(driverRule.getDriver())
                .openPage()
                .waitForLoadingPage()
                .clickEnterAccountButton();
        new LoginPage(driverRule.getDriver())
                .waitForLoadingPage()
                .clickRegisterLink();
    }

    @After
    @Step("Удаление пользователя через API")
    public void deleteUserIfCreated() {
        String accessToken;
        var newLogin = LOGIN_JSON.from(newUser);
        ValidatableResponse loginUserResponse = USER_REST.login(newLogin);
        accessToken = CHECK.extractAccessToken(loginUserResponse);

        if (accessToken != null) {
            ValidatableResponse creationResponse = USER_REST.delete(accessToken);
            CHECK.code202andSuccess(creationResponse);
            CHECK.userRemovedMessage(creationResponse);
        }
    }

    @Test
    @DisplayName("[+] Регистрация пользователя")
    public void registrationTest() {
        new RegisterPage(driverRule.getDriver())
                .waitForLoadingPage()
                .inputName(newUser.getName())
                .inputEmail(newUser.getEmail())
                .inputPassword(newUser.getPassword())
                .clickRegisterButton();
        new LoginPage(driverRule.getDriver())
                .waitForLoadingPage();
    }

    @Test
    @DisplayName("[–] Регистрация с некорректным паролем. Пароль: 123")
    public void registrationWrongPasswordTest() {
        new RegisterPage(driverRule.getDriver())
                .waitForLoadingPage()
                .inputName(newUser.getName())
                .inputEmail(newUser.getEmail())
                .inputPassword("123")
                .clickRegisterButton()
                .checkWrongPasswordWarning();
    }
}