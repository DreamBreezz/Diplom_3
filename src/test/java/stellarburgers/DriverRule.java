package stellarburgers;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.response.ValidatableResponse;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import praktikum.Check;
import praktikum.jsons.CreateUserRequestJson;
import praktikum.jsons.generators.LoginUserJsonGenerator;
import praktikum.rests.UserRests;
import stellarburgers.tests.RegistrationTests;

public class DriverRule extends ExternalResource {
    private WebDriver driver;

    private static final UserRests userRest = new UserRests();
    private static final LoginUserJsonGenerator loginJson = new LoginUserJsonGenerator();
    private static final Check check = new Check();

    static CreateUserRequestJson newUser;
    private static String accessToken;

    @Override
    protected void before() throws Throwable {
        initDriver();
    }

    @Override
    public void after() {
        deleteUserIfCreated(RegistrationTests.getCreated(),
                RegistrationTests.getNewUser());
        driver.quit();
    }

    // это подредактировать под Яндекс
    public void initDriver() {
        if ("firefox".equals(System.getProperty("browser"))) {
            initFirefox();
        }
        else {
            initChrome();
        }
    }

    // это поменять на Яндекс
    private void initFirefox() {
        WebDriverManager.firefoxdriver().setup();
        var opts = new FirefoxOptions()
                .configureFromEnv();
        driver = new FirefoxDriver(opts);
    }

    private void initChrome() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void deleteUserIfCreated(boolean isUserCreated, CreateUserRequestJson newUser) {
        if (isUserCreated) {
            var newLogin = loginJson.from(newUser);
            ValidatableResponse loginUserResponse = userRest.login(newLogin);
            accessToken = check.extractAccessToken(loginUserResponse);

            ValidatableResponse creationResponse = userRest.delete(accessToken);
            check.code202andSuccess(creationResponse);
            check.userRemovedMessage(creationResponse);
            accessToken = null;
            isUserCreated = false;
        }
    }
}
