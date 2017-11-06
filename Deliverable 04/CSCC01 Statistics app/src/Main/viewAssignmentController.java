package Main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private Button loadAssignment;
    @FXML
    private VBox assgnVbox;

    private String assignmentName = "";

    public void assgnName(String name) {
        assignmentName = name;
        assgnTitle.setText(name);
    }

    public void loadAssignment() {
        ArrayList<Question> questionList = new ArrayList<Question>();

        for(Assignment curr : Data.assignmentList) {
            System.out.println("looping a list: " + curr.getAssignmentName() + " vs " + assgnTitle.getText());
            if(curr.getAssignmentName().equals(assgnTitle.getText())) {
                System.out.println("match");
                questionList = curr.getQuestionList();
                questionList.addAll(curr.getQuestionList());
                System.out.println("SIZE : " + curr.getQuestionList().size());
                //System.out.println(questionList.get(0).getTheQuestion());
            }
        }
        for(int i = 0; i < questionList.size(); i++) {
            Label questionLabel = new Label();
            questionLabel.setText(questionList.get(i).getTheQuestion());
            assgnVbox.getChildren().add(questionLabel);
        }
    }

    public void aBackToList() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("studentAssignment.fxml"));
        Stage stage = (Stage) assgnBackToList.getScene().getWindow();
        stage.setScene(new Scene(root, 650, 400));
        stage.show();
    }
}
