package ru.radomskii.steps;

import static ru.radomskii.data.TestData.USER;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;
import ru.radomskii.data.IData;
import ru.radomskii.entities.User;

public class AssertionStep extends BaseStep {

    private SoftAssert softAssert = new SoftAssert();
    private Assertion assertion = new Assertion();
    private IData testData;

    public AssertionStep(WebDriver driver, IData testData) {
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

    @Step("Assert if there is searched value in presented searched results")
    public void assertSearchedBooks(String searchValue, boolean isSearchedSmth) {
        assertion.assertEquals(booksPage.isThereSearchedValue(searchValue), isSearchedSmth);
    }

    @Step("Assert number of presented rows")
    public void assertRows(int option) {
        softAssert.assertEquals(booksPage.getRowsOnPage(), option);
    }

    @Step("Assert all")
    public void assertAll() {
        softAssert.assertAll();
    }

    @Step("Assert clikability of Previous and Next buttons")
    public void assertPreviousAndNextButtonClickability(boolean previous, boolean next) {
        softAssert.assertEquals(booksPage.isPreviousButtonClickable(), previous);
        softAssert.assertEquals(booksPage.isNextButtonClickable(), next);
    }
}
