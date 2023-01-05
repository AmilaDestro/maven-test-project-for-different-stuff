package soloviova.mila.selenium;

import org.junit.After;
import org.openqa.selenium.WebDriver;

public abstract class SeleniumTestBase {
    WebDriver webDriver;

    @After
    public void quitDriver() {
        webDriver.quit();
    }
}
