package stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stellarburgers.EnvConfig;

import java.time.Duration;

public class MainPage {

    private final WebDriver driver;

    // создание драйвера браузера для теста
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открытие главной страницы")
    public MainPage openPage() {
        driver.get(EnvConfig.MAIN_URL);
        return this;
    }

    // === Локаторы ===
    // кнопка "Войти в аккаунт"
    public static final By enterAccountButton = By.
            xpath(".//button[text()='Войти в аккаунт']");

    // кнопка "Личный кабинет"
    public static final By accountButton = By.
            xpath(".//a[contains(@href, '/account')]");

    // === Действия ===
    @Step("Ожидание загрузки страницы")
    public MainPage waitForLoadingPage() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(enterAccountButton));
        return this;
    }

    @Step("Клик по кнопке 'Личный Кабинет'")
    public void clickAccountButton() {
        driver.findElement(accountButton).click();
    }

    @Step("Клик по кнопке 'Войти в аккаунт'")
    public void clickEnterAccountButton() {
        driver.findElement(enterAccountButton).click();
    }

}
