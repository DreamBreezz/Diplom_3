package stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stellarburgers.EnvConfig;

import java.time.Duration;

public class ForgotPasswordPage {
    private final WebDriver driver;

    // создание драйвера браузера для теста
    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    // открытие страницы
    @Step("Открытие страницы 'Восстановление пароля'")
    public ForgotPasswordPage openPage() {
        driver.get(EnvConfig.MAIN_URL + "/forgot-password");
        return this;
    }

    // === Л О К А Т О Р Ы ===
    // ссылка "Войти"
    public static final By enterLink = By.
            xpath(".//a[contains(@href, '/login')]");

    // текст "Вспомнили пароль?"
    public static final By rememberPasswordText = By
            .xpath(".//p[text()='Вспомнили пароль?']");

    // === Д Е Й С Т В И Я ===
    @Step("Ожидание загрузки страницы")
    public ForgotPasswordPage waitForLoadingPage() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(rememberPasswordText));
        return this;
    }

    @Step("Клик по ссылке 'Войти'")
    public void clickEnterLink() {
        driver.findElement(enterLink).click();
    }
}
