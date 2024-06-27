package stellarburgers.tests;

import org.junit.ClassRule;
import stellarburgers.DriverRule;

/**
 * вход по кнопке «Войти в аккаунт» на главной,
 * вход через кнопку «Личный кабинет»,
 * вход через кнопку в форме регистрации,
 * вход через кнопку в форме восстановления пароля.
 *
 * Проверь переход по клику на «Личный кабинет».
 *
 * Проверь переход по клику на «Конструктор» и на логотип Stellar Burgers.
 *
 * Проверь выход по кнопке «Выйти» в личном кабинете.
 *
 * Проверь, что работают переходы к разделам:
 * --- «Булки»,
 * --- «Соусы»,
 * --- «Начинки».
 */
public class NavigationTests {
    @ClassRule
    public static DriverRule driverRule = new DriverRule();
}
