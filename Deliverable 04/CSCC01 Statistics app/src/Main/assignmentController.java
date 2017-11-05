package Main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;

public class assignmentController {

    @FXML
    private TextField aID;
    @FXML
    private TextField aName;
    @FXML
    private Label dueDateLabel;
    @FXML
    private TextField aDueDate;
    @FXML
    private CheckBox aNoDueDate;
    @FXML
    private Label assignmentLabel;
    @FXML
    private Button createAssgnButton;

    public void createAssgnHandler() {
        if (aNoDueDate.isSelected()) {
            Admin.createAssignment(aName.getText());
        } else {
            Admin.createAssignment(aName.getText(), aDueDate.getText());
        }
    }

    public void noDeadlineHandler() {
        if (aNoDueDate.isSelected()) {
            dueDateLabel.setVisible(false);
            aDueDate.setVisible(false);
        } else {
            dueDateLabel.setVisible(true);
            aDueDate.setVisible(true);
        }
    }
}
