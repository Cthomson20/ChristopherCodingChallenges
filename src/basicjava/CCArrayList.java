package basicjava;

import java.util.ArrayList;

public class CCArrayList {
	public static int indexOfIgnoreCase(ArrayList<String> strs, String toFind) {
	    for (int i = 0; i < strs.size(); i++) {
	        if (strs.get(i).equalsIgnoreCase(toFind)) {
	            return i;
	        }
	        if (i == strs.size() - 1) {
	            return -1;
	        }
	    }
	    return -1;
	}
	
	public static void insert(ArrayList<Double> nums, double numToInsert, int insertAtIndex) {
	    if (insertAtIndex >= 0 && insertAtIndex <= nums.size()) {
	        nums.add(insertAtIndex, numToInsert);
	    }
	}


}
	
