package Main;

public class Assignment {
    /*
    Author: Juan

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
                - this Assignment Delete Status

            Functions:
                - retrieving this Assignment ID
                - retrieving this Assignment Name
                - retrieving this Assignment Deletion Status
                - change this Assignment Deletion Status
     */

    private int AssignmentID;
    private String AssignmentName;
    private Boolean DeleteStatus;

    public Assignment(int id, String Name){
        this.AssignmentID = id;
        this.AssignmentName = Name;

        //Instead of removing from ArrayList, Assignment will have DeleteStatus = True if it's been deleted by user.
        //The idea is, If DeleteStatus = True, then we ignore the assignment; though it will still remain in the List.
        this.DeleteStatus = Boolean.FALSE;

    }

    public int getAssignmentID(){
        return AssignmentID;
    }

    public String getAssignmentName(){
        return AssignmentName;
    }

    public Boolean getAssignmentStatus(){
        return DeleteStatus;
    }

    //Removes Assignment by changing a Status. Does not remove from Array List in Data.java
    public void ChangeDeleteStatus(Boolean Status){
        this.DeleteStatus = Status;
    }

}
