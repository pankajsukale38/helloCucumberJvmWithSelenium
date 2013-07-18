package smartWebDriver;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

public class SmartWebDriverFactory {

    public static WebDriver getWebDriver() {
        WebDriver driver;

        AbstractWebDriverFactory factory;
        if (SystemUtils.IS_OS_WINDOWS) {
            factory = new InternetExplorerDriverFactory();
        } else {
            factory = new ChromeWebDriverFactory();
        }

        String seleniumHubUrl = getSeleniumHubUrl();
        if (seleniumHubUrl != null) {
            System.out.println("SELENIUM_HUB em " + seleniumHubUrl);
            try {
                driver = factory.getRemoteWebDriver(seleniumHubUrl);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("SELENIUM_LOCAL");
            driver = factory.getLocalWebDriver();
        }

        return driver;
    }


    public static String getSeleniumHubUrl() {
        return System.getProperty("selenium.hub.url", System.getenv("SELENIUM_HUB_URL"));
    }

}
