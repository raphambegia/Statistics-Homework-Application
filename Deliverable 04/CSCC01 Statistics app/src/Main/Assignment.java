package Main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private String dueDate;
    private ArrayList<Question> questionList =  new ArrayList<Question>();
    int assignmentSize = 5;

    /**
     *
     * @param name
     * @param dueDate YYMMDD
     */
    public Assignment(String name, String dueDate) {
        this.AssignmentName = name;
        this.dueDate = dueDate;
    }

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

    public String getDueDate() {
        return this.dueDate;
    }

    /**
     * @return FALSE if the due date of the assignment has passed
     */
    public Boolean isAvailable() {
        if(this.getDueDate() == null) {
            return true;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date todaysDate = new Date();
        try {
            Date dueDate = dateFormat.parse(this.getDueDate());
            if(dueDate.after(todaysDate)) {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /*
    Question Functions
     */
    public void addQuestion (Question newQuestion){ this.questionList.add(newQuestion); updateQuestionID(questionList); }

//    public void removeQuestion (Question deleteQuestion){
//        Boolean found = false;
//        //Location: location in ArrayList
//        int Location = 0;
//        for (Question QuestionElement : questionList ){
//            if (deleteQuestion.getQuestionID() == QuestionElement.getQuestionID()){
//                found = true;
//
//                break;
//            }
//            Location++;
//        }
//        if (found == true){questionList.remove(Location); updateQuestionID(questionList);}
//    }

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

    public ArrayList <Question> getRandomQuestionList(){
        int count = 0;
        ArrayList <Question> randomList = new ArrayList<>();
        Random randA = new Random();
        if(questionList.size() <= assignmentSize) {
            while(randomList.size() < questionList.size()){
                int randQuestion = randA.nextInt(questionList.size());

                //check if a question is drawn twice
                while (count < randomList.size()){
                    count = 0;
                    for (Question theQuestion : randomList) {
                        if (questionList.get(randQuestion).getTheQuestion().equals(theQuestion.getTheQuestion())){
                            randQuestion = randA.nextInt(questionList.size());
                        }
                        else count++;
                    }
                }
                randomList.add(questionList.get(randQuestion));
            }
        }

        else{
            while(randomList.size() < assignmentSize){
                int randQuestion = randA.nextInt(questionList.size());

                s//check if a question is drawn twice
                while (count < randomList.size()){
                    count = 0;
                    for (Question theQuestion : randomList) {
                        if (questionList.get(randQuestion).getTheQuestion().equals(theQuestion.getTheQuestion())){
                            randQuestion = randA.nextInt(questionList.size());
                        }
                        else count++;
                    }
                }
                randomList.add(questionList.get(randQuestion));
            }
        }
        return randomList;

    }
    /*
    End of Question functions
     */
}