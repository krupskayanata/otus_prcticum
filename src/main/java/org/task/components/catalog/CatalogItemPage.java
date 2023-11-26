package org.task.components.catalog;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.task.components.Checkable;
import org.task.pageobject.AbsPageObject;
import org.task.waiter.Waiters;

public class CatalogItemPage extends AbsPageObject implements Checkable {

    private final static String pageCode = "/lessons";

    private final Waiters waiters = new Waiters(webDriver);

    private final Logger log = LogManager.getLogger(CatalogItemPage.class);
    public CatalogItemPage(WebDriver webDriver) {
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

    public void checkDescription() {
        webDriver.findElement(By.xpath("//h1[contains(text(), 'Game QA Engineer')]"));
    }

    public void openLearningPopup() {
        webDriver.findElement(By.xpath("//div[parent::div/child::span[contains(text(), 'Обучение')]]")).click();
    }

    @Override
    public String getPageCode() {
        return pageCode;
    }
}
