package org.task;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.task.factory.WebDriverFactory;
import org.task.waiter.Waiters;

import java.util.concurrent.TimeUnit;


public class AuthorizationTest {

    protected WebDriverFactory webDriverFactory = new WebDriverFactory();
    protected WebDriver driver;
    protected Actions actions;
    protected Waiters waiters;

    private final String BASE_URL = System.getProperty("base.url", "https://otus.ru" );
    private final String PASSWORD = System.getProperty("password") != null ? System.getProperty("password") : "Aa1313++";
    private final String LOGIN = System.getProperty("login") != null ? System.getProperty("login") : "semabutus@mail.ru";

    private final Logger log = LogManager.getLogger(AuthorizationTest.class);

    @BeforeEach
    public void start() {
        this.driver = webDriverFactory.createBrowser();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        this.waiters = new Waiters(driver);
    }

    @AfterEach
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void otusAuthorizationTest() {

        driver.get(BASE_URL + "/");

        login();

        enterLK();

        fillPersonalInformation();

        fillContacts();

        fillAdditionalInfo();

        fillExperience();

        driver.findElement(By.cssSelector("button[title=\"Сохранить и заполнить позже\"]")).submit();
        log.info("Information was saved successfully");

        close();
        start();
        driver.get(BASE_URL + "/");

        login();

        enterLK();

        assertionsLK();
    }

    private void login() {
        driver.findElement(By.cssSelector(".sc-mrx253-0.enxKCy.sc-945rct-0.iOoJwQ")).click(); //sc-rq8xzv-1 kqcJKZ sc-11ptd2v-1 liHMCp #__PORTAL__ > div > div > div.sc-1alnis6-1.ejcuap > div.sc-1alnis6-4.iVBbVz > div > div.sc-10p60tv-1.eDzhKh > div.sc-10p60tv-2.bQGCmu > div > div.sc-19qj39o-0.iLmCeO > div > div.sc-rq8xzv-1.kqcJKZ.sc-11ptd2v-1.liHMCp > div
        clearAndEnter(By.cssSelector("input[name=\"email\"]"), LOGIN);
        clearAndEnter(By.cssSelector("input[type=\"password\"]"), PASSWORD);
        WebElement buttonIn = driver.findElement(By.xpath("//div[parent::button[contains(@type, 'button')] and contains(text(),'Войти')]"));
        waiters.waitElementVisible(By.xpath("//div[parent::button[contains(@type, 'button')] and contains(text(),'Войти')]"));
        clickElement(buttonIn);
        log.info("Authorized successfully");
    }


    private void enterLK() {
        actions = new Actions(driver);
        WebElement elProfile = driver.findElement(By.cssSelector(".sc-199a3eq-0.fJMWHf"));
        waiters.waitElementToBeClickable(elProfile);
        actions.moveToElement(elProfile).perform();

        WebElement elMyProfile = driver.findElement(By.xpath(" //a[contains(text(), 'Мой профиль')]"));
        waiters.waitElementVisible(elMyProfile);
        elMyProfile.click();

        log.info("Выполнен вход в Мой Профиль");
    }


