package checkGoogle;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;

public class WebDriverFactory {

    public static WebDriver getRemoteChromeDriver() {
        DesiredCapabilities capability = DesiredCapabilities.chrome();
        return new RemoteWebDriver(capability);
    }

    public static WebDriver getLocalChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver 2");
        return new ChromeDriver();
    }

    public static WebDriver getSmartChromeDriver() {
        WebDriver driver;
        try {
            driver = getRemoteChromeDriver();
        } catch (UnreachableBrowserException e) {
            driver = getLocalChromeDriver();
        }

        return driver;
    }

}
