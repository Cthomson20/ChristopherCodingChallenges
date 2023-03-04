package basicjava;

public class CCStringsIfAndWhile {
	/**
	 * returns true if it is a digit and returns false if it is not 
	 * @param aChar
	 * @return true if digit, false if not a digit
	 */
	public static boolean isDigit(char aChar) {
		return Character.isDigit(aChar);
	}
	
	/**
	 * Counts the total number of times the specified characters appear in the given string.
	 *
	 * @param str the input string to count characters in
	 * @param chars the sequence of characters to count in the input string
	 * @return the total number of times the specified characters appear in the input string
	 * 
	 */
	public static int count(String str, String chars) {
	    int count = 0;

	    // Convert both input strings to lowercase for case-insensitive comparison
	    str = str.toLowerCase();
	    chars = chars.toLowerCase();

	    for (int i = 0; i < str.length(); i++) {
	        if (chars.indexOf(str.charAt(i)) >= 0) {
	            count++;
	        }
	    }

	    return count;
	}


	/**
	 * finds the smallest number in the input
	 * @param num
	 * @return the smallest digit
	 */
	public static int smallestDigit(int num) {
		if (num == 0) {
			return 0;
		}
	    num = (num < 0) ? -num : num; 
	    int smallest = 9; 
	    while (num > 0) {
	        int digit = num % 10; 
	        if (digit < smallest) {
	            smallest = digit;
	        }
	        num = (num - digit) / 10; 
	    }
	    return smallest;
	}
}