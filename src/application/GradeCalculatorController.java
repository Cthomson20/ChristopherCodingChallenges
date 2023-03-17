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
    * The method calculateRequiredAverageQuizGrade calculates the average of the required quiz grades.
    * @param mainScene The main scene of the application.
    * @param requiredQuizGradeTextfields A list of TextField objects representing the required quiz grades.
    */
    double requiredAverageQuizGrade = 0.0;
    
    Label requiredQuizErrorLabel = new Label();
    
    void calculateRequiredAverageQuizGrade(Scene mainScene,ArrayList<TextField> requiredQuizGradeTextfields) throws InvalidGradeException {
    	requiredQuizErrorLabel.setText("");
    	
    	//assume that each quiz has equal weight
    	double weightPerRequiredQuiz = 1.0/15;
    	
    	// make sure to reset to zero
    	requiredAverageQuizGrade = 0.0;
    	boolean errorInRequiredQuizGrade = false;
    	
    	for (TextField requiredQuizGradeTextfield : requiredQuizGradeTextfields) {
    		try {
    		Grade requiredQuizGrade = new Grade(requiredQuizGradeTextfield.getText(),10,weightPerRequiredQuiz);
    		requiredAverageQuizGrade += requiredQuizGrade.getWeightedPercentageGrade();
    		} catch (InvalidGradeException e) {
    			errorInRequiredQuizGrade = true;
    			requiredQuizErrorLabel.setText(e.getMessage());
    			Grade requiredQuizGrade = new Grade("0", 10, weightPerRequiredQuiz);
    			requiredAverageQuizGrade += requiredQuizGrade.getWeightedPercentageGrade();
    		}
    	}
    	if (!errorInRequiredQuizGrade) {
    		mainRequiredQuizGrade.setText(String.format("%.2f/10", requiredAverageQuizGrade/10));
    		applicationStage.setScene(mainScene);
    	}
    	
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
    	requiredQuizGradeContainer.getChildren().add(requiredQuizErrorLabel);
    	
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
    	doneButtonOne.setOnAction(doneOneEvent -> {
			try {
				calculateRequiredAverageQuizGrade(mainScene,requiredQuizGradeTextfields);
			} catch (InvalidGradeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
    	
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
    
    Label optionalQuizErrorLabel = new Label();
    
    void calculateAverageOptionalQuizGrade(Scene mainScene, ArrayList<TextField> optionalQuizGradeTextfields) throws InvalidGradeException {
    	requiredQuizErrorLabel.setText("");
    	
    	//assume that each quiz has equal weight
    	double weightPerOptionalQuiz = 1.0/5;
    	
    	// make sure to reset to zero
    	optionalAverageQuizGrade = 0.0;
    	boolean errorInOptionalQuizGrade = false;
    	
    	for (TextField optionalQuizGradeTextfield : optionalQuizGradeTextfields) {
    		try {
    		Grade optionalQuizGrade = new Grade(optionalQuizGradeTextfield.getText(),10,weightPerOptionalQuiz);
    		optionalAverageQuizGrade += optionalQuizGrade.getWeightedPercentageGrade();
    		} catch (InvalidGradeException e){
    			errorInOptionalQuizGrade = true;
    			requiredQuizErrorLabel.setText(e.getMessage());
    			Grade optionalQuizGrade = new Grade("0",10,weightPerOptionalQuiz);
    			optionalAverageQuizGrade += optionalQuizGrade.getWeightedPercentageGrade();
    		}
    	}
    	if (!errorInOptionalQuizGrade) {
    		mainOptionalQuizGrade.setText(String.format("%.2f/10", optionalAverageQuizGrade/10));
    		applicationStage.setScene(mainScene);
    	}
    	
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
    	optionalQuizGradeContainer.getChildren().add(optionalQuizErrorLabel);
    	
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
    	doneButtonTwo.setOnAction(doneTwoEvent -> {
			try {
				calculateAverageOptionalQuizGrade(mainScene,optionalQuizGradeTextfields);
			} catch (InvalidGradeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
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
    	
    	Grade projectGrade = new Grade(projectGradeTextfield.getText(),100,.5);
    	projectErrorLabel.setText(projectGrade.setValue(projectGradeTextfield.getText()));  
    	Grade requiredQuizGrade = new Grade(requiredAverageQuizGrade,10,0.1875);   		
    	Grade optionalQuizGrade = new Grade(optionalAverageQuizGrade,10,0.0625);   	   	
    	Grade codingChallengeGrade = new Grade((requiredCCChoicebox.getValue() + optionalCCChoicebox.getValue()),20,0.25);
    	
    	
    	double courseGrade = projectGrade.getWeightedPercentageGrade() + requiredQuizGrade.getWeightedPercentageGrade() + optionalQuizGrade.getWeightedPercentageGrade() + codingChallengeGrade.getWeightedPercentageGrade();
    	
    	//Display result of calculation to the user in the window
    	//Display result to two digits after decimal point
    	courseGradeLabel.setText(String.format("Your overall course grade is: %.2f", courseGrade));
    }
}

