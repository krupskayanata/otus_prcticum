package org.task.pages;

import org.openqa.selenium.WebDriver;
import org.task.pageobject.AbsPageObject;
import org.task.waiter.Waiters;

public class AbsBasePage extends AbsPageObject {

    private final static String BASE_URL = System.getProperty("base.url", "https://otus.ru");

    protected final Waiters waiters;

    private final String path;
    public AbsBasePage(WebDriver webDriver, String path) {
        super(webDriver);
        this.path = path;
        this.waiters = new Waiters(webDriver);
    }

    public void open() {
        webDriver.get(BASE_URL+ path);
    }
}
