package stellarburgers.pages;

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

    // открытие страницы
    public MainPage openPage() {
        driver.get(EnvConfig.MAIN_URL);
        return this;
    }

    // ожидание загрузки страницы
    public MainPage waitForHeader() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(pageHeader));
        return this;
    }

    // Заголовок "Для кого самокат"
    private final By pageHeader = By.xpath(".//div[text()='Для кого самокат']");
}
