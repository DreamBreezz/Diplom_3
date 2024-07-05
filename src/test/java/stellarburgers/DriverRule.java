package stellarburgers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverRule extends ExternalResource {
    private WebDriver driver;

    @Override
    protected void before() throws Throwable {
        initDriver();
    }

    @Override
    public void after() {
        driver.quit();
    }

    public void initDriver() {
        if ("yandex".equals(System.getProperty("browser"))) {
            initYandex();
        }
        else {
            initChrome();
        }
    }

    private void initYandex() {
        WebDriverManager.chromedriver().driverVersion(System.getProperty("browser.version")).setup();
 //       System.setProperty("webdriver.chrome.driver", "src/test/resources/yandexdriver");
        var opts = new ChromeOptions();
        opts.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");

        driver = new ChromeDriver(opts);
    }

    private void initChrome() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
