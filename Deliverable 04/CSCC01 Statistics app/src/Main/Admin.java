package Main;

import java.util.ArrayList;
import java.util.function.Predicate;

public class Admin extends User {

    public Admin(String id, String pw){
        setLoginID(id);
        setLoginPW(pw);
        setType('a');
        System.out.println();
        Data.AddUser(this);
    }

    public static void createStudent(String fname, String lname, int stid){
        Student newStudent = new Student(fname, lname, stid);
        Data.studentList.add(newStudent);
        System.out.println(fname + " added to Student List");
        int i = 0;
        for (Student s : Data.studentList) {
            i++;
        }
    }

    public static void removeStudent(int stid){
        Student student = null;
        for (Student theStudent : Data.studentList){
            if(stid == theStudent.getStudentId()) {
                student = theStudent;
                break;
                //Data.RemoveUser(theStudent.getLoginID());
                //Data.studentList.remove(theStudent);
            }
        }
        Predicate<Student> stuPred = theStudent -> theStudent.getStudentId() == stid;
        Data.studentList.removeIf(stuPred);
        Data.RemoveUser(student.getLoginID());
    }

    public static void addQuestion(String theQuestion, ArrayList<String> mcAnswers, int solnInd){
        Question newQuestion = new Question(theQuestion, mcAnswers, solnInd);
    }

    public static void createAssignment(String assgnName, String dueDate) {
        Assignment newAssignment = new Assignment(assgnName, dueDate);
        Data.assignmentList.add(newAssignment);
    }

    public static void createAssignment(String assgnName) {
        Assignment newAssignment = new Assignment(assgnName);
        Data.assignmentList.add(newAssignment);
    }
}