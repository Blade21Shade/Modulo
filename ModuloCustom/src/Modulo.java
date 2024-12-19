// A project making a custom modulo operator

// Before starting this project I was thinking about how it would work, and figured that using some multiplication before
// the main process of checking would be better implementation than using pure recursion/looping

// However I decided to do both methods just to prove I could get both working
// These are only for int, I don't want to attempt making a custom float modulo

public class Modulo {
    public static void main(String[] args) throws Exception {
        int dividendValue = 10;
        int divisorValue = -3;
        System.out.printf("The remainder for %5d mod %5d is: %3d\n", dividendValue, divisorValue, moduloRecursionOuter(dividendValue, divisorValue));
        System.out.println("Java answer:                           " + dividendValue % divisorValue);
    }

    // The called recursion function for modulo
    // This does some logic checks before starting the recursion loop, then calls the inner function to actually do the recursion
    public static int moduloRecursionOuter(int dividend, int divisor) {
        
        // Check for 0s before do anything else
        if (dividend == 0) {
            return 0;
        }

        if (divisor == 0) {
            return dividend;
        }

        // Value returned from this function 
        int remainder = 0;

        // Set up how returning is handled
        // Online I found that different languages handle it different ways, so I'm going to implement how Google does it
        boolean dividendNegativeSolo = false; // Only dividend is negative, will return a positive number
        boolean divisorNegativeSolo = false; // Only divisor is negative, will return a negative number 
        boolean bothNegative = false; // Both are negative, will return a negative number
        
        // Set booleans as needed
        if (dividend < 0 && divisor > 0) {
            dividendNegativeSolo = true;
        } else if (dividend > 0 && divisor < 0) {
            divisorNegativeSolo = true;
        } else if (dividend < 0 && divisor < 0) {
            bothNegative = true;
            dividend *= -1; // Inverse the signs as this math will be equivalent to 
            divisor *= -1;
        }

        // If the dividend and the divisor are the same sign and the divisor is greater than the dividend, return the dividend
        if (!dividendNegativeSolo && !divisorNegativeSolo && divisor > dividend) {
            if (bothNegative) {
                dividend *= -1;
            }
            return remainder = dividend;
        }

        // Actual recursion
        if (dividendNegativeSolo) {
            dividend *= -1; // Recursive function uses both being positive, corrects remainder at the end of the recursive call
            remainder = moduloRecursionDivisorXorDividendNeg(dividend, divisor, divisor, false);
        } else if (divisorNegativeSolo) {
            divisor *= -1; // Recursive function uses both being positive, corrects remainder at the end of the recursive call
            remainder = moduloRecursionDivisorXorDividendNeg(dividend, divisor, divisor, true);
        } else if (bothNegative) { // If both are negative or positive the same math can be used, just flip the sign later
            remainder = moduloRecursionBothPosOrNeg(dividend, divisor);
            remainder *= -1; // The function works with both positive numbers and returns a positive result, so it needs to be flipped
        } else { // Neither negative
            remainder = moduloRecursionBothPosOrNeg(dividend, divisor);
        }

        // Return the remainder
        return remainder;
    }

    // Recursive function used when both dividend and divisor are the same sign
    // I disliked calling the first argument "dividend" as every call except the original doesn't make sense when named that way
    public static int moduloRecursionBothPosOrNeg(int changingValue, int divisor) {
        int returnVal = changingValue;
        if (returnVal >= divisor) { // At least 1 more divisor can go into the current return value
            returnVal -= divisor; // Decrement
            returnVal = moduloRecursionBothPosOrNeg(returnVal, divisor); // Recursion
        }
        return returnVal; // No more divisors could go into the returnVal, start the return chain
    }

    // Recursive function used when only the divisor is negative
    // I disliked calling the second argument "divisor" as every call except the original doesn't make sense when named that way
    /* Explanation of how this works
     * The function uses some "reverse math/logic" to find the remainder
     * It effectively turns "x mod y" into "y mod x", and if the divisor is negative flips the sign of the final answer
     * to align with Google's answer
     * 
     * For the function to be called originally, either the divisor XOR the dividend is negative, which sets a corresponding flag
     * Before the function is called the sign of the negative value is flipped based off the flag
     * So if the dividend is originally negative, "-x mod y" turns into "y mod x", x = |original x|
     * Otherwise the divisor is originally negative, "x mod -y" also turns into "y mod x", y = |original y|
     * 
     * The general check is
     * if (x > yn) { (n is not defined in the code, just works for explanation)
     *     increment n
     *     continue checking if (x > yn) until no longer true
     * } else {
     *     yn value is now >= x
     *     subtract x from yn, this gets the remainder for y mod x
     *     Do a check on the flag, if divisor was originally negative flip the sign on the remainder, dividend does not flip
     * }
     * return the real answer
     * 
     * Example: 7 mod -5 (Divisor is negative)
     * 7 > 5 = true (n = 1), increment n
     * 7 > 10 = false (n = 2), enter logic
     * 10 - 7 = 3, Flip the sign: 3 * -1 = -3, return -3
     * 
     * Example: -7 mod 5 (Dividend is negative)
     * 7 > 5 = true (n = 1), increment n
     * 7 > 10 = false (n = 2), enter logic
     * 10 - 7 = 3, return 3
     */
    public static int moduloRecursionDivisorXorDividendNeg(int dividend, int changingValue, int baseDivisor, boolean divisorNegative) {
        int returnVal = changingValue;
        if (dividend > returnVal) { // "Reverse math" check
            returnVal += baseDivisor; // incrementing n in above explanation
            returnVal = moduloRecursionDivisorXorDividendNeg(dividend, returnVal, baseDivisor, divisorNegative); // Recursive call
        } else  { // When yn becomes > x, do math to get correct remainder
            returnVal -= dividend; // Always subtract the value
            if (divisorNegative) { // If the divisor is negative we need to flip the sign, this just aligns with Google's answer
                returnVal *= -1;
            } // If the dividend is negative don't flip, once again just to align with Google's answer
        }
        return returnVal; // Return chain
    }

    // Modulo using multiplication instead of recursion
    public int moduloMultiply(int dividend, int divisor) {
        return 1;
    }
}
