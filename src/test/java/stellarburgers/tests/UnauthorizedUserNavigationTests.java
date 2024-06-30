package stellarburgers.tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.ClassRule;
import org.junit.Test;
import stellarburgers.DriverRule;
import stellarburgers.pages.ForgotPasswordPage;
import stellarburgers.pages.LoginPage;
import stellarburgers.pages.MainPage;
import stellarburgers.pages.RegisterPage;

/**
 * вход по кнопке «Войти в аккаунт» на главной,
 * вход через кнопку «Личный кабинет»,
 * вход через кнопку в форме регистрации,
 * вход через кнопку в форме восстановления пароля.
 */

public class UnauthorizedUserNavigationTests {
    @ClassRule
    public static DriverRule driverRule = new DriverRule();

    @Test
    @DisplayName("Переход по кнопке 'Войти в аккаунт' на главной")
    public void toLoginPageFromMainPageViaEnterAccountButton() {
        new MainPage(driverRule.getDriver())
                .openPage()
                .waitForLoadingPage()
                .clickEnterAccountButton();
        new LoginPage(driverRule.getDriver())
                .waitForLoadingPage();
    }

    @Test
    @DisplayName("Переход по кнопке 'Личный кабинет' на главной")
    public void toLoginPageFromMainPageViaAccountButton() {
        new MainPage(driverRule.getDriver())
                .openPage()
                .waitForLoadingPage()
                .clickAccountButton();
        new LoginPage(driverRule.getDriver())
                .waitForLoadingPage();
    }

    @Test
    @DisplayName("Переход по ссылке 'Войти' на странице регистрации")
    public void toLoginPageFromRegisterPageViaEnterButton() {
        new RegisterPage(driverRule.getDriver())
                .openPage()
                .waitForLoadingPage()
                .clickEnterLink();
        new LoginPage(driverRule.getDriver())
                .waitForLoadingPage();
    }

    @Test
    @DisplayName("Переход по ссылке 'Войти' на странице восстановления пароля")
    public void toLoginPageFromForgotPasswordPageViaEnterButton() {
        new ForgotPasswordPage(driverRule.getDriver())
                .openPage()
                .waitForLoadingPage()
                .clickEnterLink();
        new LoginPage(driverRule.getDriver())
                .waitForLoadingPage();
    }
}
