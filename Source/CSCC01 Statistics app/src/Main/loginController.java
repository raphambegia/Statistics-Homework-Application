package Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.io.IOException;

public class loginController {
    @FXML
    private Label loginError;
    @FXML
    private TextField userName;
    @FXML
    private TextField password;
    @FXML
    Button loginButton;
    @FXML
    Button viewStudents;
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
    Button viewAssignments;
    @FXML
    Button logOutButton;

    public Student currStudent;

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
            Parent root = FXMLLoader.load(getClass().getResource("../AdminGUI/adminPage.fxml"));
            Stage stage  = (Stage) loginButton.getScene().getWindow();
            stage.setScene(new Scene(root, 650, 400));
            stage.show();
        }else if(Data.CheckUser(userName.getText(),password.getText()) == 1){
            for(Student s : Data.getStudentList()) {
                if(s.getFName().equals(userName.getText()) && s.getLName().equals(password.getText())) {
                    currStudent = s;
                }
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../StudentGUI/studentPage.fxml"));
            Parent root = loader.load();
            Stage stage  = (Stage) loginButton.getScene().getWindow();
            stage.setScene(new Scene(root, 650, 400));
            studentPageController controller = loader.<studentPageController>getController();
            controller.passStudent(currStudent);
            stage.show();

        }else if(Data.CheckUser(userName.getText(),password.getText()) == -1){
            loginError.setText("Login Failed");
        }
    }

    public void viewStudent(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("../AdminGUI/studentList.fxml"));
        Stage stage  = (Stage) viewStudents.getScene().getWindow();
        stage.setScene(new Scene(root, 650, 400));
        stage.show();
    }

    public void backStudent(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("../AdminGUI/studentList.fxml"));
        Stage stage  = (Stage) backStudent.getScene().getWindow();
        stage.setScene(new Scene(root, 650, 400));
        stage.show();
    }

    public void close(ActionEvent event) throws IOException{
        System.exit(0);
    }

    public void formAddStudent(ActionEvent event) throws IOException{
        boolean idExists = false;
        if (sIDAdd.getText().chars().allMatch(Character::isDigit)) {
            for(Student curr : Data.getStudentList()) {
                if(curr.getStudentId() == Integer.valueOf(sIDAdd.getText())) {
                    addedlabel.setStyle("-fx-text-fill: red;");
                    addedlabel.setText("ID taken, please select a unique ID number.");
                    idExists = true;
                }
            }
        } else {
            addedlabel.setStyle("-fx-text-fill: red;");
            addedlabel.setText("ID must be a number. Please try again.");
        }
        if (!idExists) {
            idExists = true;
            Admin.createStudent(fnameAdd.getText(),lnameAdd.getText(),Integer.valueOf(sIDAdd.getText()));
            addedlabel.setStyle("-fx-text-fill: green;");
            addedlabel.setText(fnameAdd.getText() +" " + lnameAdd.getText() + " added");
        }
    }

    public void viewAssignment(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("../AdminGUI/assignmentPage.fxml"));
        Stage stage  = (Stage) viewAssignments.getScene().getWindow();
        stage.setScene(new Scene(root, 650, 400));
        stage.show();
    }

    public void logOutHandler(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("../AdminGUI/mainView.fxml"));
        Stage stage  = (Stage) logOutButton.getScene().getWindow();
        stage.setScene(new Scene(root, 650, 400));
        stage.show();
    }

}
