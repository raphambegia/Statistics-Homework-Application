package Main;

import java.util.ArrayList;

public class Admin extends User {

    public Admin(String id, String pw){
        setLoginID(id);
        setLoginPW(pw);
        setType('a');
        System.out.println();
        Data.AddUser(id,pw);
    }

    public static void createStudent(String fname, String lname, int stid){
        Student newStudent = new Student(fname, lname, stid);
        Data.studentList.add(newStudent);
        System.out.println(fname + " added to Student List");
        int i = 0;
        for (Student s : Data.studentList) {
            i++;
            System.out.println(i + ": " + s.studentId);
        }
    }

    public void removeStudent(int stid){
        for (Student theStudent : Data.studentList){
            if(stid == theStudent.getStudentId()) {
                Data.RemoveUser(theStudent.getLoginID());
                Data.studentList.remove(theStudent);
            }
        }
    }

    public static void addQuestion(String theQuestion, ArrayList<String> mcAnswers, int solnInd, int assignmentNum){
        //Question newQuestion = new Question(theQuestion, mcAnswers, solnInd);
    }

    public void createAssignment(int idNum, String assgnName, String dueDate) {
        //Assignment newAssignment = new Assignment(idNum, assgnName, dueDate);
    }

    public void createAssignment(int idNum, String assgnName) {
        //Assignment newAssignment = new Assignment(idNum, assgnName, dueDate);
    }
}