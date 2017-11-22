package Main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ADMIN ADDS A QUESTION
 */
public class questionController {

    @FXML
    private TextField questionField;
    @FXML
    private TextField mcfield1;
    @FXML
    private TextField mcfield2;
    @FXML
    private TextField mcfield3;
    @FXML
    private TextField mcfield4;
    @FXML
    private TextField mcfield5;
    @FXML
    private Label warningLabel;
    @FXML
    private ToggleGroup answerToggle;
    @FXML
    private Button addQuestionButton;
    @FXML
    private Button backQuestionButton;

    private Assignment assignment;
    private String NOANSWERS = "NOANSWERS";
    private ArrayList<String> answerList = new ArrayList<String>();
    private ArrayList<String> idList = new ArrayList<String>();
    private List<TextField> mcList = new ArrayList<TextField>();

    public void selectedAssgn(Assignment assgn) {
        assignment = assgn;
    }

    public void addQuestionHandler() {
        // Check if question field is empty
        if (questionField.getText().replaceAll("\\s","").length() == 0) {
            warningLabel.setStyle("-fx-text-fill: red;");
            warningLabel.setText("Please include a question.");
            return;
        }

        // Check if at least one answer option has been added
        mcList.add(mcfield1);
        mcList.add(mcfield2);
        mcList.add(mcfield3);
        mcList.add(mcfield4);
        mcList.add(mcfield5);

        if (createAnsList().get(0).equals(NOANSWERS)) {
            return;
        } else {
            answerList = createAnsList();
        }

        // Check if a solution has been chosen, and it's position if so
        RadioButton solnSelected = (RadioButton) answerToggle.getSelectedToggle();
        if (solnSelected == null) {
            warningLabel.setStyle("-fx-text-fill: red;");
            warningLabel.setText("Please select the correct answer");
            return;
        }
        int solnInd = findSolutionInd(solnSelected.getId());

        // Check if the solution index and answer index match
        if (!solnMatch(solnInd)) {
            return;
        }

        // Add question to assignment & clear all fields
        Admin.addQuestion(questionField.getText(), answerList, solnInd, assignment);
        System.out.println("question added");
        warningLabel.setStyle("-fx-text-fill: green;");
        warningLabel.setText("Question added!");
        answerToggle.getSelectedToggle().setSelected(false);
        questionField.setText("");
        for(TextField tf : mcList) {
            tf.setText("");
        }
        mcList.clear();
    }

    /*
    Returns the array position of the solution
     */
    private int findSolutionInd(String solnSelected) {
        String soln = solnSelected.substring(solnSelected.length() - 1);
        return Integer.parseInt(soln) - 1;
    }

    /*
    Returns a list where the first element is "NOANSWERS" if no valid answers were entered.
     */
    private ArrayList<String> createAnsList() {
        ArrayList<String> ansList = new ArrayList<String>();
        int count = 0;
        for (TextField tf : mcList) {
            if (tf.getText().replaceAll("\\s","").length() != 0) {
                ansList.add(tf.getText());
                idList.add(tf.getId());
                count++;
            }
        }

        if (count == 0) {
            ansList.add(NOANSWERS);
            warningLabel.setStyle("-fx-text-fill: red;");
            warningLabel.setText("Please include at least one answer.");
        }

        return ansList;
    }

    /*
    Returns true if there is a solution at the index chosen.
     */
    private Boolean solnMatch(int solnInd) {
        for (int i = 0; i < idList.size() - 1; i++) {
            String idName = idList.get(i);
            int idNum = Integer.parseInt(idName.substring(idName.length() - 1)) - 1;
            if (idNum == solnInd) {
               return true;
            }
        }
        warningLabel.setStyle("-fx-text-fill: red;");
        warningLabel.setText("Answer selected has no value. Please fill in answer.");
        return false;
    }

    public void backQuestionHandler() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../AdminGUI/adViewAsPage.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) backQuestionButton.getScene().getWindow();
        stage.setScene(new Scene(root, 650, 400));
        adViewAsController controller = loader.<adViewAsController>getController();
        controller.assgnName(assignment.getAssignmentName());
        stage.show();
    }
}
