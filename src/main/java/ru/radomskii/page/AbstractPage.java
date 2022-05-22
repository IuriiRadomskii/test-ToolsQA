package ru.radomskii.page;

import java.time.Duration;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Slf4j
@AllArgsConstructor
public abstract class AbstractPage {

    protected WebDriver driver;

    protected void click(By by) {
        log.info("Click on: " + by);
        new WebDriverWait(driver, Duration.ofSeconds(2L))
            .until(ExpectedConditions.elementToBeClickable(by))
            .click();
    }

    public boolean isDisplayed(By by) {
        return new WebDriverWait(driver, Duration.ofSeconds(5L))
            .until(ExpectedConditions.visibilityOfElementLocated(by)).isDisplayed();
    }

    public void sendKeys(WebElement input, String text) {
        new WebDriverWait(driver, Duration.ofSeconds(4L))
            .until(driver1 -> input.isEnabled());
        input.clear();
        input.sendKeys(text);
    }

    public void sendKeys(By input, String text) {
        WebElement elem = new WebDriverWait(driver, Duration.ofSeconds(4L))
            .until(driver1 -> driver1.findElement(input));
        elem.click();
        elem.sendKeys(text);
    }
}
