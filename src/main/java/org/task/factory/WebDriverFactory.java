package org.task.factory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WebDriverFactory {

    private final Logger log = LogManager.getLogger(WebDriverFactory.class);

    public RemoteWebDriver createBrowser() {
        log.info("BROWSER_NAME = {}", System.getProperty("BROWSER_NAME"));
        RemoteWebDriver remoteWebDriver;
        String optionVisibility = System.getProperty("OPTION_VISIBILITY");
        log.info("OPTION_VISIBILITY = {}", optionVisibility);
        switch (BrowserEnum.of(System.getProperty("BROWSER_NAME"))) {
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                if (optionVisibility != null) {
                    chromeOptions.addArguments("--start-maximized");
                }
                remoteWebDriver = new ChromeDriver(chromeOptions);
                break;
            case FIREFOX:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (optionVisibility != null) {
                    firefoxOptions.addArguments(optionVisibility);
                }
                remoteWebDriver = new FirefoxDriver(firefoxOptions);
                break;
            default:
                log.info("BROWSER_NAME = {}. Default driver chosen", System.getProperty("BROWSER_NAME"));
                ChromeOptions chromeOptionsDefault = new ChromeOptions();
                if (optionVisibility != null) {
                    chromeOptionsDefault.addArguments(optionVisibility);
                }
                remoteWebDriver = new ChromeDriver(chromeOptionsDefault);
                break;
        }

        return remoteWebDriver;
    }
}
