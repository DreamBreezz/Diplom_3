package stellarburgers.tests;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.*;
import stellarburgers.Check;
import stellarburgers.DriverRule;
import stellarburgers.jsons.CreateUserRequestJson;
import stellarburgers.jsons.generators.CreateUserJsonGenerator;
import stellarburgers.pages.LoginPage;
import stellarburgers.pages.MainPage;
import stellarburgers.rests.UserRests;

public class LoginTest {

    private static final CreateUserJsonGenerator userJson = new CreateUserJsonGenerator();
    private static final UserRests userRest = new UserRests();
    private static final Check check = new Check();

    private static CreateUserRequestJson newUser;
    private static String accessToken;
    private static boolean isUserCreated;

    @ClassRule
    public static DriverRule driverRule = new DriverRule();

    @Before
    @Step("Создание пользователя и открытие страницы логина")
    public void createUserAndOpenLoginPage() {
        newUser = userJson.random();  // генерация json рандомного пользователя
        ValidatableResponse createUserResponse = userRest.create(newUser);
        check.code201andSuccess(createUserResponse);
        isUserCreated = true;
        accessToken = check.extractAccessToken(createUserResponse);

        new LoginPage(driverRule.getDriver())
                .openPage()
                .waitForLoadingPage();
    }

    @After
    @Step("Удаление пользователя через API")
    public void deleteUser() {
        if (isUserCreated) {
            ValidatableResponse creationResponse = userRest.delete(accessToken);
            check.code202andSuccess(creationResponse);
            check.userRemovedMessage(creationResponse);
            accessToken = null;
            isUserCreated = false;
        }
    }

    @Test
    @DisplayName("Логин пользователя")
    public void loginUserTest() {
        new LoginPage(driverRule.getDriver())
                .inputEmail(newUser.getEmail())
                .inputPassword(newUser.getPassword())
                .clickEnterButton();
        new MainPage(driverRule.getDriver())
                .waitForLoadingPageAuthUser();
    }
}
