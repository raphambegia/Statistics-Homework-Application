package Main;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

public class questionController {

    @FXML
    private TextField questionField;
    @FXML
    private TextField mc1field;
    @FXML
    private TextField mc2field;
    @FXML
    private TextField mc3field;
    @FXML
    private TextField mc4field;
    @FXML
    private TextField mc5field;
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

    public void addQuestionHandler() {
        int solnInd = findSolution();
        if (solnInd == -1) {
            return;
        }
        ArrayList<String> answerList = new ArrayList<String>();
        answerList.add(mc1field.getText());
        answerList.add(mc2field.getText());
        answerList.add(mc3field.getText());
        answerList.add(mc4field.getText());
        answerList.add(mc5field.getText());

        Admin.addQuestion(questionField.getText(), answerList, solnInd, solnInd);
    }

    /*
    Returns 0 if no answer is chosen.
     */
    public int findSolution() {
        if (answerToggle.getSelectedToggle() == null) {
            warningLabel.setText("Please select the correct answer");
        } else if (answerToggle.getSelectedToggle() == buttonMC1) {
            return 1;
        } else if (answerToggle.getSelectedToggle() == buttonMC2) {
            return 2;
        } else if (answerToggle.getSelectedToggle() == buttonMC3) {
            return 3;
        } else if (answerToggle.getSelectedToggle() == buttonMC4) {
            return 4;
        } else if (answerToggle.getSelectedToggle() == buttonMC5) {
            return 5;
        }
        return -1;
    }
}
