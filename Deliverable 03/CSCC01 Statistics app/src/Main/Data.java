package Main;

import java.util.*;

public class Data {
    static Map Users = new HashMap();
    static ArrayList <Student> studentList;

    //Author: Juan
    //Storage of Assignment data in an Array List.
    public static ArrayList<Assignment> assignmentList =  new ArrayList<Assignment>();//public static ArrayList <Assignment> assignmentList;

    public Data (){
    }

    public static void AddUser (String id, String pw){
        Users.put(id,pw);
        System.out.println(id+" added");
    }

    public static void RemoveUser (String id){
        Users.remove(id);
    }

    public static boolean CheckUser (String id, String pw){
        if ( pw.equals(Users.get(id))) return true;
        return false;
    }

    /*
    Author: Juan
    Functions for assignmentList.
     */
    public static void AddAssignment (Assignment newAssignment){
        assignmentList.add(newAssignment);
    }

    public static void deleteAssignment (Assignment deleteAssignment){
        for (Assignment AssignmentElement : assignmentList ){
            if (deleteAssignment.getAssignmentID() == AssignmentElement.getAssignmentID()){
                AssignmentElement.ChangeDeleteStatus(Boolean.TRUE);
            }
        }
    }

    public static ArrayList <Assignment> getAssignmentList(){
        return assignmentList;
    }

    //For Testing
    public void printAssignments(){
        for (Assignment AssignmentElement : assignmentList ){
            if (AssignmentElement.getAssignmentStatus() == Boolean.FALSE){
                System.out.println("Assignment ID: " + AssignmentElement.getAssignmentID() + "Assignment Name: " + AssignmentElement.getAssignmentName() );
            }
        }
    }
        /*
    End of Functions for assignmentList.
     */



}
