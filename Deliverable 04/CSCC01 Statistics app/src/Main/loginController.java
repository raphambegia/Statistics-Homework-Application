package Main;

import javafx.event.ActionEvent;
//import java.awt.event.MouseEvent;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.collections.*;


import java.io.IOException;

public class loginController {
    int buttonCounter = 0;
    @FXML
    private Label loginError;
    @FXML
    private TextField userName;
    @FXML
    private TextField password;
    @FXML
    Button loginButton;
    @FXML
    Button logOutButton;
    @FXML
    Button addStudent;
    @FXML
    Button viewStudents;
    @FXML
    Button backButton;
    @FXML
    Button backStudent;
    @FXML
    Button cancel;
    @FXML
    TextField fnameAdd;
    @FXML
    TextField lnameAdd;
    @FXML
    TextField sIDAdd;
    @FXML
    Button regStudent;
    @FXML
    Label addedlabel;
    @FXML
    GridPane gridPane;
    @FXML
    Button viewAssignments;
    @FXML
    Button addAssignment;

    //Tableview for students
    @FXML
    TableView<Student> studentTable;
    @FXML
    TableColumn<Student,String> fnameCol;
    @FXML
    TableColumn<Student,String> lnameCol;
    @FXML
    TableColumn<Student,Integer> sidCol;

    public void loginButton(ActionEvent event){
        System.out.println("Hello World");

    }

    public void loginHandler(ActionEvent event) throws IOException {
        /*
        Function Data.CheckUser :
        if UserType = Admin, return '0'
        if UserType = Student, return '1'
        if User not exist, return '-1'
        */
        if(Data.CheckUser(userName.getText(),password.getText()) == 0) {
            Parent root = FXMLLoader.load(getClass().getResource("adminPage.fxml"));
            Stage stage  = (Stage) loginButton.getScene().getWindow();
            stage.setScene(new Scene(root, 650, 400));
            stage.show();
        }else if(Data.CheckUser(userName.getText(),password.getText()) == 1){
            Parent root = FXMLLoader.load(getClass().getResource("studentPage.fxml"));
            Stage stage  = (Stage) loginButton.getScene().getWindow();
            stage.setScene(new Scene(root, 650, 400));
            stage.show();

        }else if(Data.CheckUser(userName.getText(),password.getText()) == -1){
            loginError.setText("Login Failed");
        }
    }
    public void logOutHandler(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("mainView.fxml"));
        Stage stage  = (Stage) logOutButton.getScene().getWindow();
        stage.setScene(new Scene(root, 650, 400));
        stage.show();
    }
    public void viewStudent(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("studentList.fxml"));
        Stage stage  = (Stage) viewStudents.getScene().getWindow();
        stage.setScene(new Scene(root, 650, 400));
        stage.show();
    }

    public void addStudent(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("AddStudent.fxml"));
        Stage stage  = (Stage) addStudent.getScene().getWindow();
        stage.setScene(new Scene(root, 650, 400));
        stage.show();
    }

    public void backMain(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("adminPage.fxml"));
        Stage stage  = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(root, 650, 400));
        stage.show();
    }

    public void backStudent(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("studentList.fxml"));
        Stage stage  = (Stage) backStudent.getScene().getWindow();
        stage.setScene(new Scene(root, 650, 400));
        stage.show();
    }

    public void close(ActionEvent event) throws IOException{
        System.exit(0);
    }

    public void formAddStudent(ActionEvent event) throws IOException{
        Admin.createStudent(fnameAdd.getText(),lnameAdd.getText(),Integer.valueOf(sIDAdd.getText()));
        addedlabel.setText(fnameAdd.getText() +" " + lnameAdd.getText() + " added");
    }

    public void loadStudentTable(){
        ObservableList<Student> students = FXCollections.observableArrayList(Data.studentList);

        fnameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("fName"));
        lnameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("lName"));
        sidCol.setCellValueFactory(new PropertyValueFactory<Student, Integer>("studentId"));
        studentTable.setItems(students);
    }
    public void viewAssignment(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("assignmentPage.fxml"));
        Stage stage  = (Stage) viewAssignments.getScene().getWindow();
        stage.setScene(new Scene(root, 650, 400));
        stage.show();
    }
    public void assignmentAdd(ActionEvent event) throws IOException{
        Button assignmentButton = new Button("Assignment");
        assignmentButton.setMinWidth(30);
        assignmentButton.setMinHeight(10);
        gridPane.add(assignmentButton, 0, 0);
    }

}
