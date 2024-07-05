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
    public static final By enterAccountButton = By
            .xpath(".//button[text()='Войти в аккаунт']");

    public static final By createOrderButton = By
            .xpath(".//button[text()='Оформить заказ']");

    // кнопка "Личный кабинет"
    public static final By accountButton = By
            .xpath(".//a[contains(@href, '/account')]");

    // таб "Булки"
    public static final By tabBuns = By
            .xpath(".//span[text()='Булки']/ancestor::div[contains(@class, 'tab_tab')]");

    // таб "Соусы"
    public static final By tabSauces = By
            .xpath(".//span[text()='Соусы']/ancestor::div[contains(@class, 'tab_tab')]");

    // таб "Начинки"
    public static final By tabIngredients = By
            .xpath(".//span[text()='Начинки']/ancestor::div[contains(@class, 'tab_tab')]");

    // текст "Булки" в конструкторе
    public static final By bunsText = By
            .xpath(".//div[contains(@class, 'BurgerIngredients_ingredients__menuContainer')]" +
                    "/h2[text()='Булки']");

    // текст "Соусы" в конструкторе
    public static final By saucesText = By
            .xpath(".//div[contains(@class, 'BurgerIngredients_ingredients__menuContainer')]" +
                    "/h2[text()='Соусы']");

    // текст "Начинки" в конструкторе
    public static final By ingredientsText = By
            .xpath(".//div[contains(@class, 'BurgerIngredients_ingredients__menuContainer')]" +
                    "/h2[text()='Начинки']");


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
    public MainPage setTokensToLocalStorage(String refreshToken, String accessToken) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String setRefreshToken = "window.localStorage.setItem('refreshToken', '" + refreshToken + "');";
        String setAccessToken = "window.localStorage.setItem('accessToken', '" + accessToken + "');";
        jsExecutor.executeScript(setRefreshToken);
        jsExecutor.executeScript(setAccessToken);
        return this;
    }

    @Step("Получение из Local Storage токена аутентификации")
    public String getAccessTokenFromLocalStorage() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String getToken = "return window.localStorage.getItem('accessToken');";
        return (String) jsExecutor.executeScript(getToken);
    }

    @Step("Перезагрузка страницы")
    public MainPage refresh() {
        driver.navigate().refresh();
        return this;
    }

    @Step("Клик по табу 'Булки'")
    public MainPage clickBunsTab() {
        driver.findElement(tabBuns).click();
        return this;
    }

    @Step("Клик по табу 'Соусы'")
    public MainPage clickSaucesTab() {
        driver.findElement(tabSauces).click();
        return this;
    }

    @Step("Клик по табу 'Начинки'")
    public MainPage clickIngredientsTab() {
        driver.findElement(tabIngredients).click();
        return this;
    }

    @Step("Таб 'Булки' активный")
    public MainPage currentTabBuns() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.attributeContains(tabBuns, "class", "current"));
        return this;
    }

    @Step("Таб 'Соусы' активный")
    public MainPage currentTabSauces() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.attributeContains(tabSauces, "class", "current"));
        return this;
    }

    @Step("Таб 'Начинки' активный")
    public MainPage currentTabIngredients() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.attributeContains(tabIngredients, "class", "current"));
        return this;
    }

    @Step("Скролл конструктора до Булок")
    public MainPage scrollToBuns() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(driver -> driver.findElement(bunsText).getRect().y < 300);
        return this;
    }

    @Step("Скролл конструктора до Соусов")
    public MainPage scrollToSauces() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(driver -> driver.findElement(saucesText).getRect().y < 300);
        return this;
    }

    @Step("Скролл конструктора до Начинок")
    public MainPage scrollToIngredients() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(driver -> driver.findElement(ingredientsText).getRect().y < 300);
        return this;
    }
}
