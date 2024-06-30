package stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stellarburgers.EnvConfig;

import java.time.Duration;

public class MainPage {

    private final WebDriver driver;
    private WebDriver driverAuth;

    // создание драйвера браузера для теста
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открытие главной страницы")
    public MainPage openPage() {
        driver.get(EnvConfig.MAIN_URL);
        return this;
    }

    // === Л О К А Т О Р Ы ===
    // кнопка "Войти в аккаунт"
    public static final By enterAccountButton = By.
            xpath(".//button[text()='Войти в аккаунт']");

    public static final By createOrderButton = By.
            xpath(".//button[text()='Оформить заказ']");

    // кнопка "Личный кабинет"
    public static final By accountButton = By.
            xpath(".//a[contains(@href, '/account')]");

    // === Д Е Й С Т В И Я ===
    @Step("Ожидание загрузки страницы (неавторизованный пользователь)")
    public MainPage waitForLoadingPage() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(enterAccountButton));
        return this;
    }

    @Step("Ожидание загрузки страницы (авторизованный пользователь)")
    public MainPage waitForLoadingPageAuthUser() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(createOrderButton));
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

    @Step("Запись в Local Storage токенов аутентификации")
    public MainPage setLocalStorage(String refreshToken, String accessToken) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String setRefreshToken = "window.localStorage.setItem('refreshToken', '" + refreshToken + "');";
        String setAccessToken = "window.localStorage.setItem('accessToken', '" + accessToken + "');";
        jsExecutor.executeScript(setRefreshToken);
        jsExecutor.executeScript(setAccessToken);
        return this;
    }

    @Step("Перезагрузка страницы")
    public MainPage refresh() {
        driver.navigate().refresh();
        return this;
    }
}
