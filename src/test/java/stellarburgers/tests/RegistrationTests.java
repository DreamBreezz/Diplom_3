package stellarburgers.tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import stellarburgers.DriverRule;
import stellarburgers.pages.MainPage;

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
    @DisplayName("Регистрация пользователя")
    public void registrationTest() {
        new MainPage(driverRule.getDriver());
        // открыть главную
        // клик на Личный кабинет
        // клик на Зарегистрироваться
        // ввод имени
        // ввод емеил
        // ввод пароль
        // клик на Зарегистрироваться
        // что-то увидеть
    }

    @Test
    @DisplayName("[–] Регистрация с некорректным паролем")
    public void registrationWrongPasswordTest() {
        // открыть главную
        // клик на Личный кабинет
        // клик на Зарегистрироваться
        // ввод имени
        // ввод емеил
        // ввод пароль
        // клик на Зарегистрироваться
        // что-то увидеть
    }

    /////
}
