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
        Data.addStudent(fname, lname, stid);
    }
    public static Assignment getAssignment(String assgnName){
        for (Assignment assgn: Data.assignmentList){
            if(assgnName.equals(assgn.getAssignmentName())) {
                return assgn;
            }
        }
        return null;
    }
    public static void removeStudent(int stid){
        Data.RemoveUser(stid);
    }

    public static void addQuestion(String theQuestion, ArrayList<String> mcAnswers, int solnInd, Assignment assgn){
        Question newQuestion = new Question(theQuestion, mcAnswers, solnInd);
        MongoDB.addToQuestions(assgn.getAssignmentName(), theQuestion, mcAnswers.get(0), mcAnswers.get(1), mcAnswers.get(2), mcAnswers.get(3), mcAnswers.get(4), solnInd);
        assgn.addQuestion(newQuestion);
    }

    public static void createAssignment(String assgnName, String dueDate) {
        Assignment newAssignment = new Assignment(assgnName, dueDate);
        Data.assignmentList.add(newAssignment);
        MongoDB.addToAssignmentWDueDate(assgnName, dueDate);
    }

    public static void createAssignment(String assgnName) {
        Assignment newAssignment = new Assignment(assgnName);
        Data.assignmentList.add(newAssignment);
        MongoDB.addToAssignmentNoDueDate(assgnName);
    }
}