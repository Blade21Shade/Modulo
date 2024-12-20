/* A project making a custom modulo operator
 * Originally this project was just going to be 2 methods, a recursive method and a multiplication method, as I underestimated
 * exactly what modulo needed, specifically when dividend xor divisor is negative.
 * 
 * This ended up going further from my first 2 ideas of recursion (the original reason for this) and multiplication to also include
 * a version that was most efficient which used integer division
 * 
 * I did find the integer division idea when talking to GPT about modulus vs remainder in Java, and admittedly I feel kind of 
 * dumb for not thinking about using it from the get go since it truly is very east to use, but I coded it out anyway just to
 * make sure I understood exactly what was going on
 */

 // The Modulo function is here
 // Since this was just a test file I also have the main file in here, but for real usage someone would just call
 // moduloRecursionOuter() as it is the only public function available
public class Modulo {
    public static void main(String[] args) throws Exception {
        int dividendValue = 500;
        int divisorValue = 3;
        int remainderValue = moduloRecursionOuter(dividendValue, divisorValue);
        System.out.printf("The remainder for %5d mod %5d is: %3d\n", dividendValue, divisorValue, remainderValue);
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

        if (dividend == divisor) { // This is also a 0, just the 0 isn't x or y in: x mod y
            return 0;
        }

        // Value returned from this function 
        int remainder = 0;

        // Set up how returning is handled
        // Online I found that different languages handle it different ways, so I'm going to implement how Google does it
        boolean dividendNegativeSolo = false; // Only dividend is negative, function will return a positive number
        boolean divisorNegativeSolo = false; // Only divisor is negative, function will return a negative number 
        boolean bothNegative = false; // Both are negative, will return a negative number
        
        // Set booleans as needed
        if (dividend < 0 && divisor > 0) {
            dividendNegativeSolo = true;
        } else if (dividend > 0 && divisor < 0) {
            divisorNegativeSolo = true;
        } else if (dividend < 0 && divisor < 0) {
            bothNegative = true;
            dividend *= -1; // Inverse the signs, this math is the same for if both were positive
            divisor *= -1; // The answer is corrected before returning with *= -1 
        }

        // If the divisor is greater than the dividend and the dividend and the divisor are the same sign, return the dividend
        if (divisor > dividend && !dividendNegativeSolo && !divisorNegativeSolo) {
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
            remainder *= -1; // The function works with both positive numbers and returns a positive result, so it needs to be inverted
        } else { // Neither negative
            //remainder = moduloRecursionBothPosOrNeg(dividend, divisor);
            //remainder = moduloMultiplyOriginalBothPosOrNeg(dividend, divisor);
            remainder = moduloMultiplyAfterLookingOnlineBothPosOrNeg(dividend, divisor);
        }

        // Return the remainder
        return remainder;
    }

    // Recursive function used when both dividend and divisor are the same sign
    // I disliked calling the first argument "dividend" as every call except the original doesn't make sense when named that way
    private static int moduloRecursionBothPosOrNeg(int changingValue, int divisor) {
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
    private static int moduloRecursionDivisorXorDividendNeg(int dividend, int changingValue, int baseDivisor, boolean divisorNegative) {
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

    // Modulo using a while loop and subtraction instead of recursion
    private static int moduloWhileSubtractionBothPosOrNeg(int dividend, int divisor) {
        while (dividend - divisor > divisor) { // Reduce the dividend value until it is one away from being greater than the divisor
            dividend -= divisor;
        }
        return dividend - divisor; // Returns the remainder
    }

    // Modulo using while and multiplication instead of recursion
    private static int moduloMultiplyOriginalBothPosOrNeg(int dividend, int divisor) {
        int workingValue = 0; // This value is added to to get closer to the value of dividend until workingValue + divisor > dividend

        // As long as divisor's value can still be added to workingValue while being smaller than dividend keep looping
        while (workingValue + divisor <= dividend) {
            int inWhileValue = 0; // Value added to workingValue every loop, is added to and multiplied

            // Find value of "10^i * divisor" that can be added to workingValue without exceeding dividend's value
            int i = 0;
            while (workingValue + divisor * Math.pow(10, i+1) < dividend) {
                i++;
            }
            inWhileValue += divisor * Math.pow(10, i);

            // See inWhileValue's value can be multiplied by some integer such that if it is added to workingValue it is still
            // less than dividend's value
            i = 1;
            while (workingValue + inWhileValue * (i + 1) < dividend) {
                i++;
            }
            inWhileValue *= i;

            // Finally, add this found number to workingValue
            workingValue += inWhileValue;
        }

        return dividend - workingValue; // Working value is as close to dividend as possible, so subtract and return the remainder
    }

    // After doing some reading online, and asking chat GPT about modulus vs remainder in Java, I found a better way to write a modulus function
    // This is an "ideal" way of doing it since it uses integer division, since I was planning on doing this project without using
    // the % operator I also wanted to avoid using /, but I figured doing it this way would be find just for proof's sake
    private static int moduloMultiplyAfterLookingOnlineBothPosOrNeg(int dividend, int divisor) {
        return dividend - divisor * (dividend / divisor); 
    }
}
