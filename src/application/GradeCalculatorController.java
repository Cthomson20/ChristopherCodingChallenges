package application;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GradeCalculatorController {
	Stage applicationStage;

    @FXML
    private ChoiceBox<Integer> optionalCCChoicebox;
    
    @FXML
    private ChoiceBox<Integer> requiredQuizzesCompletedChoiceBox;

    @FXML
    private TextField projectGradeTextfield;

    @FXML
    private ChoiceBox<Integer> requiredCCChoicebox;
    
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
    	int decimalCount = 0;
    	for(char c : valueEntered.toCharArray()) {
    		//check if the character is a digit
    		if (!Character.isDigit(c)) {
    			if (c == '.'){
    				decimalCount++;
    				if (decimalCount > 1) {
    					validProjectGrade = false;	
    			validProjectGrade = false;
    			projectErrorLabel.setText("Do not use " + c + " in a project grade. Make sure to enter a number.");
    			}
    		}
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
    
    double requiredAverageQuizGrade = 0.0;
    
    void calculateRequiredAverageQuizGrade(Scene mainScene,ArrayList<TextField> requiredQuizGradeTextfields) {
    	applicationStage.setScene(mainScene);
    	// make sure to reset to zero
    	requiredAverageQuizGrade = 0.0;
    	for (TextField requiredQuizGradeTextfield : requiredQuizGradeTextfields) {
    		requiredAverageQuizGrade += Double.parseDouble(requiredQuizGradeTextfield.getText());
    	}
    	requiredAverageQuizGrade = requiredAverageQuizGrade / requiredQuizGradeTextfields.size();
    }
     
    @FXML
    void getRequiredQuizGrades(ActionEvent enterRequiredQuizGradeevent) {
    	Scene mainScene = applicationStage.getScene();
    	int numberOfRequiredQuizzes = requiredQuizzesCompletedChoiceBox.getValue();
    	int rowsCreated1 = 0;
    	VBox requiredQuizGradeContainer = new VBox();
    	
    	//Create a list that we will put all the text fields with  required quiz grades
    	ArrayList<TextField> requiredQuizGradeTextfields = new ArrayList<TextField>();
    	while (rowsCreated1 < numberOfRequiredQuizzes) {
    		
    		HBox rowContainer1 = new HBox();
    		Label requiredQuizGradeLabel = new Label("Required Quiz Grade");
        	TextField requiredQuizGradeTextfield = new TextField();
        	requiredQuizGradeTextfields.add(requiredQuizGradeTextfield);
        	
        	rowContainer1.getChildren().addAll(requiredQuizGradeLabel,requiredQuizGradeTextfield);
        	rowsCreated1 ++;
        	
        	requiredQuizGradeContainer.getChildren().add(rowContainer1);
    	}
    	
    	Button doneButtonOne = new Button("Done");
    	doneButtonOne.setOnAction(doneOneEvent -> calculateRequiredAverageQuizGrade(mainScene,requiredQuizGradeTextfields));
    	requiredQuizGradeContainer.getChildren().add(doneButtonOne);
    	
    	Scene requiredQuizGradeScene = new Scene(requiredQuizGradeContainer);
    	applicationStage.setScene(requiredQuizGradeScene);
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
    	
    	//assuming that quizzes are worth 25% towards the course grade
    	//assuming that quizzes are marked out of 10
    	//double quizGrade = requiredQuizzesCompletedChoiceBox.getValue();
    	courseGrade += (requiredAverageQuizGrade * 100/10) * 0.25;
    	System.out.println("Quiz Grade entered: " + requiredAverageQuizGrade + " Course grade so far: " + courseGrade);
    	
    	int requiredCodingChallengesPassed = requiredCCChoicebox.getValue();
    	int optionalCodingChallengesPassed = optionalCCChoicebox.getValue();
    	courseGrade = courseGrade + ((requiredCodingChallengesPassed + optionalCodingChallengesPassed) * 100/20) * 0.25;
    	System.out.println("Required coding challenges passed: " + requiredCodingChallengesPassed + " Course Grade so far: " + courseGrade);
    	
    	courseGradeLabel.setText(String.format("Your course grade is %.2f", courseGrade));
    }

}