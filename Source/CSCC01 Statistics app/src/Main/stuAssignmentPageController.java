package Main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * STUDENT VIEWS THEIR LIST OF ASSIGNMENTS
 */
public class stuAssignmentPageController {

    @FXML
    Button secretButton;
    @FXML
    GridPane studentGrid;
    @FXML
    Button assignmentBack;
    @FXML
    VBox assignmentBox;

    public Student currStudent;

    public void passStudent(Student student) {
        currStudent = student;
        studentDisplayAssignment();
    }

    public void studentDisplayAssignment(){
        for(int i=0;i<Data.assignmentList.size();i++){
            Assignment assign = Data.assignmentList.get(i);
            Label space = new Label(" ");
            Button assgnButton = new Button(assign.getAssignmentName());
            Label availLabel = new Label();
            TextFlow assignFlow = new TextFlow();
            if (assign.isAvailable()) {
                assgnButton.setStyle("-fx-text-fill: green;");
                availLabel.setStyle("-fx-text-fill: green;");
                if (assign.getDueDate() == null) {
                    availLabel.setText(" * Always Available! * ");
                } else {
                    availLabel.setText(" * Deadline: " + assign.getDueDate());
                }
            } else {
                assgnButton.setStyle("-fx-text-fill: black;");
                availLabel.setText(" Deadline: " + assign.getDueDate());
                availLabel.setStyle("-fx-text-fill: black;");
            }
            assignFlow.getChildren().addAll(space, assgnButton, availLabel);
            assgnButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../StudentGUI/viewAssignmentPage.fxml"));
                    try {
                        Parent root = loader.load();
                        Stage stage = (Stage) secretButton.getScene().getWindow();
                        stage.setScene(new Scene(root, 650, 400));
                        viewAssignmentController controller = loader.<viewAssignmentController>getController();
                        controller.initAssignment(assign, currStudent);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            assgnButton.setMinWidth(180);
            assgnButton.setMinHeight(10);
//            studentGrid.setHalignment(assgnButton, HPos.CENTER);
//            studentGrid.add(assgnButton, 0,i);
            Separator sep = new Separator();
            sep.setVisible(false);
            sep.setMinHeight(4);
            assignmentBox.getChildren().addAll(assignFlow, sep);
        }
    }

    public void studentBacktoMain(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../StudentGUI/studentPage.fxml"));
        Parent root = loader.load();
        Stage stage  = (Stage) assignmentBack.getScene().getWindow();
        stage.setScene(new Scene(root, 650, 400));
        studentPageController controller = loader.<studentPageController>getController();
        controller.passStudent(currStudent);
        stage.show();
    }

    /**
     * keep this
     * @param event
     * @throws IOException
     */
    public void assgnSelected(ActionEvent event) throws IOException {
    }
}
