package ru.radomskii.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.radomskii.entities.User;

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
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void loginAs(User user) {
        click(loginButton);
        userNameInput.sendKeys(user.getUsername());
        passwordInput.sendKeys(user.getPassword());
        click(loginButton);
    }

    public By getLogoutButton() {
        return logoutButton;
    }

    public WebElement getUserNameValue() {
        return userNameValue;
    }
}
