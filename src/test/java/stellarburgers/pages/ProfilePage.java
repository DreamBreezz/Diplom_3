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

    // === Д Е Й С Т В И Я ===
    @Step("Ожидание загрузки страницы")
    public ProfilePage waitForLoadingPage() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(saveButton));
        return this;
    }
}
