package org.task.components;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.task.pageobject.AbsPageObject;
import org.task.waiter.Waiters;

public class MenuSectionBlock extends AbsPageObject {

    private final Waiters waiters = new Waiters(webDriver);

    private final Logger log = LogManager.getLogger(MenuSectionBlock.class);

    public MenuSectionBlock(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//main[./section]")
    private WebElement menuBlock;


    public void checkBlockMenuAvailable() {
        Assertions.assertThat(waiters.waitElementVisible(menuBlock.findElement(By.xpath(
                ".//descendant::section/descendant::div[contains(text(), 'Тестирование')]")))).isTrue();
    }

    public void clickTestingMenuToCatalogPage() {
        WebElement catalogPage = menuBlock.findElement(By.xpath(
                ".//descendant::section/descendant::div[contains(text(), 'Тестирование')]"));
        catalogPage.click();
    }
}
