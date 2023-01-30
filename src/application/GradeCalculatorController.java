package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class GradeCalculatorController {

    @FXML
    private ChoiceBox<Integer> optionalCCChoicebox;

    @FXML
    private TextField projectGradeTextfield;

    @FXML
    private ChoiceBox<Integer> requiredCCChoicebox;

    @FXML
    private Slider quizGradeSlider;
    
    @FXML
    private Label courseGradeLabel;
    
    @FXML
    private Label projectErrorLabel;
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
    double getProjectGrade(String valueEntered) {
    	//Check that the string entered by the user is a valid decimal number
    	boolean validProjectGrade = true;
    	for(char c : valueEntered.toCharArray()) {
    		//check if the character is a digit
    		if (!Character.isDigit(c) && c != '.'){
    			validProjectGrade = false;
    			projectErrorLabel.setText("Do not use " + c + " in a project grade. Make sure to enter a number.");
    		}
    	}
    	
    	//convert the string entered by the user to a double if the input is a valid number
    	//Otherwise the project grade will default to zero
    	double projectGrade = 0;
    	if (validProjectGrade) {
    		projectGrade = Double.parseDouble(valueEntered);
    	}
    	
    	//check if the number entered by the user is a valid percentage grade
    	//if valid, include it in the grade computation
    	if(projectGrade < 0 || projectGrade > 100) {
    		projectErrorLabel.setText("Project grade should be between 0% and 100%.");
    		projectGrade = 0;
    	}
    	return projectGrade;
    }

    @FXML
    void calculateGrade(ActionEvent event) {
    	//Clear all error messages
    	projectErrorLabel.setText("");
    	
    	double courseGrade = 0.0;
    	
    	//assuming that project is worth 50% towards course grade
    	String projectValueEntered = projectGradeTextfield.getText();
    	
    	double projectGrade = getProjectGrade(projectValueEntered);
    	
    	courseGrade = courseGrade + projectGrade * 50/100;
    	
    	System.out.println("Project grade entered: " + projectGrade + " Course grade so far: " + courseGrade);
    	
    	double quizGrade = quizGradeSlider.getValue();
    	courseGrade = courseGrade + (quizGrade * 100/10) * 0.25;
    	System.out.println("Quiz Grade " + quizGrade + " Course grade so far: " + courseGrade);
    	
    	int requiredCodingChallengesPassed = requiredCCChoicebox.getValue();
    	int optionalCodingChallengesPassed = optionalCCChoicebox.getValue();
    	courseGrade = courseGrade + ((requiredCodingChallengesPassed + optionalCodingChallengesPassed) * 100/20) * 0.25;
    	System.out.println("Required coding challenges passed: " + requiredCodingChallengesPassed + " Course Grade so far: " + courseGrade);
    	
    	courseGradeLabel.setText(String.format("Your course grade is %.2f", courseGrade));
    }

}