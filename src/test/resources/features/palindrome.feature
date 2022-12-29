Feature: Palindromic
  As a user
  I want to have a function that checks if its String argument is palindrome
  So that I didn't have to perform the check myself

    Scenario Outline: Check if any String <valueToCheck> is palindrome <checkResult> ignoring its case and spaces
      Given the String with value <valueToCheck>
      When I check the passed String parameter by isPalindrome function
      Then it returns the result <checkResult>

      Examples:
      | valueToCheck | checkResult |
      | AAA          | true        |
      | Qwerty       | false       |
      | Fff FF       | true        |
      | 1234as_Df    | false       |
