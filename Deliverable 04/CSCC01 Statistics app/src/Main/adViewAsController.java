package Main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * ADMIN VIEWS A SPECIFIC ASSIGNMENT WITH ALL THE POSSIBLE QUESTIONS,
 * MAY ADD MORE QUESTIONS OR EDIT CURRENT QUESTIONS
 */
public class adViewAsController {

    @FXML
    private Button editQuestionButton;
    @FXML
    private Button addQuestionButton;
    @FXML
    private Button backToAsPageButton;
    @FXML
    private Label aAssgnTitle;
    @FXML
    private ListView<Question> assgnQList;

    String assignmentName = "";
    Assignment assignment;
    ArrayList<Question> questionList = new ArrayList<Question>();

    /**
     * Gets assignment name from prev scene, loads the questions
     * @param name
     */
    public void assgnName(String name) {
        assignmentName = name;
        for(Assignment a : Data.assignmentList) {
            if(a.getAssignmentName().equals(name)) {
                assignment = a;
            }
        }
        aAssgnTitle.setText(name);
        loadAssignmentQs();
    }

    public void loadAssignmentQs() {
        // Gets all the questions
        for(Assignment curr : Data.assignmentList) {
            System.out.println("looping a list: " + curr.getAssignmentName() + " vs " + aAssgnTitle.getText());
            if(curr.getAssignmentName().equals(aAssgnTitle.getText())) {
                questionList = curr.getQuestionList();
            }
        }
        // Puts full questions into the list
        assgnQList.getItems().addAll(questionList);
    }

    public void editQuestion() {

    }

    public void addQuestion() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addQuestion.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) addQuestionButton.getScene().getWindow();
        stage.setScene(new Scene(root, 650, 400));
        questionController controller = loader.<questionController>getController();
        controller.selectedAssgn(assignment);
        stage.show();
    }

    public void aBackToAsPage() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("assignmentPage.fxml"));
        Stage stage = (Stage) backToAsPageButton.getScene().getWindow();
        stage.setScene(new Scene(root, 650, 400));
        stage.show();
    }
}
