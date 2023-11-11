package org.task.components.popups;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.task.pageobject.AbsPageObject;
import org.assertj.core.api.*;

public class AuthPopup extends AbsPageObject implements IPopup {
    private final String PASSWORD = System.getProperty("password") != null ? System.getProperty("password") : "Aa1313++";
    private final String LOGIN = System.getProperty("login") != null ? System.getProperty("login") : "semabutus@mail.ru";
    public AuthPopup(WebDriver webDriver) {
        super(webDriver);
    }

    private final Logger log = LogManager.getLogger(AuthPopup.class);

    private String authPopupSelector = "#__PORTAL__ > div";

    @Override
    public void popupShouldBeNotVisible() {
        Assertions.assertThat(
                waiters.waitForCondition(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(authPopupSelector))))
                .as("Auth popup should not be present on page")
                .isTrue();
    }

    @Override
    public void popupShouldBeVisible() {
        Assertions.assertThat(waiters.waitElementVisible(By.cssSelector(authPopupSelector)))
                .as("Auth popup should be present on page")
                .isTrue();
    }

    public void login() throws InterruptedException {
        clearAndEnter(By.cssSelector("input[name=\"email\"]"), LOGIN);
        clearAndEnter(By.cssSelector("input[type=\"password\"]"), PASSWORD);
        WebElement buttonIn2 = webDriver.findElement(By.xpath("//div[parent::button[contains(@type, 'button')] and contains(text(),'Войти')]"));
        waiters.waitElementVisible(By.xpath("//div[parent::button[contains(@type, 'button')] and contains(text(),'Войти')]"));
        clickElement(buttonIn2);
        log.info("Authorized successfully");
    }

    private void clearAndEnter(By by, String text) {
        WebElement webElement = webDriver.findElement(by);
        String js = "arguments[0].click();";
        ((JavascriptExecutor) webDriver).executeScript(js, webElement);
        webElement.clear();
        webDriver.findElement(by).sendKeys(text);
    }

    private void clickElement(WebElement webElement) throws InterruptedException {
        String js = "arguments[0].click();";
        boolean noElement = true;
        do {
            ((JavascriptExecutor) webDriver).executeScript(js, webElement);
            Thread.sleep(5L);
            try {
                webDriver.findElement(By.xpath("//div[contains(text(),'Неизвестная ошибка')]"));
            } catch (NoSuchElementException ex) {
                noElement = false;
            }

        } while (noElement);
    }

}
