package stellarburgers.tests;

import lombok.extern.java.Log;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import stellarburgers.DriverRule;
import stellarburgers.pages.LoginPage;

public class LoginTest {

    @ClassRule
    public static DriverRule driverRule = new DriverRule();

    @BeforeClass
    public static void createUserAndOpenLoginPage() {
        // здесь вставить создание юзера через АПИ
        new LoginPage(driverRule.getDriver())
                .openPage()
                .waitForLoadingPage();
    }

    @AfterClass
    public static void deleteUser() {
        // здесь вставить удаление юзера через АПИ
    }

    @Test
    public void loginUserTest() {
        new LoginPage(driverRule.getDriver())
                .inputEmail()
                .inputPassword()
                .clickEnterButton();
    }

}
