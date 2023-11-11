package org.task;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.WebDriver;
import org.task.components.HeaderBlockMainPage;
import org.task.components.ProfilePage;
import org.task.components.popups.AuthPopup;
import org.task.components.popups.MenuPopup;
import org.task.factory.WebDriverFactory;
import org.task.pages.MainPage;

import java.util.concurrent.TimeUnit;

public class FillOtusInfo_Test {
    protected WebDriverFactory webDriverFactory = new WebDriverFactory();
    protected WebDriver driver;

    @BeforeEach
    public void start() {
        this.driver = webDriverFactory.createBrowser();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterEach
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void clickButtonIn() throws InterruptedException {
        new MainPage(driver, "/").open();
        AuthPopup authPopup = new AuthPopup(driver);
        authPopup.popupShouldBeNotVisible();

        new HeaderBlockMainPage(driver).clickButtonInput();

        authPopup.popupShouldBeVisible();
        authPopup.login();
        authPopup.popupShouldBeNotVisible();

        MenuPopup menuPopup = new MenuPopup(driver);
        menuPopup.clickMenu();
        menuPopup.popupShouldBeVisible();
        menuPopup.clickProfile();

        menuPopup.popupShouldBeNotVisible();

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.fillPersonalInformation();

        profilePage.fillContacts();

        profilePage.fillAdditionalInfo();

        profilePage.fillExperience();
        profilePage.clickSaveButton();

        close();
        start();

        new MainPage(driver, "/").open();
        authPopup = new AuthPopup(driver);
        authPopup.popupShouldBeNotVisible();

        new HeaderBlockMainPage(driver).clickButtonInput();

        authPopup.popupShouldBeVisible();
        authPopup.login();
        authPopup.popupShouldBeNotVisible();

        menuPopup = new MenuPopup(driver);
        menuPopup.clickMenu();
        menuPopup.popupShouldBeVisible();
        menuPopup.clickProfile();

        menuPopup.popupShouldBeNotVisible();

        profilePage = new ProfilePage(driver);
        profilePage.assertionsLK();



    }
}
