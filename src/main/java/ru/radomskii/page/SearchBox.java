package ru.radomskii.page;

import java.time.Duration;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Slf4j
public class SearchBox extends AbstractPage {

    @FindBy(css = "#searchBox")
    private WebElement searchInput;

    @FindBy(css = "div.rt-tr-group")
    private List<WebElement> searchRows;

    private By rowsPerPageMenu = By.cssSelector("div.-center > span > select");
    private By titleColumnHref = By.xpath("//span[@class='mr-2']/a");
    private By authorAndPublisherColumn = By.cssSelector("div.rt-td");
    private By nextButton = By.xpath("//button[contains(text(), 'Next')]");
    private By previousButton = By.xpath("//button[contains(text(), 'Previous')]");
    private String optionLocatorTemplate = "//option[@value='%d']";
    private By jumpToPageInput = By.cssSelector("div.-pageJump > input");
    private By totalPages = By.className("-totalPages");

    private String searchValue;

    public SearchBox(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isSearched(String searchValue) {
        searchInput.sendKeys(searchValue);
        this.searchValue = searchValue;
        return isSmthSearched();
    }

    public boolean isSmthSearched() {
        new WebDriverWait(driver, Duration.ofSeconds(1L));
        for (WebElement row : searchRows) {
            try {
                if (row.findElement(titleColumnHref).getText().contains(searchValue)) {
                    return true;
                }
            } catch (NoSuchElementException e) {
                return false;
            }
            List<WebElement> authorAndPublisher = row.findElements(authorAndPublisherColumn);
            for (WebElement elem : authorAndPublisher) {
                if (elem.getText().contains(searchValue)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void chooseOption(int number) {
        log.info("Choose {} pages on presented book list", number);
        if (!(number == 5 || number == 10 || number == 20 || number == 25 || number == 50 || number == 100)) {
            throw new IllegalArgumentException(String.format("Illegal option value %d", number));
        }
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        By option = By.xpath(String.format(optionLocatorTemplate, number));
        click(rowsPerPageMenu);
        click(option);
    }

    public int getRowsNumber() {
        return searchRows.size();
    }

    public boolean previousButtonClickability() {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(4L))
                .until(ExpectedConditions.elementToBeClickable(previousButton))
                .isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean nextButtonClickability() {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(4L))
                .until(ExpectedConditions.elementToBeClickable(nextButton))
                .isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void openFirstPage() {
        log.info("Open first page on presented booklist");
        while (!(new WebDriverWait(driver, Duration.ofSeconds(4L))
            .until(driver1 -> driver1.findElement(jumpToPageInput))
            .getAttribute("value").equals(Integer.toString(1)))) {
            click(previousButton);
        }
    }

    public void openLastPage() {
        log.info("Open last page on presented booklist");
        while (!(new WebDriverWait(driver, Duration.ofSeconds(4L))
            .until(driver1 -> driver1.findElement(jumpToPageInput)).getAttribute("value")
            .equals(driver.findElement(totalPages).getText()))) {
            click(nextButton);
        }
    }
}
