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

    @ClassRule
    public static DriverRule driverRule = new DriverRule();

    @Before
    @Step("Создание пользователя через API и открытие страницы логина")
    public void createUserAndOpenLoginPage() {
        newUser = userJson.random();  // генерация json рандомного пользователя
        ValidatableResponse createUserResponse = userRest.create(newUser);  // создание пользователя
        check.code201andSuccess(createUserResponse);
        accessToken = check.extractAccessToken(createUserResponse);  // сохранение токена авторизации на случай,
                                                                     // если логин через браузер не сработает
        new LoginPage(driverRule.getDriver())
                .openPage()
                .waitForLoadingPage();
    }

    @After
    @Step("Удаление пользователя через API")
    public void deleteUser() {
        ValidatableResponse creationResponse = userRest.delete(accessToken);
        check.code202andSuccess(creationResponse);
        check.userRemovedMessage(creationResponse);
        accessToken = null;
    }

    @Test
    @DisplayName("[+] Логин пользователя")
    public void loginUserTest() {
        new LoginPage(driverRule.getDriver())
                .inputEmail(newUser.getEmail())
                .inputPassword(newUser.getPassword())
                .clickEnterButton();
        accessToken = new MainPage(driverRule.getDriver())  // перезапись токена, сохранённого при создании юзера
                .waitForLoadingPageAuthUser()
                .getAccessTokenFromLocalStorage();
        System.out.println("\nAccess token from local storage:\n" + accessToken + "\n");
    }
}
