package org.task;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.task.factory.WebDriverFactory;
import org.task.waiter.Waiters;

import java.util.concurrent.TimeUnit;

public class AbstractBaseTest {

    private WebDriverFactory webDriverFactory = new WebDriverFactory();
    protected WebDriver driver;
    protected Actions actions;
    protected Waiters waiters;


    @BeforeEach
    public void start() {
        this.driver = webDriverFactory.createBrowser();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        this.waiters = new Waiters(driver);
    }

    @AfterEach
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }

}
