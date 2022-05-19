package ru.radomskii.steps;

import org.openqa.selenium.WebDriver;
import ru.radomskii.page.BooksPage;

public abstract class BaseStep {

    protected WebDriver driver;
    protected BooksPage booksPage;

    BaseStep(WebDriver driver) {
        this.driver = driver;
        booksPage = new BooksPage(driver);
    }
}
