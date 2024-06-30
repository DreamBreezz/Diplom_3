package stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stellarburgers.EnvConfig;

import java.time.Duration;

public class ProfilePage {
    private final WebDriver driver;

    // создание драйвера браузера для теста
    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    // открытие страницы
    public ProfilePage openPage() {
        driver.get(EnvConfig.MAIN_URL + "/account/profile");
        return this;
    }

    // === Л О К А Т О Р Ы ===
    // кнопка "Сохранить"
    public static final By saveButton = By
            .xpath(".//button[text()='Сохранить']");

    // кнопка "Конструктор"
    public static final By constructorButton = By
            .xpath(".//p[text()='Конструктор']/ancestor::a[contains(@href, '/')]");

    // логотип
    public static final By logo = By
            .xpath(".//div[contains(@class,'AppHeader')]/a[contains(@href, '/')]");

    // ссылка "Выход"
    public static final By logoutLink = By
            .xpath(".//button[text()='Выход']");

    // === Д Е Й С Т В И Я ===
    @Step("Ожидание загрузки страницы")
    public ProfilePage waitForLoadingPage() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(saveButton));
        return this;
    }

    @Step("Клик по кнопке 'Конструктор'")
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    @Step("Клик по логотипу")
    public void clickLogo() {
        driver.findElement(logo).click();
    }

    @Step("Клик по кнопке 'Выйти'")
    public void clickLogoutLink() {
        driver.findElement(logoutLink).click();
    }
}
