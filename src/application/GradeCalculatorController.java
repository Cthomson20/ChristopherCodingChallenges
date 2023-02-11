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
    private ChoiceBox<Integer> optionalQuizzesCompletedChoiceBox;
    

    @FXML
    private TextField projectGradeTextfield;

    @FXML
    private ChoiceBox<Integer> requiredCCChoicebox;
    
    @FXML
    private Label courseGradeLabel;
    
    @FXML
    private Label mainRequiredQuizGrade;
    
    @FXML
    private Label mainOptionalQuizGrade;
    
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
    
    /**
    * The method calculateRequiredAverageQuizGrade calculates the average of the required quiz grades.
    * @param mainScene The main scene of the application.
    * @param requiredQuizGradeTextfields A list of TextField objects representing the required quiz grades.
    */
    double requiredAverageQuizGrade = 0.0;
    
    
    void calculateRequiredAverageQuizGrade(Scene mainScene,ArrayList<TextField> requiredQuizGradeTextfields) {
    	applicationStage.setScene(mainScene);
    	// make sure to reset to zero
    	requiredAverageQuizGrade = 0.0;
    	for (TextField requiredQuizGradeTextfield : requiredQuizGradeTextfields) {
    		requiredAverageQuizGrade += Double.parseDouble(requiredQuizGradeTextfield.getText());
    	}
    	requiredAverageQuizGrade = requiredAverageQuizGrade / 15;
    	mainRequiredQuizGrade.setText(String.format("%.2f/10", requiredAverageQuizGrade));
    }
    /**
    * The method getRequiredQuizGrades handles the creation of a new scene for the user to enter the required quiz grades.
    * It does so by first retrieving the main scene using the applicationStage object.
    * Then it gets the number of required quizzes from the requiredQuizzesCompletedChoiceBox object and creates a new VBox container called requiredQuizGradeContainer.
    * Next, it creates a list of TextField objects to store the required quiz grades and adds them to the requiredQuizGradeContainer.
    * The number of TextField objects created is determined by the value of numberOfRequiredQuizzes.
    * A button labeled "Done" is also added to the requiredQuizGradeContainer, and its action is set to call the calculateRequiredAverageQuizGrade method,
    * passing the main scene and the list of required quiz grades as arguments.
    * Finally, the method creates a new scene using the requiredQuizGradeContainer and sets it as the current scene using the applicationStage object.
    * @param enterRequiredQuizGradeevent The event that triggers the method to execute.
    */
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
        	TextField requiredQuizGradeTextfield = new TextField("0.0");
    		Label outOfTenLabel = new Label("/10");
        	requiredQuizGradeTextfields.add(requiredQuizGradeTextfield);
        	
        	rowContainer1.getChildren().addAll(requiredQuizGradeLabel,requiredQuizGradeTextfield, outOfTenLabel);
        	rowsCreated1 ++;
        	
        	requiredQuizGradeContainer.getChildren().add(rowContainer1);
    	}
    	
    	Button doneButtonOne = new Button("Done");
    	doneButtonOne.setOnAction(doneOneEvent -> calculateRequiredAverageQuizGrade(mainScene,requiredQuizGradeTextfields));
    	
    	requiredQuizGradeContainer.getChildren().add(doneButtonOne);
    	
    	Scene requiredQuizGradeScene = new Scene(requiredQuizGradeContainer);
    	applicationStage.setScene(requiredQuizGradeScene);
    }
    /**
     * Calculates the average of the grades from a list of optional quiz grades.
     *
     * @param mainScene The main scene for the application.
     * @param optionalQuizGradeTextfields The list of textfields representing the optional quiz grades.
     */
    double optionalAverageQuizGrade = 0.0;
    
    void calculateAverageOptionalQuizGrade(Scene mainScene, ArrayList<TextField> optionalQuizGradeTextfields) {
    	applicationStage.setScene(mainScene);
    	optionalAverageQuizGrade = 0.0;
    	for (TextField optionalQuizGradeTextfield : optionalQuizGradeTextfields) {
    		optionalAverageQuizGrade += Double.parseDouble(optionalQuizGradeTextfield.getText());
    	}
    	optionalAverageQuizGrade = optionalAverageQuizGrade / 5;
    	mainOptionalQuizGrade.setText(optionalAverageQuizGrade + "/10");
    }
    
    /**
    * getOptionalQuizGrades method is used to retrieve the optional quiz grades entered by the user.
    * @param enterOptionalQuizGradeevent - an ActionEvent triggered by clicking the button to enter the optional quiz grades.
    * The method creates a VBox container for the optional quiz grades and a HBox container for each individual grade.
    * It also creates a label and a textfield for each grade.
    * The number of grades to be entered is determined by the value of the "optionalQuizzesCompletedChoiceBox" choice box.
    * The textfields are stored in an ArrayList so that the values can be passed to the "calculateAverageOptionalQuizGrade" method.
    * The method then creates a button "Done" which triggers the "calculateAverageOptionalQuizGrade" method.
    * Finally, the method sets the optionalQuizGradeScene as the current scene of the application stage.
    */
    @FXML
    void getOptionalQuizGrades (ActionEvent enterOptionalQuizGradeevent) {
    	Scene mainScene = applicationStage.getScene();
    	
    	int numberOfOptionalQuizzes = optionalQuizzesCompletedChoiceBox.getValue();
    	int rowsCreated2 = 0;
    	VBox optionalQuizGradeContainer = new VBox();
    	
    	ArrayList<TextField> optionalQuizGradeTextfields = new ArrayList<TextField>();
    	while (rowsCreated2 < numberOfOptionalQuizzes) {
    		HBox rowContainer2 = new HBox();
    		Label optionalQuizGradeLabel = new Label("Optional Quiz Grade");
        	TextField optionalQuizGradeTextfield = new TextField("0.0");
        	Label outOfTenLabel = new Label("/10");
        	optionalQuizGradeTextfields.add(optionalQuizGradeTextfield);
        	
        	rowContainer2.getChildren().addAll(optionalQuizGradeLabel,optionalQuizGradeTextfield,outOfTenLabel);
        	rowsCreated2 ++;
        	
        	optionalQuizGradeContainer.getChildren().add(rowContainer2);
    	}
    	
    	Button doneButtonTwo = new Button("Done");
    	doneButtonTwo.setOnAction(doneTwoEvent -> calculateAverageOptionalQuizGrade(mainScene,optionalQuizGradeTextfields));
    	optionalQuizGradeContainer.getChildren().add(doneButtonTwo);
    
    	Scene optionalQuizGradeScene = new Scene(optionalQuizGradeContainer);
    	applicationStage.setScene(optionalQuizGradeScene);
    	
    }
    
    /**
    * The method calculateGrade calculates the overall course grade based on the grade for the project, quizzes, and coding challenges.
    * The method starts by clearing any error messages in the projectErrorLabel.
    * Then it retrieves the project grade entered by the user and calculates its contribution to the overall course grade.
    * The project grade is assumed to be worth 50% of the course grade.
    * Next, the method calculates the contribution of the quizzes to the overall course grade.
    * The quizzes are assumed to be worth 25% of the course grade and are marked out of 10.
    * Finally, the method calculates the contribution of the coding challenges to the overall course grade.
    * The coding challenges are assumed to be worth 25% of the course grade, with both required and optional coding challenges contributing to the overall grade.
    * The final course grade is displayed to the user through the courseGradeLabel object.
    * @param event The event that triggers the method to execute.
    */
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
    	//assuming the total average quiz grade is the optional average plus the required average divided by 2
    	double averageQuizGrade = (((optionalAverageQuizGrade * 0.0625 ) + (requiredAverageQuizGrade * 0.1875)) * 100/20);
    	
    	courseGrade += averageQuizGrade;
    	System.out.println("Quiz Grade entered: " + averageQuizGrade + " Course grade so far: " + courseGrade);
    	
    	
    	int requiredCodingChallengesPassed = requiredCCChoicebox.getValue();
    	int optionalCodingChallengesPassed = optionalCCChoicebox.getValue();
    	courseGrade = courseGrade + ((requiredCodingChallengesPassed + optionalCodingChallengesPassed) * 100/20) * 0.25;
    	System.out.println("Required coding challenges passed: " + requiredCodingChallengesPassed + " Course Grade so far: " + courseGrade);
    	
    	courseGradeLabel.setText(String.format("Your course grade is %.2f", courseGrade));
    }}

