package ru.radomskii.test;

import static ru.radomskii.data.TestData.BASE_URL;
import static ru.radomskii.data.TestData.USER;

import org.testng.annotations.Test;
import ru.radomskii.entities.User;

public class ProfileTest extends BaseTest {

    @Test
    public void testProfile() {
        modalStep.open(testData.getTestData(BASE_URL, String.class));
        modalStep.loginAs(testData.getTestData(USER, User.class));
        assertionStep.assertLoggedInUser();
    }
}
