package soloviova.mila.main.palindrome;

import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * This class contains different methods that define if used String is Palindrome
 * (palindromic Strings are equal if we read them in any order).
 * Implements interface {@link Palindromic}
 */
@NoArgsConstructor
public class PalindromeSearcher implements Palindromic {
    @Override
    public boolean isPalindrome(String stringToCheck) {
        final String stringWithoutSpaces = getStringWithoutSpaces(stringToCheck);
        final String reversedString = new StringBuilder(stringWithoutSpaces).reverse().toString();

        return reversedString.equals(stringWithoutSpaces);
    }

    public boolean isPalindromeUsingArrays(final String stringToCheck) {
        final String stringWithoutSpaces = getStringWithoutSpaces(stringToCheck);
        final char[] charsInTheString = stringWithoutSpaces.toCharArray();

        final StringBuilder builder = new StringBuilder();
        for (int i = charsInTheString.length - 1; i >= 0; i--) {
            builder.append(charsInTheString[i]);
        }

        return builder.toString().equals(stringWithoutSpaces);
    }

    public boolean isPalindromeNoExtraLibraries(final String stringToCheck) {
        final char[] chars = getStringWithoutSpaces(stringToCheck).toCharArray();

        for (int i = 0, j = chars.length - 1; i < chars.length && j >= 0; i++, j--) {
            if (!String.valueOf(chars[i]).equalsIgnoreCase(String.valueOf(chars[j]))) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindromeUsingApacheCommon(final String stringToCheck) {
        final String stringWithoutSpaces = getStringWithoutSpaces(stringToCheck);
        final String reversedString = StringUtils.reverse(stringWithoutSpaces);

        return reversedString.equals(stringWithoutSpaces);
    }

    private String getStringWithoutSpaces(final String originalString) {
        return originalString.replaceAll("\\s+", "").toLowerCase();
    }
}
