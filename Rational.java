// Rational.java - Defines a class for Rational numbers
public class Rational {
    private int numerator;
    private int denominator;

    // Constructor
    public Rational(int numerator, int denominator) {
        if (denominator == 0) {
            throw new ArithmeticException("Denominator cannot be zero");
        }
        // Reduce fraction to lowest terms
        int gcd = gcd(Math.abs(numerator), Math.abs(denominator));
        this.numerator = numerator / gcd;
        this.denominator = denominator / gcd;

        // Ensure denominator is always positive
        if (this.denominator < 0) {
            this.numerator = -this.numerator;
            this.denominator = -this.denominator;
        }
    }

    // Method to compute GCD using Euclidean Algorithm
    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // Getter methods
    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    // Method to display rational number
    @Override
    public String toString() {
        return denominator == 1 ? numerator + "" : numerator + "/" + denominator;
    }

    // Addition of two rational numbers
    public Rational add(Rational other) {
        int newNumerator = this.numerator * other.denominator + other.numerator * this.denominator;
        int newDenominator = this.denominator * other.denominator;
        return new Rational(newNumerator, newDenominator);
    }
}