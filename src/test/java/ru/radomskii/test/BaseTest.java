package ru.radomskii.test;

import static ru.radomskii.data.TestData.BASE_URL;
import static ru.radomskii.data.TestData.USER;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import ru.radomskii.data.IData;
import ru.radomskii.data.TestData;
import ru.radomskii.entities.User;
import ru.radomskii.steps.AssertionStep;
import ru.radomskii.steps.ModelStep;

@Slf4j
public class BaseTest {

    protected WebDriver driver;
    protected IData testData = new TestData();
    protected ModelStep modelStep;
    protected AssertionStep assertionStep;

    @Parameters({"username", "password", "baseUrl"})
    @BeforeClass(alwaysRun = true)
    public void setUp(String username, String password, String baseUrl) {
        log.info("Inject test data");
        testData.addTestData(USER, new User(username, password));
        testData.addTestData(BASE_URL, baseUrl);
    }

    protected void tearDownDriver() {
        if (driver != null) {
            log.info("Tear down driver: " + driver);
            driver.quit();
        }
    }

    protected void setWebDriver() {
        log.info("Setting up driver...");
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments(
            "-private");
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5L));
        driver.manage().window().maximize();
        log.info("Driver is set: " + driver);
    }

    protected void initSteps() {
        log.info("Init steps");
        modelStep = new ModelStep(driver);
        assertionStep = new AssertionStep(driver, testData);
    }
}
