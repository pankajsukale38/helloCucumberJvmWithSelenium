package checkGoogle;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.seleniumhq.selenium.fluent.FluentWebDriver;
import org.seleniumhq.selenium.fluent.FluentWebElement;
import org.seleniumhq.selenium.fluent.Period;
import smartWebDriver.WebDriverFactory;

public class GoogleStepdefs {
    private WebDriver driver;
    private FluentWebDriver fluentWebDriver;

    @Before
    public void before() {
        driver = WebDriverFactory.getSmartChromeDriver();
        fluentWebDriver = new FluentWebDriver(driver);
    }


    @After
    public void after() {
        driver.quit();
    }

    @Dado("^que acessei o motor de busca$")
    public void que_acessei_o_motor_de_busca() throws Throwable {
        driver.get("http://www.google.com");

        fluentWebDriver.within(Period.secs(10)).title().shouldBe("Google");
    }

    @Quando("^pesquiso por Cucumber$")
    public void pesquiso_por_Cucumber() throws Throwable {
        final FluentWebElement searchTextElement = fluentWebDriver.input(By.name("q"));
        searchTextElement.sendKeys("Cucumber");
        searchTextElement.submit();
    }

    @Entao("^encontro diversos links$")
    public void encontro_diversos_links() throws Throwable {
        fluentWebDriver.within(Period.secs(10)).title().shouldMatch("^Cucumber.*$");
    }
}