    private void fillPersonalInformation() {
//        Имя
        clearAndEnter(By.id("id_fname"), "Игнатий");
//        Выбор страны
        driver.findElement(By.xpath("//div[parent::label/child::input[contains(@name, 'country')]]")).click();
        driver.findElement(By.cssSelector("button[title='Россия']")).click();

//        Имя (в блоге), фамилия + имя, фамилия латиницей
        clearAndEnter(By.id("id_lname"), "Макаров");
        clearAndEnter(By.id("id_fname_latin"), "Ignatiy");
        clearAndEnter(By.id("id_lname_latin"), "Makaroff");
        clearAndEnter(By.id("id_blog_name"), "Ignatish");

//        Дата рождения
        clearAndEnter(By.name("date_of_birth"), "08.03.1988");

        log.info("Добавлены персональные данные пользователя");

//        Основная информация
//        Город
        driver.findElement(By.xpath("//div[ancestor::label/child::input[contains(@name, 'city')]]")).click();
        driver.findElement(By.xpath("//button[@data-value='136']")).click();

//        Уровень английского языка
        driver.findElement(By.xpath("//input[@data-title='Уровень знания английского языка']/../..")).click();
        driver.findElement(By.xpath("//button[contains(@title,'Начальный уровень (Beginner)')]")).click();

//        Готовность к переезду
        WebElement elMoving = driver.findElement(By.xpath("//span[contains(text(),'Нет')]"));
        if (!elMoving.isSelected()) {
            elMoving.click();
        }

//        Формат работы
//        Полный день
        WebElement elFullDay = driver.findElement(By.xpath("//input[@title='Полный день']/../.."));

        boolean a = elFullDay.isSelected(); // isSelected возвращает все время false
        if (a) {
            elFullDay.click();
        }


//       Гибкий график
        WebElement elflexiblesSchedule = driver.findElement(By.xpath("//input[@title='Гибкий график']/../.."));
        boolean a1 = elflexiblesSchedule.isSelected();
        if (a1) {
            elflexiblesSchedule.click();
        }

//       Удаленно
        WebElement elDist = driver.findElement(By.xpath("//input[@title='Удаленно']/../.."));
        if (elDist.isSelected()) {
            elDist.click();
            log.info("Добавлена основная информация пользователя");
        }
    }

//       Контактная информация
    private void fillContacts() {

        driver.findElement(By.xpath("//div[ancestor::label/child::input[contains(@value, 'telegram')]]")).click();
        clearAndEnter(By.id("id_contact-0-value"), "https://vk.com/fake");
        driver.findElement(By.xpath("//button[contains(text(),'Добавить')]")).click();
        driver.findElement(By.xpath("//span[@class='placeholder']")).click();
        driver.findElement(By.xpath("//div[ancestor::label/child::input[contains(@value, 'vk')]]")).click();
        clearAndEnter(By.id("id_contact-1-value"), "@Ignatiy");

        log.info("Добавлена контактная информация пользователя");


    }

//       Другое
    private void fillAdditionalInfo() {
//       Пол
        if (!(driver.findElement(By.xpath("//div[@class='select select_full']")).isSelected())) {
            driver.findElement(By.xpath("//option[@value='m']")).click();
        }

//       Компания
        clearAndEnter(By.id("id_company"), "OTUS training");

//       Должность
        clearAndEnter(By.id("id_work"), "Team lead");

        log.info("Добавлена прочая информация пользователя");
    }

    private void fillExperience() {

        driver.findElement(By.cssSelector("a[title='Добавить']")).click();
        driver.findElement(By.cssSelector("#id_experience-0-experience")).click();
        driver.findElement(By.cssSelector("#id_experience-0-experience > option:nth-child(3)")).click();
        driver.findElement(By.cssSelector("#id_experience-0-level > option:nth-child(1)")).click();
        log.info("Добавление информации об опыте разработки пользователя");
    }

    private void assertionsLK() {
        Assertions.assertEquals("Игнатий", driver.findElement(By.id("id_fname")).getAttribute("value"));
        Assertions.assertEquals("Макаров", driver.findElement(By.id("id_lname")).getAttribute("value"));
        Assertions.assertEquals("Ignatiy", driver.findElement(By.id("id_fname_latin")).getAttribute("value"));
        Assertions.assertEquals("Makaroff", driver.findElement(By.id("id_lname_latin")).getAttribute("value"));
        Assertions.assertEquals("Ignatish", driver.findElement(By.id("id_blog_name")).getAttribute("value"));
        Assertions.assertEquals("08.03.1988", driver.findElement(By.name("date_of_birth")).getAttribute("value"));


        Assertions.assertNotNull( driver.findElement(By.xpath("//div[parent::label/child::input[contains(@name, 'country')] and contains(text(), 'Россия')]")));
        Assertions.assertNotNull( driver.findElement(By.xpath("//div[parent::label/child::input[contains(@name, 'city')] and contains(text(), 'Брянск')]")));
        Assertions.assertNotNull( driver.findElement(By.xpath("//div[parent::label/child::input[contains(@name, 'english_level')] and contains(text(), 'Начальный уровень (Beginner)')]")));

        log.info("В разделе о себе отображаются указанные ранее данные");
    }

    private void clearAndEnter(By by, String text) {
        WebElement webElement = driver.findElement(by);
        String js = "arguments[0].click();";
        ((JavascriptExecutor) driver).executeScript(js, webElement);
        webElement.clear();
        driver.findElement(by).sendKeys(text);
    }

    private void clickElement(WebElement webElement) {
        String js = "arguments[0].click();";
        ((JavascriptExecutor) driver).executeScript(js, webElement);
    }

}
