package soloviova.mila.cucumber.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/java/soloviova/mila/cucumber/features"},
        glue = {"soloviova/mila/cucumber/stepdefinitions"},
        publish = true
)
public class PalindromeCheckerTest {
}
