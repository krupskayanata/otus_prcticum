package org.task.components.calendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.task.components.Checkable;
import org.task.pageobject.AbsPageObject;
import org.task.waiter.Waiters;

public class CalendarOfActionsPage extends AbsPageObject implements Checkable {

    private final static String pageCode = "/events";
    private final Waiters waiters = new Waiters(webDriver);

    private final Logger log = LogManager.getLogger(CalendarOfActionsPage.class);

    public CalendarOfActionsPage(WebDriver webDriver) {
        super(webDriver);
    }


    @Override
    public void checkPage(String path) {

        Assertions.assertThat(
                waiters.waitForCondition(
                        ExpectedConditions.urlContains(this.getPageCode() + path))
        ).isTrue();


        log.info("Current url {}", webDriver.getCurrentUrl());
    }

    public void checkMeetingCurrentFilter(String currentMeetingType) {
        Assertions.assertThat(
                waiters.waitElementVisible(By.xpath(String.format("//span[contains(text(), %s)]", currentMeetingType)))
        ).isTrue();
    }

    public void changeMeetingFilterType(String currentMeetingType, String newMeetingType) {
        waiters.waitElementVisible(By.xpath(String.format("//span[contains(text(), '%s')]", currentMeetingType)));
        webDriver.findElement(By.xpath(String.format("//span[contains(text(), '%s')]", currentMeetingType))).click();
        waiters.waitElementVisible(By.xpath(String.format("//a[contains(@title, '%s')]", newMeetingType)));
        webDriver.findElement(By.xpath(String.format("//a[contains(@title, '%s')]", newMeetingType))).click();
    }

    @Override
    public String getPageCode() {
        return pageCode;
    }
}
