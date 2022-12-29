Feature: Palindromic
  As a user
  I want to have a function that checks if its String argument is palindrome
  So that I didn't have to perform the check myself

  Scenario: Verify that the String 'AAA' is palindrome (positive case)
    Given the String with value 'AAA'
    When I check the given palindrome String by isPalindrome method
    Then the result should be true

  Scenario: Verify that the String '01 Qwerty' is not palindrome
    Given the String with value 'Qwerty'
    When I check the given non-palindrome String by isPalindrome method
    Then the result should be false
