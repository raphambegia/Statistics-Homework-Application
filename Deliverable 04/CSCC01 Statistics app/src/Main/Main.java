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

        ArrayList<String> al = new ArrayList<String>();
        al.add("k");
        al.add("knn");
        Question q = new Question("who is the best", al, 0);
        Question qq = new Question("who is the bestest", al, 0);
        Data.assignmentList.get(0).addQuestion(q);
        Data.assignmentList.get(0).addQuestion(qq);

        //System.out.println(Data.assignmentList.get(0).getQuestionList().get(0).getTheQuestion());

        Data.studentList.add(stu);
        launch(args);
    }
}
