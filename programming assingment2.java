public class CreditCardValidator {

    /** Check if the credit card number is valid */
    public static boolean isValid(long number) {
        // Check length and starting digits
        if (!hasValidLength(number) || !hasValidPrefix(number)) {
            return false;
        }

        // Validate using the Luhn algorithm
        int sum = sumOfDoubleEvenPlace(number) + sumOfOddPlace(number);
        return sum % 10 == 0;
    }

    /** Return true if the card length is between 13 and 16 digits */
    private static boolean hasValidLength(long number) {
        int length = String.valueOf(number).length();
        return length >= 13 && length <= 16;
    }

    /** Return true if the card number starts with a valid prefix */
    private static boolean hasValidPrefix(long number) {
        return prefixMatched(number, 4) || prefixMatched(number, 5) ||
               prefixMatched(number, 37) || prefixMatched(number, 6);
    }

    /** Return true if the prefix matches the number */
    private static boolean prefixMatched(long number, int d) {
        int prefixLength = String.valueOf(d).length();
        return getPrefix(number, prefixLength) == d;
    }

    /** Get the prefix of the number with the specified number of digits */
    private static long getPrefix(long number, int k) {
        String numStr = String.valueOf(number);
        return numStr.length() > k ? Long.parseLong(numStr.substring(0, k)) : number;
    }

    /** Get the sum of doubled even-place digits in the card number */
    private static int sumOfDoubleEvenPlace(long number) {
        int sum = 0;
        String numStr = String.valueOf(number);
        for (int i = numStr.length() - 2; i >= 0; i -= 2) {
            int digit = Character.getNumericValue(numStr.charAt(i)) * 2;
            sum += digit > 9 ? digit - 9 : digit;
        }
        return sum;
    }

    /** Get the sum of odd-place digits in the card number */
    private static int sumOfOddPlace(long number) {
        int sum = 0;
        String numStr = String.valueOf(number);
        for (int i = numStr.length() - 1; i >= 0; i -= 2) {
            sum += Character.getNumericValue(numStr.charAt(i));
        }
        return sum;
    }

    /** Main method for testing */
    public static void main(String[] args) {
        long[] testNumbers = {
            4388576018402626L, // Invalid Visa
            4532015112830366L, // Valid Visa
            6011000990139424L, // Valid Discover
            371449635398431L,  // Valid American Express
            5111111111111111L  // Invalid MasterCard
        };

        for (long number : testNumbers) {
            System.out.println("Card number: " + number + " is " + 
                (isValid(number) ? "valid" : "invalid"));
        }
    }
}
