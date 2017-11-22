package Main;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

public class studentMarkListController {

    @FXML
    Button toSPageButton;

    @FXML
    TableView<Student> stuMarksTable;
    @FXML
    TableColumn<Student, String> assignNameCol;
    @FXML
    TableColumn<Student, String> attemptOneCol;
    @FXML
    TableColumn<Student, Integer> attemptTwoCol;
    @FXML
    TableColumn<Student, Integer> bestMarkCol;

    private Student student;

    public void initStudent(Student stu) {
        student = stu;
        loadStudentMarks();
    }

    public void loadStudentMarks() {
        for(String str : student.getCompletedAssignNames()) {
            assignNameCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Student, String> c) {
                    return new SimpleStringProperty(str);
                }
            });
        }

    }

    public void toStudentPage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../StudentGUI/studentPage.fxml"));
        Parent root = loader.load();
        Stage stage  = (Stage) toSPageButton.getScene().getWindow();
        stage.setScene(new Scene(root, 650, 400));
        studentPageController controller = loader.<studentPageController>getController();
        controller.passStudent(student);
        stage.show();
    }
}
