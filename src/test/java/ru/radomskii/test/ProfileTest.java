package ru.radomskii.test;

import static ru.radomskii.data.TestData.BASE_URL;
import static ru.radomskii.data.TestData.USER;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.radomskii.entities.User;

@Story("Test log in profile")
public class ProfileTest extends BaseTest {

    @BeforeClass
    public void setupProfileTest() {
        setWebDriver();
        initSteps();
    }

    @AfterClass
    public void tearDownProfileTest() {
        tearDownDriver();
    }

    @Description("Verify login functionality")
    @Test
    public void testProfile() {
        modelStep.open(testData.getTestData(BASE_URL, String.class));
        modelStep.loginAs(testData.getTestData(USER, User.class));
        assertionStep.assertLoggedInUser();
    }
}
