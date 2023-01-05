package soloviova.mila.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import soloviova.mila.main.palindrome.PalindromeSearcher;
import soloviova.mila.main.palindrome.Palindromic;

import static org.junit.Assert.assertEquals;

public class PalindromeCheckerTestSteps {
    private final Palindromic palindromicInstance = new PalindromeSearcher();
    private String valueToCheck;
    private Boolean checkResult;

    @Given("^the String with value (\\w*\\s*\\w*)$")
    public void init_string_value_to_check(final String valueToCheck) {
        this.valueToCheck = valueToCheck;
    }

    @When("^I check the passed String parameter by any isPalindrome function$")
    public void check_if_the_string_is_palindrome() {
        checkResult = ((PalindromeSearcher) palindromicInstance).isPalindromeNoExtraLibraries(valueToCheck);
    }

    @Then("^it returns the result (\\S+)$")
    public void verify_result(final String expectedResult) {
        assertEquals(checkResult, Boolean.parseBoolean(expectedResult));
    }
}
