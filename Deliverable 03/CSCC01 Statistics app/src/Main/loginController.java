package Main;

import javafx.event.ActionEvent;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;


import java.io.IOException;

public class loginController {
    @FXML
    private Label label1;
    @FXML
    private TextField userName;
    @FXML
    private PasswordField password;

    public void loginButton(ActionEvent event){
        System.out.println("Hello World");

    }
    public void loginHandler(ActionEvent event) throws IOException {
        if(userName.getText().equals("admin") && password.getText().equals("pass")) {
            Parent root1 = FXMLLoader.load(getClass().getResource("adminPage.fxml"));
            Stage stage = new Stage();
            stage.setTitle("ABC");
            stage.setScene(new Scene(root1));
            stage.show();
        }else{
            label1.setText("Login Failed");
        }
    }
}
