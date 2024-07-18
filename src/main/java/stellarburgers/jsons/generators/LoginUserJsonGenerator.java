package stellarburgers.jsons.generators;

import stellarburgers.jsons.CreateUserRequestJson;
import stellarburgers.jsons.UserRequestJson;

public class LoginUserJsonGenerator {

    public UserRequestJson from(CreateUserRequestJson user) {
        return new UserRequestJson(
                user.getEmail(),
                user.getPassword()
        );
    }
}
