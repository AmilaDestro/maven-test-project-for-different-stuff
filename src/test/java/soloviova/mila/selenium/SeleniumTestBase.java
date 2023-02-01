package soloviova.mila.selenium;

import org.junit.After;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class SeleniumTestBase {
    WebDriver webDriver;
    JavascriptExecutor jsExecutor;
    WebDriverWait wait;
    Actions actions;

    @After
    public void quitDriver() {
        webDriver.quit();
    }
}
