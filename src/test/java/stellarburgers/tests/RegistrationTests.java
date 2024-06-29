package stellarburgers.tests;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.*;
import praktikum.Check;
import praktikum.jsons.CreateUserRequestJson;
import praktikum.jsons.generators.CreateUserJsonGenerator;
import praktikum.jsons.generators.LoginUserJsonGenerator;
import praktikum.rests.UserRests;
import stellarburgers.DriverRule;
import stellarburgers.pages.LoginPage;
import stellarburgers.pages.MainPage;
import stellarburgers.pages.RegisterPage;

/** Проверь:
 * Успешную регистрацию.
 * Ошибку для некорректного пароля. Минимальный пароль — шесть символов.
 */

public class RegistrationTests {

    private static final CreateUserJsonGenerator userJson = new CreateUserJsonGenerator();
    private static final UserRests userRest = new UserRests();
    private static final LoginUserJsonGenerator loginJson = new LoginUserJsonGenerator();
    private static final Check check = new Check();

    static CreateUserRequestJson newUser;
    private static boolean isUserCreated;

    @ClassRule
    public static DriverRule driverRule = new DriverRule();

    @Before
    public void openPageAndNavigate() {
        newUser = userJson.random();
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
        if (isUserCreated) {
            var newLogin = loginJson.from(newUser);
            ValidatableResponse loginUserResponse = userRest.login(newLogin);
            String accessToken = check.extractAccessToken(loginUserResponse);

            ValidatableResponse creationResponse = userRest.delete(accessToken);
            check.code202andSuccess(creationResponse);
            check.userRemovedMessage(creationResponse);
            accessToken = null;
            isUserCreated = false;
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
        isUserCreated = true;
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
