package ru.radomskii.steps;

import io.qameta.allure.Step;
import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.radomskii.entities.User;

@Slf4j
public class ModelStep extends BaseStep {

    public ModelStep(WebDriver driver) {
        super(driver);
    }

    @Step("Open provided url")
    public void open(String url) {
        log.info("Opening: " + url);
        driver.get(url);
        new WebDriverWait(driver, Duration.ofSeconds(5L))
            .until(ExpectedConditions.titleIs("ToolsQA"));
    }

    @Step("Login as User")
    public void loginAs(User user) {
        log.info("Logging as {}", user.getUsername());
        booksPage.loginAs(user);
    }

    @Step("Choose {option} books on page in dropdown menu")
    public void chooseNumberOfBooksOnPage(int option) {
        booksPage.chooseNumberOfBooksOnPage(option);
    }

    @Step("Open first page in presented book list")
    public void goToFirstPage() {
        booksPage.goToFirstPage();
    }

    @Step("Open last page in presented book list")
    public void goToLastPage() {
        booksPage.goToLastPage();
    }
}
