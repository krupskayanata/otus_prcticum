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
    private final Waiters waiters = new Waiters(webDriver);

    private final Logger log = LogManager.getLogger(CalendarOfActionsPage.class);

    public CalendarOfActionsPage(WebDriver webDriver) {
        super(webDriver);
    }


    @Override
    public void checkPage() {

        Assertions.assertThat(
                waiters.waitForCondition(
                        ExpectedConditions.urlContains("/events/near/"))
        ).isTrue();


        log.info("Current url {}", webDriver.getCurrentUrl());
    }

    public void clickFilterToWebinarsOnly() {
        waiters.waitElementVisible(By.xpath("//span[contains(text(), 'Интенсивы')]"));
        webDriver.findElement(By.xpath("//span[contains(text(), 'Интенсивы')]")).click();
        waiters.waitElementVisible(By.xpath("//a[contains(@title, 'Открытый вебинар')]"));
        webDriver.findElement(By.xpath("//a[contains(@title, 'Открытый вебинар')]")).click();
    }
}
