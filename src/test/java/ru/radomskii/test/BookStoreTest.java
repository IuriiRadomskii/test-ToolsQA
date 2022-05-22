package ru.radomskii.test;

import static ru.radomskii.data.TestData.BASE_URL;

import io.qameta.allure.Story;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.radomskii.data.BookStoreTestDataProvider;

@Story("Test BookStore page functionality")
public class BookStoreTest extends BaseTest {

    @BeforeMethod
    public void setupBookStoreTest() {
        setWebDriver();
        initSteps();
        modelStep.open(testData.getTestData(BASE_URL, String.class));
    }

    @AfterMethod
    public void tearDownBookStoreTest() {
        tearDownDriver();
    }

    @Test(dataProvider = "Search input data", dataProviderClass = BookStoreTestDataProvider.class)
    public void searchInputTest(String searchValue, boolean isSearchedSmth) {
        assertionStep.assertSearchedBooks(searchValue, isSearchedSmth);
    }

    @Test(dataProvider = "Rows on page display options", dataProviderClass = BookStoreTestDataProvider.class)
    public void dropdownMenuPresentedBooksTest(int[] testOptions) {
        int[] options = testOptions;
        for (int option : options) {
            modelStep.chooseNumberOfBooksOnPage(option);
            assertionStep.assertRows(option);
        }
        assertionStep.assertAll();
    }

    @Test
    public void nextAndPreviousButtonsTest() {
        modelStep.chooseNumberOfBooksOnPage(5);
        assertionStep.assertPreviousAndNextButtonClickability(false, true);
        modelStep.goToLastPage();
        assertionStep.assertPreviousAndNextButtonClickability(true, false);
        assertionStep.assertAll();
    }
}
