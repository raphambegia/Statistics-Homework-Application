package Main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 *  STUDENT VIEWS SPECIFIC ASSIGNMENT FOR REVIEWING OR ATTEMPTING
 */
public class viewAssignmentController {
    @FXML
    private Label assgnTitle;
    @FXML
    private Button assgnBackToList;
    @FXML
    private VBox assgnVbox;
    @FXML
    private Button submitAssignButton;

    private Assignment assignment;
    private ArrayList<Question> questionList = new ArrayList<Question>();
    private ArrayList<ToggleGroup> toggleList = new ArrayList<ToggleGroup>();


    /**
     * Gets assignment from prev scene, loads the questions
     * @param assign
     */
    public void initAssignment(Assignment assign) {
        assgnTitle.setText(assign.getAssignmentName());
        assignment = assign;

        if(!assignment.isAvailable()) {
            submitAssignButton.setDisable(true);
        }

        loadAssignment();
    }

    public void loadAssignment() {
        // Gets all the questions
        for(Assignment curr : Data.assignmentList) {
            System.out.println("looping a list: " + curr.getAssignmentName() + " vs " + assgnTitle.getText());
            if(curr.getAssignmentName().equals(assgnTitle.getText())) {
                questionList = curr.getQuestionList();
            }
        }

        // Displays all the questions
        for(int i = 0; i < questionList.size(); i++) {
            Label questionLabel = new Label();
            questionLabel.setText(i+1 + ". " + questionList.get(i).getTheQuestion());
            ToggleGroup mcAnsToggle = new ToggleGroup();
            toggleList.add(mcAnsToggle);
            assgnVbox.getChildren().add(questionLabel);
            for(int j = 0; j < questionList.get(i).getMcAnswers().size(); j++) {
                RadioButton mcAnsOption = new RadioButton(questionList.get(i).getMcAnswers().get(j));
                mcAnsOption.setToggleGroup(mcAnsToggle);
                assgnVbox.getChildren().add(mcAnsOption);
            }
            Separator sep = new Separator();
            sep.setVisible(false);
            assgnVbox.getChildren().add(sep);
        }
    }

    public void aBackToList() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("studentAssignment.fxml"));
        Stage stage = (Stage) assgnBackToList.getScene().getWindow();
        stage.setScene(new Scene(root, 650, 400));
        stage.show();
    }

    public void submitAssignment() {

    }
}
