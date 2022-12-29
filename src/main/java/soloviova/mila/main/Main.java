package soloviova.mila.main;

import soloviova.mila.main.palindrome.PalindromeSearcher;
import soloviova.mila.main.palindrome.Palindromic;

import java.util.Scanner;

public class Main {

    private static final Palindromic palindromicInstance = new PalindromeSearcher();
    public static void main(String[] args) {
        System.out.println("Enter a string to check if it is palindrome:\n");
        final Scanner scanner = new Scanner(System.in);
        final String inputResult = scanner.nextLine();

        final boolean result = ((PalindromeSearcher) palindromicInstance).isPalindromeUsingArrays(inputResult);
        final String isPalindromeString = result ? "IS" : "is NOT";
        System.out.printf("The String you have entered [%s] %s palindrome%n", inputResult, isPalindromeString);
    }
}
