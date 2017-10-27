package Main;

public class Assignment {
    /*
    Background:
        Assignments serve the purpose of housing assigned questions for the student users. Through the application's
        GUI, they are:
            - created and modified by Admin users
            - used by student users to:
                > access & complete questions
                > obtain a mark
                > revisit and redo Assignment

    Description:
        Encompasses the Assignment object: its internal objects, and related functions as follows:

            Objects:
                - this Assignment ID
                - this Assignment Name

            Functions:
                - retrieving this Assignment ID
                - retrieving this Assignment Name
                - creating this Assignment object in Database
                - removing this Assignment object from Database
     */

    private int AssignmentID;
    private String AssignmentName;

    public Assignment(int id, String Name){
        this.AssignmentID = id;
        this.AssignmentName = Name;

    }

    public int getAssignmentID(int id){
        return AssignmentID;
    }

    public String getAssignmentID(String Name){
        return AssignmentName;
    }

    public void createAssignment(int id, String Name){
        Assignment newAssignment = new Assignment(id, Name);
        //Data.studentList.add(newStudent);
        /*
        Add newAssignment to "database"
        See Data.java for guide
         */
    }

    /*
        RemoveAssignment{}

        Remove Assignment from "database"
        See Student.java for guide
    */

}
