package ru.radomskii.page;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.radomskii.entities.User;

@Getter
public class BooksPage extends AbstractPage {

    private By loginButton = By.cssSelector("#login");
    private By logoutButton = By.cssSelector("#submit");

    @FindBy(css = "#userName")
    private WebElement userNameInput;

    @FindBy(css = "#password")
    private WebElement passwordInput;

    @FindBy(css = "#userName-value")
    private WebElement userNameValue;

    public BooksPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void loginAs(User user) {
        click(loginButton);
        userNameInput.sendKeys(user.getUsername());
        passwordInput.sendKeys(user.getPassword());
        click(loginButton);
    }

    public boolean isThereSearchedValue(String searchValue) {
        return new SearchBox(driver).isSearched(searchValue);
    }

    public void chooseNumberOfBooksOnPage(int option) {
        new SearchBox(driver).chooseOption(option);
    }

    public int getRowsOnPage() {
        return new SearchBox(driver).getRowsNumber();
    }

    public void goToFirstPage() {
        new SearchBox(driver).openFirstPage();
    }

    public void goToLastPage() {
        new SearchBox(driver).openLastPage();
    }


    public boolean isPreviousButtonClickable() {
        return new SearchBox(driver).previousButtonClickability();
    }

    public boolean isNextButtonClickable() {
        return new SearchBox(driver).nextButtonClickability();
    }
}
