package Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class studentPageController {

    @FXML
    Button studentAssgnView;
    @FXML
    Button logOutButton;

    public Student currStudent;

    public void passStudent(Student stu) {
        currStudent = stu;
    }

    public void studentAssignmentView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("studentAssignment.fxml"));
        Parent root = loader.load();
        Stage stage  = (Stage) studentAssgnView.getScene().getWindow();
        stage.setScene(new Scene(root, 650, 400));
        stuAssignmentPageController controller = loader.<stuAssignmentPageController>getController();
        controller.passStudent(currStudent);
        stage.show();
    }

    public void logOutHandler(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("mainView.fxml"));
        Stage stage  = (Stage) logOutButton.getScene().getWindow();
        stage.setScene(new Scene(root, 650, 400));
        stage.show();
    }
}
