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

    // === Л О К А Т О Р Ы ===
    // кнопка "Сохранить"
    public static final By SAVE_BUTTON = By
            .xpath(".//button[text()='Сохранить']");

    // кнопка "Конструктор"
    public static final By CONSTRUCTOR_BUTTON = By
            .xpath(".//p[text()='Конструктор']/ancestor::a[contains(@href, '/')]");

    // логотип
    public static final By LOGO = By
            .xpath(".//div[contains(@class,'AppHeader')]/a[contains(@href, '/')]");

    // ссылка "Выход"
    public static final By LOGOUT_LINK = By
            .xpath(".//button[text()='Выход']");


    // === Д Е Й С Т В И Я ===
    @Step("Ожидание загрузки страницы")
    public ProfilePage waitForLoadingPage() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(SAVE_BUTTON));
        return this;
    }

    @Step("Клик по кнопке 'Конструктор'")
    public void clickConstructorButton() {
        driver.findElement(CONSTRUCTOR_BUTTON).click();
    }

    @Step("Клик по логотипу")
    public void clickLogo() {
        driver.findElement(LOGO).click();
    }

    @Step("Клик по кнопке 'Выйти'")
    public void clickLogoutLink() {
        driver.findElement(LOGOUT_LINK).click();
    }
}
