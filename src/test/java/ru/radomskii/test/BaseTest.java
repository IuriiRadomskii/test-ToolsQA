package ru.radomskii.test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import ru.radomskii.entities.User;

public class BaseTest {

    protected WebDriver driver;
    protected User testUser;

    @Parameters({"username", "password"})
    @BeforeSuite(alwaysRun = true)
    public void setUp(String username, String password) {
        testUser = new User(username, password);
    }

}
