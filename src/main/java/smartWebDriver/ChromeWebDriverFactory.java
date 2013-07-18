package smartWebDriver;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;

public class ChromeWebDriverFactory extends AbstractWebDriverFactory {
    protected static final String DRIVERS_HOME = "./drivers";

    @Override
    public WebDriver getRemoteWebDriver(String seleniumHubUrl) throws MalformedURLException {
        DesiredCapabilities capability = DesiredCapabilities.chrome();
        return getRemoteWebDriverFromHub(seleniumHubUrl, capability);
    }

    @Override
    public WebDriver getLocalWebDriver() {
        String driverUri;
        if (SystemUtils.IS_OS_MAC) {
            driverUri = DRIVERS_HOME + "/chromedriver_mac/chromedriver 3";
        } else if (SystemUtils.IS_OS_LINUX) {
            driverUri = DRIVERS_HOME + "/chromedriver_linux/chromedriver";
        } else if (SystemUtils.IS_OS_WINDOWS) {
            driverUri = DRIVERS_HOME + "/chromedriver_win/chromedriver.exe";
        } else {
            throw new RuntimeException("Usupported Operationg System [" + SystemUtils.OS_NAME + "]");
        }
        System.setProperty("webdriver.chrome.driver", driverUri);
        return new ChromeDriver();
    }
}
