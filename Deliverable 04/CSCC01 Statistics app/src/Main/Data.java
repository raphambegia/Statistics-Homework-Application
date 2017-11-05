package Main;

import java.util.*;

public class Data{

    //Author: Juan
    //Storage of Assignment data in an Array List.
    public static ArrayList<Assignment> assignmentList =  new ArrayList<Assignment>();//public static ArrayList <Assignment> assignmentList;

    public Data (){
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
