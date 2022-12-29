package soloviova.mila.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import soloviova.mila.main.palindrome.PalindromeSearcher;
import soloviova.mila.main.palindrome.Palindromic;

import static org.junit.Assert.assertFalse;

public class PalindromeCheckerNegativeTestSteps {
    private final Palindromic palindromicInstance = new PalindromeSearcher();
    private String valueToCheck;
    private Boolean checkResult;

    @Given("the String with value 'Qwerty'")
    public void given_non_palindrome_String() {
        valueToCheck = "01 Qwerty";
    }

    @When("I check the given non-palindrome String by isPalindrome method")
    public void check_the_given_non_palindrome_String() {
        checkResult = palindromicInstance.isPalindrome(valueToCheck);
    }

    @Then("the result should be false")
    public void verify_the_result_is_false() {
        assertFalse(checkResult);
    }
}
