package smartWebDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public abstract class AbstractWebDriverFactory {

    public abstract WebDriver getRemoteWebDriver(String seleniumHubUrl) throws MalformedURLException;

    public abstract WebDriver getLocalWebDriver();

    public WebDriver getRemoteWebDriverFromHub(String seleniumHubUrl, DesiredCapabilities capability) throws MalformedURLException {
        return new RemoteWebDriver(new URL(seleniumHubUrl), capability);
    }

}
