package org.task;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.task.factory.WebDriverFactory;
import org.task.waiter.Waiters;

import java.util.concurrent.TimeUnit;

public class AbstractBaseTest {
    protected WebDriver driver;
    protected Actions actions;
    protected Waiters waiters;


    @BeforeEach
    public void start() {
        this.driver = new WebDriverFactory().createChrome();
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
