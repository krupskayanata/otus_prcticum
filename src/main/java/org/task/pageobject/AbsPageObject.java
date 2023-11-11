package org.task.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.task.waiter.Waiters;

public abstract class AbsPageObject {

    protected final WebDriver webDriver;

    protected Waiters waiters;
    protected Actions actions;

    public AbsPageObject(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.waiters = new Waiters(webDriver);
        this.actions = new Actions(webDriver);

        PageFactory.initElements(webDriver, this);
    }
}
