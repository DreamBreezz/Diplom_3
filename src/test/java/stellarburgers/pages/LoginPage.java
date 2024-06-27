package stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stellarburgers.EnvConfig;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;

    // создание драйвера браузера для теста
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // открытие страницы
    public LoginPage openPage() {
        driver.get(EnvConfig.MAIN_URL + "/account/profile");
        return this;
    }


    // === Локаторы ===
    // ссылка "Восстановить пароль"
    public static final By recoverPassButton = By.
            xpath(".//a[contains(@href, '/forgot-password')]");

    // кнопка "Зарегистрироваться"
    public static final By registerButton = By.
            xpath(".//a[contains(@href, '/register')]");

    // === Действия ===
    @Step("Ожидание загрузки страницы")
    public LoginPage waitForLoadingPage() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(recoverPassButton));
        return this;
    }

    @Step("Клик по кнопке 'Зарегистрироваться'")
    public void clickRegisterLink() {
        driver.findElement(registerButton).click();
    }
}
