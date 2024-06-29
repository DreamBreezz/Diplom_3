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
        driver.get(EnvConfig.MAIN_URL + "/login");
        return this;
    }


    // === Локаторы ===
    // поле "Email"
    private final By inputEmail = By.xpath(".//label[text()='Email']/following-sibling::input");

    // поле "Пароль"
    private final By inputPassword = By.xpath(".//label[text()='Пароль']/following-sibling::input");

    // кнопка "Зарегистрироваться"
    public static final By registerButton = By.
            xpath(".//a[contains(@href, '/register')]");

    // ссылка "Восстановить пароль"
    public static final By recoverPassButton = By.
            xpath(".//a[contains(@href, '/forgot-password')]");

    // кнопка "Войти"
    public static final By enterButton = By.
            xpath(".//button[text()='Войти']");


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

    @Step("Ввод email")
    public LoginPage inputEmail(String email) {
        driver.findElement(inputEmail).sendKeys(email);
        return this;
    }

    @Step("Ввод пароля")
    public LoginPage inputPassword(String pass) {
        driver.findElement(inputPassword).sendKeys(pass);
        return this;
    }

    @Step("Клик по кнопке 'Войти'")
    public void clickEnterButton() {
        driver.findElement(enterButton).click();
    }
}
