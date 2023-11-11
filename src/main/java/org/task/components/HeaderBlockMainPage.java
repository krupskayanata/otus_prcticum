package org.task.components;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.task.pageobject.AbsPageObject;
import org.task.waiter.Waiters;

public class HeaderBlockMainPage extends AbsPageObject {

    private final Waiters waiters = new Waiters(webDriver);

    private final Logger log = LogManager.getLogger(HeaderBlockMainPage.class);

    public HeaderBlockMainPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//div[./nav]")
    private WebElement header;



    public void clickButtonInput() {
        WebElement buttonIn = header.findElement(By.xpath(".//button[text()='Войти']"));
        buttonIn.click();
    }


}
