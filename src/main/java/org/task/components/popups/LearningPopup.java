package org.task.components.popups;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.task.pageobject.AbsPageObject;

public class LearningPopup extends AbsPageObject implements IPopup {
    public LearningPopup(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void popupShouldBeNotVisible() {
        waiters.waitInvisibilityOf(webDriver.findElement(By.xpath("//p[contains(text(), 'Все курсы')]")));
    }

    @Override
    public void popupShouldBeVisible() {
        waiters.waitElementVisible(webDriver.findElement(By.xpath("//p[contains(text(), 'Все курсы')]")));
    }

    public void clickCalendarOfActions() {
        webDriver.findElement(By.xpath("//a[contains(@href, 'https://otus.ru/events/near')]")).click();
    }
}
