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
    void calculateGrade(ActionEvent event) {
    	double courseGrade = 0.0;
    	String projectGrade = projectGradeTextfield.getText();
    	courseGrade = Double.parseDouble(projectGrade) * 50/100;
    	System.out.println("Project grade " + projectGrade + " Course grade so far: " + courseGrade);
    	
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