package ru.radomskii.steps;

import static ru.radomskii.data.TestData.USER;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;
import ru.radomskii.data.TestData;
import ru.radomskii.entities.User;

@Slf4j
public class AssertionStep extends BaseStep {

    private SoftAssert softAssert = new SoftAssert();
    private TestData testData;

    public AssertionStep(WebDriver driver, TestData testData) {
        super(driver);
        this.testData = testData;
    }

    @Step("Assert if there is an expected username and 'Logout' button")
    public void assertLoggedInUser() {
        softAssert.assertEquals(booksPage.isDisplayed(booksPage.getLogoutButton()), true);
        softAssert
            .assertEquals(booksPage.getUserNameValue().getText(), testData.getTestData(USER, User.class).getUsername());
        softAssert.assertAll();
    }
}
