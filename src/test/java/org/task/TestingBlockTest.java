package org.task;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.task.components.MenuSectionBlock;
import org.task.components.calendar.CalendarOfActionsPage;
import org.task.components.catalog.CatalogItemPage;
import org.task.components.catalog.CatalogPage;
import org.task.components.enums.CatalogCategory;
import org.task.components.popups.LearningPopup;
import org.task.factory.WebDriverFactory;
import org.task.pages.MainPage;

import java.util.concurrent.TimeUnit;

public class TestingBlockTest {

    private final static String WEBINAR_MEETING_TYPE = "Открытый вебинар";
    private final static String INTENSIVES_MEETING_TYPE = "Интенсивы";

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
    public void testTest() {
        new MainPage(driver, "/").open();

        MenuSectionBlock menuSectionBlock = new MenuSectionBlock(driver);
        menuSectionBlock.checkBlockMenuAvailable(CatalogCategory.TESTING.getTitle());
        menuSectionBlock.clickMenuToCatalogPage(CatalogCategory.TESTING.getTitle());

        CatalogPage catalogPage = new CatalogPage(driver);
        catalogPage.checkPage("?categories=testing");
        catalogPage.checkItemQuantityInCatalogPage(10);
        catalogPage.clickItem();

        CatalogItemPage catalogItemPage = new CatalogItemPage(driver);
        catalogItemPage.checkPage("/qa-game/");
        catalogItemPage.checkDescription();
        catalogItemPage.openLearningPopup();

        LearningPopup learningPopup = new LearningPopup(driver);
        learningPopup.popupShouldBeVisible();
        learningPopup.clickCalendarOfActions();
        learningPopup.popupShouldBeNotVisible();

        CalendarOfActionsPage calendarOfActionsPage = new CalendarOfActionsPage(driver);
        calendarOfActionsPage.checkPage("/near");
        calendarOfActionsPage.checkMeetingCurrentFilter(INTENSIVES_MEETING_TYPE);
        calendarOfActionsPage.changeMeetingFilterType(INTENSIVES_MEETING_TYPE, WEBINAR_MEETING_TYPE);

    }
}
