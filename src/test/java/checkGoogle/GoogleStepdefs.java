package checkGoogle;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleStepdefs {
    private WebDriver driver;

    @Before
    public void before() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver 2");
        driver = new ChromeDriver();
    }

    @After
    public void after() {
        driver.quit();
    }

    @Dado("^que acessei o motor de busca$")
    public void que_acessei_o_motor_de_busca() throws Throwable {
        driver.get("http://www.google.com");
        System.out.println(driver.getTitle());
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("google");
            }
        });
    }

    @Quando("^pesquiso por Cucumber$")
    public void pesquiso_por_Cucumber() throws Throwable {
        WebElement searchTextElement = driver.findElement(By.name("q"));
        searchTextElement.sendKeys("Cucumber");
        searchTextElement.submit();
    }

    @Entao("^encontro diversos links$")
    public void encontro_diversos_links() throws Throwable {
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("cucumber");
            }
        });
    }
}
