package stellarburgers.jsons.generators;

import com.github.javafaker.Faker;
import stellarburgers.jsons.CreateUserRequestJson;

public class CreateUserJsonGenerator {

    Faker faker = new Faker();

    public CreateUserRequestJson random() {
        return new CreateUserRequestJson(
                faker.internet().emailAddress(),
                faker.internet().password(),
                faker.name().username()
        );
    }
}
