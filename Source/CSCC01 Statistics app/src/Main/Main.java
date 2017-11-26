package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    Stage stage;
    Scene scene1, sccene2;


    @Override
    public void start(Stage primaryStage) throws Exception{
        this.stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("../AdminGUI/mainView.fxml"));
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
        //Admin.createAssignment("A1", "2016/09/09");
        List<Document> assgns = MongoDB.getAssignmentsNoDueDate();
        for(Document assgn: assgns) {
            Admin.createAssignment((String) assgn.get("assignName"));
        }

        List<Document> assgns2 = MongoDB.getAssignmentsWDueDate();
        for(Document assgn: assgns2) {
            Admin.createAssignment((String) assgn.get("assignName"), (String) assgn.get("dueDate"));
        }

        List<Document> questions = MongoDB.getQuestions();
        for(Document question: questions) {
            ArrayList<String> mc = new ArrayList();
            mc.add((String) question.get("mc1"));
            mc.add((String) question.get("mc2"));
            mc.add((String) question.get("mc3"));
            mc.add((String) question.get("mc4"));
            mc.add((String) question.get("mc5"));
            Assignment assgn = Admin.getAssignment((String) question.get("assignName"));
            Admin.addQuestion((String) question.get("question"), mc, (int) question.get("soln"), assgn);
        }
        for(Assignment assignment: Data.assignmentList ){
            List<Document> scores = MongoDB.getScores(assignment.getAssignmentName());
            for(Student student : Data.studentList){
                for(Document studentScores: scores){
                    if(student.getStudentId() == (int) studentScores.get("stID")){
                        double setScore = (int) studentScores.get("stID");
                        student.setAssignmentMarks(assignment.getAssignmentName(),setScore );
                    }
                }
            }

        }

        //ArrayList<String> al = new ArrayList<String>();
        //al.add("karen");
        //al.add("kareb");
        //Question q = new Question("who is the best", al, 0);
        //Question qq = new Question("who is the bestest", al, 1);
        //Data.assignmentList.get(0).addQuestion(q);
       // Data.assignmentList.get(0).addQuestion(qq);

        //System.out.println(Data.assignmentList.get(0).getQuestionList().get(0).getTheQuestion());

        //Data.studentList.add(stu);
        launch(args);
    }
}
