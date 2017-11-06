package Main;

import java.util.*;


public class Data {
    static ArrayList <User> userList = new ArrayList();
    static ArrayList <Student> studentList = new ArrayList();

    //Author: Juan
    //Storage of Assignment data in an Array List.
    public static ArrayList<Assignment> assignmentList =  new ArrayList();//public static ArrayList <Assignment> assignmentList;


    public Data (){
    }

    public static void AddUser (User user){
        userList.add(user);
        System.out.println(user.getLoginID() +" added to User List");
    }

    public static void RemoveUser (String id){
        int location = 0;
        Boolean found = false;
        for( User theuser : userList){
           if (id.equals(theuser.getLoginID())) {
               found = true;
               break;
           }
           location++;
       }

       if (found == true) userList.remove(location);
    }

    /*
        if UserType = Admin, return '0'
        if UserType = Student, return '1'
        if User not exist, return '-1'
     */

    public static int CheckUser (String id, String pw){
        Boolean found = false;
        for (User theuser : userList){
            System.out.println("Looping User: " + theuser.getLoginID());
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
            if (deleteAssignment.getAssignmentID() == AssignmentElement.getAssignmentID()){
                found = true;

                break;
            }
            Location++;
        }
        if (found == true){assignmentList.remove(Location);  updateAssignmentID(assignmentList);}
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
