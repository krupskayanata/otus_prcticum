package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Set;
//import org.junit.jupiter.api.Test;


public class TestTask {
    Logger log = LogManager.getLogger(TestTask.class);


    @BeforeAll
    public static void setup(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void start() {

    }

    @AfterEach
    public void shutdown() {
    }

    @Test
    // открытие браузера режиме "headless"
    public void firstTest(){

        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        WebDriver driver = new ChromeDriver(options);

        // проверка результата поисковой выдачи
        driver.get("https://duckduckgo.com/");
        WebElement webElement = driver.findElement(By.id("searchbox_input"));
        webElement.clear();
        webElement.sendKeys("ОТУС");
        WebElement button = driver.findElement(By.xpath("//*[@id=\"searchbox_homepage\"]/div/div/button"));
        button.submit();
        String actual = driver.getPageSource();
        String expected = "Онлайн‑курсы для профессионалов, дистанционное обучение современным";
        Assertions.assertTrue(actual.contains(expected));

        driver.quit();
    }

    @Test
    // открытие браузера в режиме "киоск"
    public void secondTest(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("kiosk");
        WebDriver driver = new ChromeDriver(options);

        // проверка открытия картинки в модальном окне
        driver.get("https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818");
        WebElement webElement = driver.findElement(By.xpath("/html/body/section[2]/div/ul[2]/li[2]/span/a/div[1]"));
        webElement.click();
        Exception error = null;
        try {
            WebElement modalWindow = webElement.findElement(By.xpath("/html/body/div[7]/div[3]"));
        } catch (org.openqa.selenium.NoSuchElementException ex) {
            error = ex;
        }
        driver.quit();
        Assertions.assertNull(error);

    }

    @Test
    // открытие браузера в полноэкранном режиме
    public void thirdTest(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-fullscreen");
        WebDriver driver = new ChromeDriver(options);


        // авторизация на сайте https://otus.ru
        driver.get("https://otus.ru");
        WebElement webElement = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[1]/div[1]/div/section/div[1]/button"));
        webElement.click();
        String actual = driver.getPageSource();
        String expected = "Войдите в свой аккаунт";
        Assertions.assertTrue(actual.contains(expected));
        WebElement inputLogin = driver.findElement(By.name("email"));
        inputLogin.sendKeys("semabutus@mail.ru");
        WebElement inputPassword = driver.findElement(By.xpath("//*[@id=\"__PORTAL__\"]/div/div/div[3]/div[2]/div/div[2]/div[1]/div/div[1]/div/div[2]/div/input"));
        inputPassword.sendKeys("Aa1313++");
        WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"__PORTAL__\"]/div/div/div[3]/div[2]/div/div[2]/div[1]/div/button/div"));
        submitButton.click();

        Set<Cookie> cookies = driver.manage().getCookies();
        log.info("Cookies");
        cookies.forEach(cookie -> log.info("Cookie {}", cookie.toString()));

        driver.quit();

    }


}
