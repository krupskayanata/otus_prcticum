package org.task.components;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.task.pageobject.AbsPageObject;

public class ProfilePage extends AbsPageObject {

    private final Logger log = LogManager.getLogger(ProfilePage.class);
    public ProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void clickSaveButton() {
        webDriver.findElement(By.cssSelector("button[title=\"Сохранить и заполнить позже\"]")).submit();
        log.info("Information was saved successfully");
    }

    public void fillPersonalInformation() {
//        Имя
        clearAndEnter(By.id("id_fname"), "Игнатий");
//        Выбор страны
        webDriver.findElement(By.xpath("//div[parent::label/child::input[contains(@name, 'country')]]")).click();
        webDriver.findElement(By.cssSelector("button[title='Россия']")).click();

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
        webDriver.findElement(By.xpath("//div[ancestor::label/child::input[contains(@name, 'city')]]")).click();
        webDriver.findElement(By.xpath("//button[@data-value='136']")).click();

//        Уровень английского языка
        webDriver.findElement(By.xpath("//input[@data-title='Уровень знания английского языка']/../..")).click();
        webDriver.findElement(By.xpath("//button[contains(@title,'Начальный уровень (Beginner)')]")).click();

//        Готовность к переезду
        WebElement elMoving = webDriver.findElement(By.xpath("//span[contains(text(),'Нет')]"));
        if (!elMoving.isSelected()) {
            elMoving.click();
        }

//        Формат работы
//        Полный день
        WebElement elFullDay = webDriver.findElement(By.xpath("//input[@title='Полный день']/../.."));

        boolean a = elFullDay.isSelected(); // isSelected возвращает все время false
        if (a) {
            elFullDay.click();
        }


//       Гибкий график
        WebElement elflexiblesSchedule = webDriver.findElement(By.xpath("//input[@title='Гибкий график']/../.."));
        boolean a1 = elflexiblesSchedule.isSelected();
        if (a1) {
            elflexiblesSchedule.click();
        }

//       Удаленно
        WebElement elDist = webDriver.findElement(By.xpath("//input[@title='Удаленно']/../.."));
        if (elDist.isSelected()) {
            elDist.click();
            log.info("Добавлена основная информация пользователя");
        }
    }

    public void fillContacts() {

        webDriver.findElement(By.xpath("//div[ancestor::label/child::input[contains(@value, 'telegram')]]")).click();
        clearAndEnter(By.id("id_contact-0-value"), "https://vk.com/fake");
        webDriver.findElement(By.xpath("//button[contains(text(),'Добавить')]")).click();
        webDriver.findElement(By.xpath("//span[@class='placeholder']")).click();
        webDriver.findElement(By.xpath("//div[ancestor::label/child::input[contains(@value, 'vk')]]")).click();
        clearAndEnter(By.id("id_contact-1-value"), "@Ignatiy");

        log.info("Добавлена контактная информация пользователя");


    }

    //       Другое
    public void fillAdditionalInfo() {
//       Пол
        if (!(webDriver.findElement(By.xpath("//div[@class='select select_full']")).isSelected())) {
            webDriver.findElement(By.xpath("//option[@value='m']")).click();
        }

//       Компания
        clearAndEnter(By.id("id_company"), "OTUS training");

//       Должность
        clearAndEnter(By.id("id_work"), "Team lead");

        log.info("Добавлена прочая информация пользователя");
    }

    public void fillExperience() {

        webDriver.findElement(By.cssSelector("a[title='Добавить']")).click();
        webDriver.findElement(By.cssSelector("#id_experience-0-experience")).click();
        webDriver.findElement(By.cssSelector("#id_experience-0-experience > option:nth-child(3)")).click();
        webDriver.findElement(By.cssSelector("#id_experience-0-level > option:nth-child(1)")).click();
        log.info("Добавление информации об опыте разработки пользователя");
    }

    private void clearAndEnter(By by, String text) {
        WebElement webElement = webDriver.findElement(by);
        String js = "arguments[0].click();";
        ((JavascriptExecutor) webDriver).executeScript(js, webElement);
        webElement.clear();
        webDriver.findElement(by).sendKeys(text);
    }

    public void assertionsLK() {
        Assertions.assertEquals("Игнатий", webDriver.findElement(By.id("id_fname")).getAttribute("value"));
        Assertions.assertEquals("Макаров", webDriver.findElement(By.id("id_lname")).getAttribute("value"));
        Assertions.assertEquals("Ignatiy", webDriver.findElement(By.id("id_fname_latin")).getAttribute("value"));
        Assertions.assertEquals("Makaroff", webDriver.findElement(By.id("id_lname_latin")).getAttribute("value"));
        Assertions.assertEquals("Ignatish", webDriver.findElement(By.id("id_blog_name")).getAttribute("value"));
        Assertions.assertEquals("08.03.1988", webDriver.findElement(By.name("date_of_birth")).getAttribute("value"));


        Assertions.assertNotNull( webDriver.findElement(By.xpath("//div[parent::label/child::input[contains(@name, 'country')] and contains(text(), 'Россия')]")));
        Assertions.assertNotNull( webDriver.findElement(By.xpath("//div[parent::label/child::input[contains(@name, 'city')] and contains(text(), 'Брянск')]")));
        Assertions.assertNotNull( webDriver.findElement(By.xpath("//div[parent::label/child::input[contains(@name, 'english_level')] and contains(text(), 'Начальный уровень (Beginner)')]")));

        log.info("В разделе о себе отображаются указанные ранее данные");
    }
}
