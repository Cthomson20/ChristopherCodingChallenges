package application;

public class Grade {
	double value;
	int maxValue = 100;
	double weight;
	
	
	Grade(String value,int maxValue,double weight)throws InvalidGradeException {
			try {
			this.value = Double.parseDouble(value);
			if (this.value < 0 || this.value > maxValue) {
			throw new InvalidGradeException("Invalid Grade Value: " + value);
		}
			} catch (NumberFormatException e) {
			throw new InvalidGradeException("Invalid Grade Value: " + value);
			}
			this.maxValue = maxValue;
			this.weight = weight;
		}
		
	double getWeightedPercentageGrade() { 
		return value * 100 * weight / maxValue;
	} 


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
	 




