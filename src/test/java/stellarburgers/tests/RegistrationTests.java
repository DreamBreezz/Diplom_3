package stellarburgers.tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import praktikum.jsons.CreateUserRequestJson;
import praktikum.jsons.generators.CreateUserJsonGenerator;
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
    private static CreateUserRequestJson newUser;
    public static boolean isUserCreated;

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

    public static boolean getCreated() {
        return isUserCreated;
    }

    public static CreateUserRequestJson getNewUser() {
        return newUser;
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
