package org.task;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.task.waiter.Waiters;

import java.util.Set;


public class TaskTest extends AbstractBaseTest {

    private final Logger log = LogManager.getLogger(TaskTest.class);
    private static final String js = "arguments[0].click();";

    @Test
    public void firstTest(){

        // проверка результата поисковой выдачи
        driver.get("https://duckduckgo.com/");
        WebElement webElement = driver.findElement(By.id("searchbox_input"));
        webElement.clear();
        webElement.sendKeys("ОТУС");
        WebElement button = driver.findElement(By.xpath("//*[@id=\"searchbox_homepage\"]/div/div/button"));
        button.submit();
        waiters.waitForCondition(ExpectedConditions.invisibilityOf(button));
        String actual = driver.getPageSource();
        String expected = "Онлайн‑курсы для профессионалов";
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
        WebElement webElement = driver.findElement(By.xpath("//a[contains(@href,'assets/images/p2.jpg')]"));
        webElement.click();
        Exception error = null;
        try {
            WebElement modalWindow = driver.findElement(By.xpath("//div[contains(@class,'pp_hoverContainer')]"));
        } catch (org.openqa.selenium.NoSuchElementException ex) {
            error = ex;
        }
        driver.quit();
        Assertions.assertNull(error);

    }

    @Test
    // открытие браузера в полноэкранном режиме
    public void thirdTest() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-fullscreen");
        WebDriver driver = new ChromeDriver(options);


        // авторизация на сайте https://otus.ru
        driver.get("https://otus.ru");
        WebElement webElement = driver.findElement(By.xpath("//button[text()='Войти']"));
        webElement.click();
        String actual = driver.getPageSource();
        String expected = "Войдите в свой аккаунт";
        Assertions.assertTrue(actual.contains(expected));
        WebElement inputLogin = driver.findElement(By.name("email"));
        ((JavascriptExecutor) driver).executeScript(js, inputLogin);
        inputLogin.sendKeys("semabutus@mail.ru");

        WebElement inputPassword = driver.findElement(By.xpath("//input[contains(@type, 'password')]"));
        ((JavascriptExecutor) driver).executeScript(js, inputPassword);
        waiters = new Waiters(driver);
        waiters.waitElementToBeClickable(inputPassword);
        ((JavascriptExecutor) driver).executeScript(js, inputPassword);
        inputPassword.sendKeys("Aa1313++");

        WebElement submitButton = driver.findElement(By.xpath("//div[text()='Войти']"));
        submitButton.click();

        Set<Cookie> cookies = driver.manage().getCookies();
        log.info("Cookies");
        cookies.forEach(cookie -> log.info("Cookie {}", cookie.toString()));

        driver.quit();

    }


}
