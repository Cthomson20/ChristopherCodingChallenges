package basicjava;

import java.util.Arrays;

public class CCArrays {

	
	public static char[] replace(char[] charArray, char toReplace, char replaceWith) {
		if (charArray == null || charArray.length == 0) {
	        return charArray; 
	    }
		for (int i = 0; i < charArray.length; i++) {
	        if (Character.toLowerCase(charArray[i]) == Character.toLowerCase(toReplace)) {
	            charArray[i] = replaceWith;
	        }
	    }
		return charArray;
	}
	
	public static void sortAlphabetic(String[] strArray) {
	    Arrays.sort(strArray, String.CASE_INSENSITIVE_ORDER);
	}
}
