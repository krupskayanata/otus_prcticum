package org.task.components.catalog;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.task.components.Checkable;
import org.task.pageobject.AbsPageObject;
import org.task.waiter.Waiters;

import java.util.List;

public class CatalogPage extends AbsPageObject implements Checkable {

    private final static String pageCode = "catalog/courses";

    private final Waiters waiters = new Waiters(webDriver);

    private final Logger log = LogManager.getLogger(CatalogPage.class);

    public CatalogPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void checkItemQuantityInCatalogPage(int quantity) {
        List<WebElement> items = webDriver.findElements(By.xpath("//a[ancestor::section and contains(@href, '/lessons/')]"));
        Assertions.assertThat(items).hasSize(quantity);
    }

    public void clickItem() {
        webDriver.findElement(By.xpath("//a[ancestor::section and contains(@href, '/lessons/qa-game')]")).click();
    }

    @Override
    public void checkPage(String path) {
        Assertions.assertThat(
                waiters.waitForCondition(
                        ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(),'Каталог')]")))
        ).isTrue();

        Assertions.assertThat(
                waiters.waitForCondition(
                        ExpectedConditions.urlContains(this.getPageCode() + path))
        ).isTrue();


        log.info("Current url {}", webDriver.getCurrentUrl());
    }

    @Override
    public String getPageCode() {
        return pageCode;
    }
}
