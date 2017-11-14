package Main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 * ADMIN ADDS AN ASSIGNMENT
 */
public class assignmentController {

    @FXML
    private TextField aID;
    @FXML
    private TextField aName;
    @FXML
    private Label dueDateLabel;
    @FXML
    private CheckBox aNoDueDate;
    @FXML
    private Label assignmentLabel;
    @FXML
    private DatePicker aDatePicker;
    @FXML
    private Button createAssgnButton;
    @FXML
    private GridPane gridPane;
    @FXML
    Button backToAssignments;

    public static final String INVALID_DATE = "INVALIDDATE";

    public void createAssgnHandler() {
        // Make sure the assignment name is 1-25 char and not used
        if (aName.getText().trim().length() == 0 || aName.getText().trim().length() > 25) {
            assignmentLabel.setStyle("-fx-text-fill: red;");
            assignmentLabel.setText("Please enter an assignment name between 1-25 characters.");
            return;
        }
        if (isNameUsed()) {
            return;
        }

        // Create an assignment based on whether it has a duedate or not
        if (aNoDueDate.isSelected()) {
            Admin.createAssignment(aName.getText());
            assignmentLabel.setStyle("-fx-text-fill: green;");
            assignmentLabel.setText(aName.getText() + " created");
        } else {
            // If they want a duedate they need to select one!
            if (aDatePicker.getValue() == null) {
                assignmentLabel.setStyle("-fx-text-fill: red;");
                assignmentLabel.setText("Please pick a valid due date");
                return;
            }
            //The duedate must be after today
            String dueDateStr = validDueDate();
            if (dueDateStr.equals(INVALID_DATE)) {
                return;
            } else {
                Admin.createAssignment(aName.getText(), dueDateStr);
                assignmentLabel.setStyle("-fx-text-fill: green;");
                assignmentLabel.setText(aName.getText() + " with due date " + dueDateStr + " created");
            }
        }
    }

    public void noDeadlineHandler() {
        if (aNoDueDate.isSelected()) {
            dueDateLabel.setVisible(false);
            aDatePicker.setVisible(false);
            assignmentLabel.setText("");
        } else {
            dueDateLabel.setVisible(true);
            aDatePicker.setVisible(true);
        }
    }
    public void backToViewAssgn(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("assignmentPage.fxml"));
        Stage stage  = (Stage) backToAssignments.getScene().getWindow();
        stage.setScene(new Scene(root, 650, 400));
        stage.show();
    }

    /*
    If the due date is after today, returns the duedate in yyyy/MM/dd format.
    Otherwise returns INVALID_DATE
     */
    private String validDueDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

        LocalDate ld = aDatePicker.getValue();
        Calendar cal = Calendar.getInstance();
        cal.set(ld.getYear(), ld.getMonthValue(), ld.getDayOfMonth());

        Date todaysDate = new Date();
        Date dueDate = Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());

        if (dueDate.before(todaysDate)) {
            assignmentLabel.setStyle("-fx-text-fill: red;");
            assignmentLabel.setText("Please enter a date after today.");
            return INVALID_DATE;
        } else {
            assignmentLabel.setText("");
        }

        String dueDateStr = dateFormat.format(dueDate);
        return dueDateStr;
    }

    /**
     * Assignment names must be unique.
     * @return TRUE if the assignment name is already in use
     */
    private Boolean isNameUsed() {
        for (Assignment curr : Data.assignmentList) {
            if (aName.getText().equals(curr.getAssignmentName())) {
                assignmentLabel.setStyle("-fx-text-fill: red;");
                assignmentLabel.setText("Assignment name already in use.");
                return true;
            }
        }
        return false;
    }
}
