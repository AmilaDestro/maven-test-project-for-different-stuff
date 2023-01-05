package soloviova.mila.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features/palindrome.feature", glue = "soloviova/mila/cucumber")
public class PalindromeCheckerTestRunner {
}
