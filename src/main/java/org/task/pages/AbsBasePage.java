package org.task.pages;

import org.openqa.selenium.WebDriver;
import org.task.pageobject.AbsPageObject;
import org.task.waiter.Waiters;

public abstract class AbsBasePage extends AbsPageObject {

    private final static String BASE_URL = System.getProperty("base.url", "https://otus.ru");

    private final String path;
    public AbsBasePage(WebDriver webDriver, String path) {
        super(webDriver);
        this.path = path;
    }

    public void open() {
        webDriver.get(BASE_URL+ path);
    }
}
