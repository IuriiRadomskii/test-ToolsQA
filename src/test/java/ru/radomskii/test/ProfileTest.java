package ru.radomskii.test;

import static ru.radomskii.data.TestData.BASE_URL;
import static ru.radomskii.data.TestData.USER;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import ru.radomskii.entities.User;

@Story("Test log in profile")
public class ProfileTest extends BaseTest {

    @Description("Verify login functionality")
    @Test
    public void testProfile() {
        modalStep.open(testData.getTestData(BASE_URL, String.class));
        modalStep.loginAs(testData.getTestData(USER, User.class));
        assertionStep.assertLoggedInUser();
    }
}
