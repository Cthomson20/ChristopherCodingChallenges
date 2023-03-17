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
			this.value = Double.parseDouble(value);
			} catch (NumberFormatException e) {
			throw new InvalidGradeException("Invalid Grade Value: " + value);
			}
			this.maxValue = maxValue;
			this.weight = weight;
		}
		
	double getWeightedPercentageGrade() { 
		return value * 100 * weight / maxValue;
	}
}


