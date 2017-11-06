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
    private RadioButton buttonMC1;
    @FXML
    private RadioButton buttonMC2;
    @FXML
    private RadioButton buttonMC3;
    @FXML
    private RadioButton buttonMC4;
    @FXML
    private RadioButton buttonMC5;
    @FXML
    private ToggleGroup answerToggle;
    @FXML
    private Button addQuestionButton;
    @FXML
    private Button backQuestionButton;

    private String NOANSWERS = "NOANSWERS";
    private ArrayList<String> answerList = new ArrayList<String>();
    private ArrayList<String> idList = new ArrayList<String>();
    private List<TextField> mcList = new ArrayList<TextField>();

    public void addQuestionHandler() {
        // Check if question field is empty
        if (questionField.getText().replaceAll("\\s","").length() == 0) {
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
            answerList = createAnsList();
        } else {
            return;
        }

        // Check if a solution has been chosen, and it's position if so
        RadioButton solnSelected = (RadioButton) answerToggle.getSelectedToggle();
        if (solnSelected == null) {
            warningLabel.setText("Please select the correct answer");
            return;
        }
        int solnInd = findSolutionInd(solnSelected.getId());

        // Check if the solution index and answer index match
        if (!solnMatch(solnInd)) {
            return;
        }

        // Add question to assignment & clear all fields
        Admin.addQuestion(questionField.getText(), answerList, solnInd);
        warningLabel.setText("Question added!");
        answerToggle.getSelectedToggle().setSelected(false);
        questionField.setText("");
        for(TextField tf : mcList) {
            tf.setText("");
        }
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
        int count = 0;

        for (TextField tf : mcList) {
            if (tf.getText().replaceAll("\\s","").length() != 0) {
                answerList.add(tf.getText());
                idList.add(tf.getId());
                count++;
            }
        }

//        if (mcfield1.getText().replaceAll("\\s","").length() != 0) {
//            answerList.add(mcfield1.getText());
//            idList.add(mcfield1.getId());
//            count++;
//        } else if (mcfield2.getText().replaceAll("\\s","").length() != 0) {
//            answerList.add(mcfield2.getText());
//            idList.add(mcfield2.getId());
//            count++;
//        } else if (mcfield3.getText().replaceAll("\\s","").length() != 0) {
//            answerList.add(mcfield3.getText());
//            idList.add(mcfield3.getId());
//            count++;
//        } else if (mcfield4.getText().replaceAll("\\s","").length() != 0) {
//            answerList.add(mcfield4.getText());
//            idList.add(mcfield4.getId());
//            count++;
//        } else if (mcfield5.getText().replaceAll("\\s","").length() != 0) {
//            answerList.add(mcfield5.getText());
//            idList.add(mcfield5.getId());
//            count++;
//        }

        if (count == 0) {
            answerList.add(NOANSWERS);
            warningLabel.setText("Please include at least one answer.");
        }

        return answerList;
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
        warningLabel.setText("Answer selected has no value. Please fill in answer.");
        return false;
    }

    public void backQuestionHandler() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("assignmentPage.fxml"));
        Stage stage  = (Stage) backQuestionButton.getScene().getWindow();
        stage.setScene(new Scene(root, 650, 400));
        stage.show();
    }
}
