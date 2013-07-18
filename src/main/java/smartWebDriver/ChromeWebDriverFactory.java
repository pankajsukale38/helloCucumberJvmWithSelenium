package smartWebDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;

public class ChromeWebDriverFactory extends AbstractWebDriverFactory {
    @Override
    public WebDriver getRemoteWebDriver(String seleniumHubUrl) throws MalformedURLException {
        DesiredCapabilities capability = DesiredCapabilities.chrome();
        return getRemoteWebDriverFromHub(seleniumHubUrl, capability);
    }

    @Override
    public WebDriver getLocalWebDriver() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver 2");
        return new ChromeDriver();
    }
}
