package Main;

import com.mongodb.MongoClient;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.bson.Document;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
        MongoDB newDB = new MongoDB();
        newDB.connect();
        Data AllData;
        Admin admin1 = new Admin("admin","pass");
        List<Document> students = MongoDB.getStudents();
        for(Document student: students) {
            Student newStudent = new Student((String) student.get("fName"),(String) student.get("lName"),(int) student.get("stID"));
            Data.studentList.add(newStudent);
        }
        Admin.createAssignment("A1", "2016/09/09");

        ArrayList<String> al = new ArrayList<String>();
        al.add("k");
        al.add("knn");
        Question q = new Question("who is the best", al, 0);
        Question qq = new Question("who is the bestest", al, 1);
        Data.assignmentList.get(0).addQuestion(q);
        Data.assignmentList.get(0).addQuestion(qq);

        //System.out.println(Data.assignmentList.get(0).getQuestionList().get(0).getTheQuestion());
        MongoDB.printStudent();
        launch(args);
    }
}
