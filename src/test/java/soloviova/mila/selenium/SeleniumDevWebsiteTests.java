package soloviova.mila.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * A simple test that checks whether Selenium official website page is available.
 * Uses JUnit assertions.
 */
public class SeleniumDevWebsiteTests extends SeleniumTestBase {

    @Before
    public void setupWebDriver() {
        webDriver = WebDriverManager.chromedriver().create();
    }

    @Test
    public void openSite() {
        webDriver.navigate().to("https://www.selenium.dev/");
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(2L));

        assertEquals("The web page title is NOT 'Selenium'", webDriver.getTitle(), "Selenium");

        final WebElement seleniumLogo = webDriver.findElement(By.id("selenium_logo"));

        assertNotNull("Web Element 'selenium_logo' was not found", seleniumLogo);
    }
}
