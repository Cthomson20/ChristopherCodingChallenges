package application;

public class Grade {
	double value;
	int maxValue = 100;
	double weight;
	
	Grade (double gradeValue, int maxPossibleValue, double weightTowardsCourseGrade){
		value = gradeValue;
		maxValue = maxPossibleValue;
		weight = weightTowardsCourseGrade;
	}
	
	double getWeightedPercentageGrade() { 
		return value * 100 * weight / maxValue;
}
	
	/**
     * Convert the value entered to a double value. This method will verify that the value
     * entered is indeed a number and is valid percentage grade (between 0 and 100). If the 
     * value entered is not a valid percentage grade, this method will return 0.0 as the project grade
     * instead
     * 
     * 
     * @param valueEntered a string that holds a value entered by the user intended to be a project grade
     * @return the project value entered by the user if it is a valid percentage grade and 0 otherwise
     */
    String setValue(String valueAsString) {
    	String errorMessage = "";
    	//Check that the string entered by the user is a valid decimal number
    	boolean validProjectGrade = true;
    	int decimalCount = 0;
    	for(char c : valueAsString.toCharArray()) {
    		//check if the character is a digit
    		if (!Character.isDigit(c)) {
    			if (c == '.'){
    				decimalCount++;
    				if (decimalCount > 1) {
    					validProjectGrade = false;	
    			validProjectGrade = false;
    			errorMessage = String.format("Do not use %c in a grade value. Make sure to enter a number.", c);
    			}
    		}
    	}
    }
    	
    	//convert the string entered by the user to a double if the input is a valid number
    	//Otherwise the project grade will default to zero
    	
    	if (validProjectGrade) {
    		value = Double.parseDouble(valueAsString);
    	}
    	
    	//check if the number entered by the user is a valid percentage grade
    	//if valid, include it in the grade computation
    	if(value < 0 || value > maxValue) {
    		errorMessage = String.format("Grade should be between 0 and %d.", maxValue);
    		value = 0;
    	}
    	return errorMessage;
    	}
	}


