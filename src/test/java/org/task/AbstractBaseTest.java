package org.task;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.task.factory.WebDriverFactory;
import org.task.waiter.Waiters;

import java.util.concurrent.TimeUnit;

public class AbstractBaseTest {

    protected WebDriverFactory webDriverFactory = new WebDriverFactory();
    protected WebDriver driver;
    protected Actions actions;
    protected Waiters waiters;




}
