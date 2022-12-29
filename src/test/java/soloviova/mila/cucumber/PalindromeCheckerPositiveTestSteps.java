package soloviova.mila.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import soloviova.mila.main.palindrome.PalindromeSearcher;
import soloviova.mila.main.palindrome.Palindromic;

import static org.junit.Assert.assertTrue;


public class PalindromeCheckerPositiveTestSteps {

    private final Palindromic palindromicInstance = new PalindromeSearcher();
    private String valueToCheck;
    private Boolean checkResult;

    @Given("the String with value 'AAA'")
    public void given_palindrome_String_AAA() {
        valueToCheck = "AAA";
    }

    @When("I check the given palindrome String by isPalindrome method")
    public void check_the_given_palindrome_String() {
        checkResult = palindromicInstance.isPalindrome(valueToCheck);
    }

    @Then("the result should be true")
    public void verify_the_result_is_true() {
        assertTrue(checkResult);
    }
}
