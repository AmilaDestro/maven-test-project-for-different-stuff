package soloviova.mila.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.attributeContains;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementSelectionStateToBe;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlToBe;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * Contains some Selenium tests
 */
public class SeleniumChromeDriverTests extends SeleniumTestBase {

    @Before
    public void setupWebDriver() {
        webDriver = WebDriverManager.chromedriver().create();
        jsExecutor = (JavascriptExecutor) webDriver;
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(5L));
        actions = new Actions(webDriver);
    }

    @Test
    public void openSite() {
        webDriver.get("https://www.selenium.dev/");

        wait.until(visibilityOfElementLocated(By.id("selenium_logo")));
        assertEquals("The web page title is NOT 'Selenium'", webDriver.getTitle(), "Selenium");
    }

    @Test
    public void testScroll() {
        final String expectedName = "John Shepard";
        final String expectedBirthDate = "04/12/2154";

        webDriver.get("https://formy-project.herokuapp.com/scroll");
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(2L));

        WebElement nameInputField = webDriver.findElement(By.id("name"));
        actions.moveToElement(nameInputField);
        nameInputField.click();
        nameInputField.sendKeys(expectedName);

        WebElement birthDateInputField = webDriver.findElement(By.id("date"));
        birthDateInputField.click();
        birthDateInputField.sendKeys(expectedBirthDate);

        final String actualName = nameInputField.getAttribute("value");
        final String actualBirthDate = birthDateInputField.getAttribute("value");

        assertEquals(format("Unexpected name - [%s], expected - [%s]", actualName, expectedName),
                expectedName, actualName);
        assertEquals(format("Unexpected birth date - [%s], expected - [%s]", actualBirthDate, expectedBirthDate),
                expectedBirthDate, actualBirthDate);
    }

    @Test
    public void testSwitchWindow() {
        webDriver.get("https://formy-project.herokuapp.com/switch-window");
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(3L));

        WebElement newTabButton = webDriver.findElement(By.id("new-tab-button"));
        newTabButton.click();

        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1L));

        final String originalWindow = webDriver.getWindowHandle();
        String newWindow = "";

        newWindow = webDriver.getWindowHandles().stream()
                .filter(handle -> !handle.equalsIgnoreCase(originalWindow))
                .findFirst().orElse(newWindow);

        webDriver.switchTo().window(originalWindow);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1L));

        webDriver.switchTo().window(newWindow);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3L));
        webDriver.close();

        Set<String> windowHandles = webDriver.getWindowHandles();
        assertEquals( 1, windowHandles.size());
        assertEquals(originalWindow, windowHandles.toArray()[0]);
    }

    @Test
    public void testAlert() {
        webDriver.get("https://formy-project.herokuapp.com/switch-window");
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(3L));

        WebElement alertButton = webDriver.findElement(By.id("alert-button"));
        alertButton.click();

        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = webDriver.switchTo().alert();
        assertEquals("This is a test alert!", alert.getText());
        alert.accept();
    }

    @Test
    public void testModalWindowWithJavascriptExecutor() {
        webDriver.get("https://formy-project.herokuapp.com/modal");
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(3L));

        WebElement modalButton = webDriver.findElement(By.id("modal-button"));
        modalButton.click();

        wait.until(visibilityOfElementLocated(By.id("exampleModal")));

        WebElement modalBody = webDriver.findElement(By.id("exampleModal"));
        WebElement modalCloseButton = webDriver.findElement(By.id("close-button"));
        modalCloseButton.click();
        wait.until(invisibilityOf(modalBody));

        modalButton.click();
        wait.until(visibilityOfElementLocated(By.id("exampleModal")));
        jsExecutor.executeScript("arguments[0].click();", modalCloseButton);
        wait.until(invisibilityOf(modalBody));
    }

    @Test
    public void testDragAndDrop() {
        webDriver.get("https://formy-project.herokuapp.com/dragdrop");
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(3L));

        WebElement image = webDriver.findElement(By.id("image"));
        WebElement dropBox = webDriver.findElement(By.id("box"));

        actions.dragAndDrop(image, dropBox).build().perform();

        wait.until(visibilityOfElementLocated(By.cssSelector(".ui-widget-header.ui-droppable.ui-state-highlight")));
    }

    @Test
    public void testRadioButton() {
        webDriver.get("https://formy-project.herokuapp.com/radiobutton");
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(3L));

        WebElement radioButton2 = webDriver.findElement(By.cssSelector("input[value='option2']"));
        radioButton2.click();

        wait.until(elementSelectionStateToBe(radioButton2, true));

        WebElement radioButton3 = webDriver.findElement(By.xpath("/html/body/div/div[3]/input"));
        radioButton3.click();

        wait.until(elementSelectionStateToBe(radioButton3, true));
        wait.until(elementSelectionStateToBe(radioButton2, false));
    }

    @Test
    public void testCheckBox() {
        webDriver.get("https://formy-project.herokuapp.com/checkbox");
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(3L));

        WebElement checkbox1 = webDriver.findElement(By.id("checkbox-1"));
        WebElement checkBox2 = webDriver.findElement(By.cssSelector("input[value='checkbox-2']"));
        WebElement checkbox3 = webDriver.findElement(By.xpath("//*[@id=\"checkbox-3\"]"));

        checkbox1.click();
        wait.until(elementSelectionStateToBe(checkbox1, true));

        checkBox2.click();
        wait.until(elementSelectionStateToBe(checkBox2, true));

        checkbox3.click();
        wait.until(elementSelectionStateToBe(checkbox3, true));
    }

    @Test
    public void testDatePicker() {
        final String date = "08/20/2021";

        webDriver.get("https://formy-project.herokuapp.com/datepicker");
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(3L));

        WebElement datePickerField = webDriver.findElement(By.id("datepicker"));
        datePickerField.sendKeys(date);
        datePickerField.sendKeys(Keys.ENTER);

        wait.until(attributeContains(datePickerField, "value", date));
    }

    @Test
    public void testDropDownMenu() {
        webDriver.get("https://formy-project.herokuapp.com/dropdown");
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(3L));

        WebElement dropDownButton = webDriver.findElement(By.id("dropdownMenuButton"));
        dropDownButton.click();

        WebElement autoCompleteItem = webDriver.findElement(By.id("autocomplete"));
        wait.until(visibilityOf(autoCompleteItem));

        autoCompleteItem.click();
        wait.until(invisibilityOf(autoCompleteItem));
        wait.until(urlToBe("https://formy-project.herokuapp.com/autocomplete"));
    }
}
