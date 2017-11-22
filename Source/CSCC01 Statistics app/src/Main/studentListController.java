package Main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * ADMIN VIEWS LIST OF STUDENTS TO ADD/REMOVE
 */
public class studentListController {

    @FXML
    Button addStudent;
    @FXML
    Button backButton;

    //Tableview for students
    @FXML
    TableView<Student> studentTable;
    @FXML
    TableColumn<Student,String> fnameCol;
    @FXML
    TableColumn<Student,String> lnameCol;
    @FXML
    TableColumn<Student,Integer> sidCol;

    public void initialize() {
        loadStudentTable();
    }

    public void addStudent(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../AdminGUI/AddStudent.fxml"));
        Stage stage  = (Stage) addStudent.getScene().getWindow();
        stage.setScene(new Scene(root, 650, 400));
        stage.show();
    }

    public void loadStudentTable(){
        ObservableList<Student> students = FXCollections.observableArrayList(Data.studentList);

//        for(Student student : students) {
//            System.out.println(student.getFName());
//        }

        fnameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("fName"));
        lnameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("lName"));
        sidCol.setCellValueFactory(new PropertyValueFactory<Student, Integer>("studentId"));
        studentTable.setItems(students);
    }

    public void backMain(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("../AdminGUI/adminPage.fxml"));
        Stage stage  = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(root, 650, 400));
        stage.show();
    }

    public void rmStudent() {
        Student student = studentTable.getSelectionModel().getSelectedItem();
        if (student != null) {
            Admin.removeStudent(student.getStudentId());
            loadStudentTable();
        }
        // Does nothing when no student is selected
    }
}
