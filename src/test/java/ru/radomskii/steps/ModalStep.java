package ru.radomskii.steps;

import io.qameta.allure.Step;
import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.radomskii.entities.User;

@Slf4j
public class ModalStep extends BaseStep {

    public ModalStep(WebDriver driver) {
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
        log.info("Logging user: " + user.getUsername());
        booksPage.loginAs(user);
    }
}
