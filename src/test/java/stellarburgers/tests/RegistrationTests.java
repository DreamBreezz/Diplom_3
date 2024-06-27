package stellarburgers.tests;

import io.qameta.allure.junit4.DisplayName;
import lombok.extern.java.Log;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import stellarburgers.DriverRule;
import stellarburgers.pages.LoginPage;
import stellarburgers.pages.MainPage;
import stellarburgers.pages.RegisterPage;

/** Проверь:
 * Успешную регистрацию.
 * Ошибку для некорректного пароля. Минимальный пароль — шесть символов.
 */

public class RegistrationTests {

    @ClassRule
    public static DriverRule driverRule = new DriverRule();

    @BeforeClass
    // открыть страницу
    public static void openPageAndAcceptCookies() {
        new MainPage(driverRule.getDriver())
                .openPage();
    }
    @Test
    @DisplayName("[+] Регистрация пользователя")
    public void registrationTest() {
        new MainPage(driverRule.getDriver())
                .openPage()
                .waitForLoadingPage()
                .clickAccountButton();
        new LoginPage(driverRule.getDriver())
                .waitForLoadingPage()
                .clickRegisterLink();
        new RegisterPage(driverRule.getDriver())
                .waitForLoadingPage()
                .inputName()
                .inputEmail()
                .inputPassword()
                .clickRegisterButton();
        new LoginPage(driverRule.getDriver())
                .waitForLoadingPage();
    }

    @Test
    @DisplayName("[–] Регистрация с некорректным паролем")
    public void registrationWrongPasswordTest() {
        new MainPage(driverRule.getDriver())
                .openPage()
                .waitForLoadingPage()
                .clickEnterAccountButton();
        new LoginPage(driverRule.getDriver())
                .waitForLoadingPage()
                .clickRegisterLink();
        new RegisterPage(driverRule.getDriver())
                .waitForLoadingPage()
                .inputName()
                .inputEmail()
                .inputWrongPassword()
                .clickRegisterButton()
                .checkWrongPasswordWarning();
    }

    /////
}
