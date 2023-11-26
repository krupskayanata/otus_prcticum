package org.task.components.popups;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.task.pageobject.AbsPageObject;

public class MenuPopup extends AbsPageObject implements IPopup {
    public MenuPopup(WebDriver webDriver) {
        super(webDriver);
    }

    private final Logger log = LogManager.getLogger(MenuPopup.class);

    //MenuPopup

    private String menuPopupSelector = "//a[contains(@href, '/lk/biography/personal')]";

    @Override
    public void popupShouldBeNotVisible() {
        Assertions.assertThat(
                        waiters.waitForCondition(ExpectedConditions.invisibilityOfElementLocated(By.xpath(menuPopupSelector))))
                .as("Auth popup should not be present on page")
                .isTrue();
    }

    @Override
    public void popupShouldBeVisible() {
        Assertions.assertThat(
                        waiters.waitForCondition(ExpectedConditions.elementToBeClickable(By.xpath(menuPopupSelector))))
                .as("Auth popup should be present on page")
                .isTrue();
    }

    public void clickMenu() {
        WebElement elProfile = webDriver.findElement(By.cssSelector(".sc-199a3eq-0.fJMWHf"));
        waiters.waitElementToBeClickable(elProfile);
        actions.moveToElement(elProfile).perform();
    }

    public void clickProfile() {
        WebElement elMyProfile = webDriver.findElement(By.xpath(" //a[contains(text(), 'Мой профиль')]"));
        waiters.waitElementVisible(elMyProfile);
        elMyProfile.click();

        log.info("Выполнен вход в Мой Профиль");
    }

}

