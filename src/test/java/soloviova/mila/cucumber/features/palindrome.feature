Feature: Palindromic
  As a user
  I want to have a function that checks if its String argument is palindrome
  So that I didn't have to perform the check myself

    Scenario Outline: Check if any String <ValueToCheck> is palindrome <CheckResult> ignoring its case and spaces
      Given the String with <ValueToCheck> value
      When I check the passed String parameter by any isPalindrome function
      Then it returns the result <CheckResult>

      Examples:
      | ValueToCheck | CheckResult |
      | "AAA"        | "true"        |
      | "Qwerty"     | "false"       |
      | "Fff FF"     | "true"        |
      | "1234as_Df"  | "false"       |
