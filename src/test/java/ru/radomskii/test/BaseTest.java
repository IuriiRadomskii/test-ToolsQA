package ru.radomskii.test;

import static ru.radomskii.data.TestData.BASE_URL;
import static ru.radomskii.data.TestData.USER;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import ru.radomskii.data.TestData;
import ru.radomskii.entities.User;
import ru.radomskii.steps.AssertionStep;
import ru.radomskii.steps.ModalStep;

@Slf4j
public class BaseTest {

    protected WebDriver driver;
    protected TestData testData = new TestData();
    protected ModalStep modalStep;
    protected AssertionStep assertionStep;

    @Parameters({"username", "password", "baseUrl"})
    @BeforeClass(alwaysRun = true)
    public void setUp(String username, String password, String baseUrl) {
        log.info("Before class...");
        testData.addTestData(USER, new User(username, password));
        testData.addTestData(BASE_URL, baseUrl);
        setWebDriver();
        initSteps();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            log.info("Tear down driver: " + driver);
            driver.quit();
        }
    }

    private void setWebDriver() {
        log.info("Setting up driver...");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5L));
        log.info("Driver is set: " + driver);
    }

    private void initSteps() {
        modalStep = new ModalStep(driver);
        assertionStep = new AssertionStep(driver, testData);
    }
}
