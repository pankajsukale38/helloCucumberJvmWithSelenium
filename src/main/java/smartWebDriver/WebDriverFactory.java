package smartWebDriver;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverFactory {

    public static WebDriver getRemoteChromeDriver(String seleniumHubUrl) throws MalformedURLException {
        DesiredCapabilities capability = DesiredCapabilities.chrome();
        return new RemoteWebDriver(new URL(seleniumHubUrl), capability);
    }

    public static WebDriver getLocalChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver 2");
        return new ChromeDriver();
    }

    public static WebDriver getSmartChromeDriver() {
        WebDriver driver;
        String seleniumHubUrl = getSeleniumHubUrl();

        if (seleniumHubUrl != null) {
            try {
                System.out.println("SELENIUM_HUB em " + seleniumHubUrl);
                driver = getRemoteChromeDriver(seleniumHubUrl);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("SELENIUM_LOCAL");
            if (SystemUtils.IS_OS_WINDOWS) {
                driver = getLocalInternetExplorerDriver();
            } else {
                driver = getLocalChromeDriver();
            }
        }

        return driver;
    }

    private static WebDriver getLocalInternetExplorerDriver() {
        return new InternetExplorerDriver();
    }

    public static String getSeleniumHubUrl() {
        return System.getProperty("selenium.hub.url", System.getenv("SELENIUM_HUB_URL"));
    }

}
