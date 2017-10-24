package Main;

import javafx.event.ActionEvent;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;


import java.io.IOException;

public class loginController {
    @FXML
    private Label label1;
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
    TableView studentTable;
    @FXML
    Button cancel;

    public void loginButton(ActionEvent event){
        System.out.println("Hello World");

    }

    public void loginHandler(ActionEvent event) throws IOException {
        if(userName.getText().equals("admin") && password.getText().equals("pass")) {
            Parent root = FXMLLoader.load(getClass().getResource("adminPage.fxml"));
            Stage stage  = (Stage) loginButton.getScene().getWindow();
            stage.setScene(new Scene(root, 650, 400));
            stage.show();
        }else{
            label1.setText("Login Failed");
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

    public void studentTable(ActionEvent event) throws IOException{

    }
}
