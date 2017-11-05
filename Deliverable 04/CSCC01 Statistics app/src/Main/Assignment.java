package Main;

import java.util.*;

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

    //ID denotes Assignment's place in the list. 1st Assignment: ID = 0. Updated automatically when AddAssignment or
    // RemoveAssignment called. Same applies for Question ID.
    private int AssignmentID;
    private String AssignmentName;
    private ArrayList<Question> questionList =  new ArrayList<Question>();


    public Assignment(String Name){
        this.AssignmentName = Name;
    }

    public int getAssignmentID(){
        return AssignmentID;
    }
    public String getAssignmentName(){
        return AssignmentName;
    }

    public void changeAssignmentID(int newID){
        AssignmentID = newID;
    }

    /*
    Question Functions
     */
    public void AddQuestion (Question newQuestion){ questionList.add(newQuestion); updateQuestionID(questionList); }

    public void removeQuestion (Question deleteQuestion){
        Boolean found = false;
        //Location: location in ArrayList
        int Location = 0;
        for (Question QuestionElement : questionList ){
            if (deleteQuestion.getQuestionID() == QuestionElement.getQuestionID()){
                found = true;

                break;
            }
            Location++;
        }
        if (found == true){questionList.remove(Location); updateQuestionID(questionList);}
    }

    public void updateQuestionID (ArrayList<Question> ListofQuestions){
        //Location in ArrayList
        int Location = 0;
        for (Question QuestionElement : ListofQuestions ){
            QuestionElement.changeQuestionID(Location);
            Location++;
        }
    }

    public ArrayList <Question> getQuestionList(){
        return questionList;
    }
    /*
    End of Question functions
     */
}

