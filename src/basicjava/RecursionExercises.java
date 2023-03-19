package basicjava;

import java.util.ArrayList;

public class RecursionExercises {
	
	
	public int countDigits(int n) {
		if (n < 10) { 
			return 1; // base case: single digit numbers 0-9 are only one digit
		}else {
			int remainingDigits = n / 10; // shows remaining digits left
			return 1 + countDigits(remainingDigits);
		}
	}

	public int addDigits(int n) {
		if (n <= 0) {
			return n; // base case: if n = zero or is negative return n 
		}else {
			int lastDigit = n % 10; // removes last digit
			int remainingDigits = n / 10; // shows remaining digits left
			return lastDigit + addDigits(remainingDigits);
		}
	}
	
	public int sum(int sumFrom, int sumTo) {
	    if (sumFrom < 0 || sumTo < 0 || sumTo < sumFrom) {
	        return 0; // base case: sum from less than 0, someTo less than 0, someFrom greater than someTo, return 0
	    } else if (sumFrom == sumTo) {
	        return sumFrom;
	    } else {
	        return sumFrom + sum(sumFrom + 1, sumTo);
	    }
	}

	
	public int sumEvenNumbers(int n) {
	    if (n == 0) {
	        return 0; // base case: there are no even numbers in 0
	    } else if ((n < 0)&& (n % 2 != 0)) {
	        // If n is negative and odd, add 1 to n
	        return sumEvenNumbers(n + 1);
	    } else if ((n < 0)&& (n % 2 == 0)) {
	    	//if n is negative and even, add 2 to n 
	    	return sumEvenNumbers(n + 2);
	    } else if (n % 2 != 0) {
	        // If n is positive and odd, subtract 1 from n
	        return sumEvenNumbers(n - 1);
	    } else {
	        // If n is positive and even, add n and + 2 
	        return n + sumEvenNumbers(n - 2);
	    }
	}


	
	public int sumOfMultiple(ArrayList<Integer> list) {
		 if (list == null ||list.isEmpty()) {
		        return 0;  // base case: empty list or null list
		    }
		    int num = list.get(0);
		    ArrayList<Integer> restOfList = new ArrayList<>(list.subList(1, list.size()));
		    int sum = sumOfMultiple(restOfList);
		    if (num % 5 == 0) {
		    	// add num to sum if it is a multiple of 5
		    	sum += num;
		    }
		    return sum;
		}
	
	public static int countVowels(String n) {
	    if (n == null || (n.isEmpty())) {
	        return 0;  // base case: empty or null string
	    } else {
	        char c = n.charAt(0);
	        int count = countVowels(n.substring(1));
	        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ||
	                c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
	        	count += 1;
	        }
	        	return count;
	    }
	}

	
	public String removeVowels(String n) {
		if (n == null) {
	        return null;  // base case: null string
	    } else if (n.isEmpty()) {
	        return "";  // base case: empty string
	    } else {
        char c = n.charAt(0);
        String restOfString = n.substring(1);
        String result = removeVowels(restOfString);
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ||
            c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
            return result;
        } else {
            return c + result;
        }
    }
}


}
