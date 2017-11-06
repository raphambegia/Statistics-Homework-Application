package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main extends Application {
    Stage stage;
    Scene scene1, sccene2;


    @Override
    public void start(Stage primaryStage) throws Exception{
        this.stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("mainView.fxml"));
        primaryStage.setTitle("Statistics Application");
        primaryStage.setScene(new Scene(root, 650, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        Data AllData;
        Admin admin1 = new Admin("admin","pass");
        Student stu = new Student("john", "doe", 01);
        Admin.createAssignment("A1");
        Data.studentList.add(stu);
        launch(args);
    }
}
