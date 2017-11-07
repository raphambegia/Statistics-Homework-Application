package Main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class viewAssignmentController {
    @FXML
    private Label assgnTitle;
    @FXML
    private Button assgnBackToList;
    @FXML
    private VBox assgnVbox;
    @FXML
    private ScrollPane sp;

    private String assignmentName = "";


    /**
     * Gets assignment name from prev scene, loads the questions
     * @param name
     */
    public void assgnName(String name) {
        assignmentName = name;
        assgnTitle.setText(name);
        loadAssignment();
    }

    public void loadAssignment() {
        ArrayList<Question> questionList = new ArrayList<Question>();
        ArrayList<ToggleGroup> toggleList = new ArrayList<ToggleGroup>();

        for(Assignment curr : Data.assignmentList) {
            System.out.println("looping a list: " + curr.getAssignmentName() + " vs " + assgnTitle.getText());
            if(curr.getAssignmentName().equals(assgnTitle.getText())) {
                questionList = curr.getQuestionList();
            }
        }
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
        }
    }

    public void aBackToList() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("studentAssignment.fxml"));
        Stage stage = (Stage) assgnBackToList.getScene().getWindow();
        stage.setScene(new Scene(root, 650, 400));
        stage.show();
    }
}
