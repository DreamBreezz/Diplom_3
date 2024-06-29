package stellarburgers.tests;

import io.restassured.response.ValidatableResponse;
import org.junit.*;
import stellarburgers.Check;
import stellarburgers.DriverRule;
import stellarburgers.jsons.CreateUserRequestJson;
import stellarburgers.jsons.generators.CreateUserJsonGenerator;
import stellarburgers.pages.LoginPage;
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
    public void createUserAndOpenLoginPage() {
        newUser = userJson.random();  // генерация json рандомного пользователя
        ValidatableResponse createUserResponse = userRest.create(newUser);
        accessToken = check.extractAccessToken(createUserResponse);

        new LoginPage(driverRule.getDriver())
                .openPage()
                .waitForLoadingPage();
    }

    @After
    public void deleteUser() {
        ValidatableResponse creationResponse = userRest.delete(accessToken);
        check.code202andSuccess(creationResponse);
        check.userRemovedMessage(creationResponse);
        accessToken = null;
    }

    @Test
    public void loginUserTest() {
        new LoginPage(driverRule.getDriver())
                .inputEmail(newUser.getEmail())
                .inputPassword(newUser.getPassword())
                .clickEnterButton();
    }

}
