package Main;

import java.util.*;
import java.util.function.Predicate;


public class Data {
    static ArrayList <User> userList = new ArrayList();
    static ArrayList <Student> studentList = new ArrayList<Student>();

    //Author: Juan
    //Storage of Assignment data in an Array List.
    public static ArrayList<Assignment> assignmentList =  new ArrayList();//public static ArrayList <Assignment> assignmentList;


    public Data (){
    }

    public static void AddUser (User user){
        userList.add(user);
        System.out.println(user.getLoginID() +" added to User List");
    }

    public static void addStudent(String fname, String lname, int stid) {
        Student newStudent = new Student(fname, lname, stid);
        MongoDB.addToStudents(fname, lname, stid);
        MongoDB.update(); // Adds student to Data studentList
        //Data.studentList.add(newStudent); theoretically don't need this line because it's in mongo?
        System.out.println(fname + " added to Student List");
    }

    public static void RemoveUser (int stid){

        Student student = null;
        for (Student theStudent : Data.studentList){
            if(stid == theStudent.getStudentId()) {
                student = theStudent;
                break;
            }
        }
        Predicate<Student> stuPred = theStudent -> theStudent.getStudentId() == stid;
        Data.studentList.removeIf(stuPred);
        MongoDB.removeStudent(stid);

        int location = 0;
        Boolean found = false;
        for( User theuser : userList){
           if (student.getLoginID().equals(theuser.getLoginID())) {
               found = true;
               break;
           }
           location++;
       }

       if (found == true) userList.remove(location);
    }

    public static ArrayList<Student> getStudentList() {
        return studentList;
    }

    /*
        if UserType = Admin, return '0'
        if UserType = Student, return '1'
        if User not exist, return '-1'
     */

    public static int CheckUser (String id, String pw){
        Boolean found = false;
        for (User theuser : userList){
            if (theuser.getLoginID().equals(id) && theuser.getLoginPW().equals(pw)) {
                if(theuser.getType() == 'a') return 0;
                if(theuser.getType() == 's') return 1;
                break;
            }
        }
        return -1;
    }

    /*
    Author: Juan
    Functions for assignmentList.
     */
    public static void AddAssignment (Assignment newAssignment){
        assignmentList.add(newAssignment);
        updateAssignmentID(assignmentList);
    }

    public static ArrayList <Assignment> getAssignmentList(){
        return assignmentList;
    }

    public static void removeAssignment (Assignment deleteAssignment){
        Boolean found = false;
        //Location in ArrayList
        int Location = 0;
        for (Assignment AssignmentElement : assignmentList ){
            if(deleteAssignment.getAssignmentName().equals(AssignmentElement.getAssignmentName())) {
            //if (deleteAssignment.getAssignmentID() == AssignmentElement.getAssignmentID()){
                found = true;

                break;
            }
            Location++;
        }
        if (found == true){
            Predicate<Assignment> assgnPred = AssignmentElement -> AssignmentElement.getAssignmentName() == deleteAssignment.getAssignmentName();
            Data.assignmentList.removeIf(assgnPred);
            //assignmentList.remove(Location);
            //updateAssignmentID(assignmentList);
        }
        MongoDB.removeAssignment(deleteAssignment.getAssignmentName());
    }

    public static void updateAssignmentID (ArrayList<Assignment> ListofAssignments){
        //Location in ArrayList
        int Location = 0;
        for (Assignment AssignmentElement : ListofAssignments ){
            AssignmentElement.changeAssignmentID(Location);
            Location++;
        }
    }

    /*
    End of Functions for assignmentList.
     */
}
