package stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stellarburgers.EnvConfig;

import java.time.Duration;

public class RegisterPage {
    private final WebDriver driver;

    // создание драйвера браузера для теста
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    // открытие страницы
    public RegisterPage openPage() {
        driver.get(EnvConfig.MAIN_URL + "/login");
        return this;
    }

    // === Локаторы ===
    // кнопка "Зарегистрироваться"
    public static final By registerButton = By.
            xpath(".//button[text()='Зарегистрироваться']");

    // поле "Имя"
    private final By inputName = By.xpath(".//label[text()='Имя']/following-sibling::input");

    // поле "Email"
    private final By inputEmail = By.xpath(".//label[text()='Email']/following-sibling::input");

    // поле "Пароль"
    private final By inputPassword = By.xpath(".//label[text()='Пароль']/following-sibling::input");

    // текст "Некорректный пароль"
    private final By wrongPasswordText = By.xpath(".//p[text()='Некорректный пароль']");

    // === Действия ===
    @Step("Ожидание загрузки страницы")
    public RegisterPage waitForLoadingPage() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(registerButton));
        return this;
    }

    @Step("Ввод имени")
    public RegisterPage inputName() {
        driver.findElement(inputName).sendKeys("Джон Смит");
        return this;
    }

    @Step("Ввод email")
    public RegisterPage inputEmail() {
        driver.findElement(inputEmail).sendKeys("jsm0001@mail.mail");
        return this;
    }

    @Step("Ввод пароля")
    public RegisterPage inputPassword() {
        driver.findElement(inputPassword).sendKeys("P@ssword");
        return this;
    }

    @Step("Ввод неподходящего пароля")
    public RegisterPage inputWrongPassword() {
        driver.findElement(inputPassword).sendKeys("123");
        return this;
    }

    @Step("Клик по кнопке 'Зарегистрироваться'")
    public RegisterPage clickRegisterButton() {
        driver.findElement(registerButton).click();
        return this;
    }

    @Step("Проверка наличия текста 'Некорректный пароль'")
    public void checkWrongPasswordWarning() {
        driver.findElement(wrongPasswordText);
    }
}
