package ru.radomskii.page;

import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Slf4j
public abstract class AbstractPage {

    protected WebDriver driver;

    protected void click(By by) {
        log.info("Click on: " + by);
        new WebDriverWait(driver, Duration.ofSeconds(5L))
            .until(ExpectedConditions.elementToBeClickable(by))
            .click();
    }

    public boolean isDisplayed(By by) {
        return new WebDriverWait(driver, Duration.ofSeconds(5L))
            .until(ExpectedConditions.visibilityOfElementLocated(by)).isDisplayed();
    }

    public boolean isDisplayed(WebElement webElement) {
        return webElement.isDisplayed();
    }
}
