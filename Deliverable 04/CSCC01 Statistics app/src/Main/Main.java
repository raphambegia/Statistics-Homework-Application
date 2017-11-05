package Main;


import java.util.ArrayList;

public class Main {





    public static void main(String[] args) {
        Assignment first = new Assignment("first");
        Assignment sec = new Assignment( "sec");
        Assignment third = new Assignment("third");
        Assignment fourth = new Assignment( "fourth");

        Question Q1 = new Question("Q1", "1+1?");
        Question segundo = new Question ("Q2", "what's your name?");
        Question tetra = new Question("Q3", "3+3?");
        Question Q4 = new Question("Q4", "4+1?");

        Data.AddAssignment(first);
        Data.AddAssignment(sec);
        Data.AddAssignment(third);
        Data.AddAssignment(fourth);


        first.AddQuestion(tetra);

        first.AddQuestion(Q4);
        first.AddQuestion(Q1);
        first.AddQuestion(segundo);

        //PROBLEM FOUND: Adding the same question in a different assignment will mess everything up. YOU CANT ADD THE SAME QUESTION IN 2 ASSIGNMENTS.
        sec.AddQuestion(segundo);

        ArrayList<Assignment> AssignmentList;
        AssignmentList = Data.getAssignmentList();


        for (Assignment AssignmentElement : AssignmentList ){
                ArrayList<Question> QuestionList;
                QuestionList = AssignmentElement.getQuestionList();

                for (Question QuestionElement : QuestionList ){
                    System.out.println("Assignment ID: " + AssignmentElement.getAssignmentID() + "    Assignment Name: " + AssignmentElement.getAssignmentName()
                            + "        Question: " + QuestionElement.getQuestionText() + "     QID: " + QuestionElement.getQuestionID() );
                }

    }

        Data.removeAssignment(sec);
        Data.removeAssignment(third);

        first.removeQuestion(segundo);


        for (Assignment AssignmentElement : AssignmentList ){
            ArrayList<Question> QuestionList;
            QuestionList = AssignmentElement.getQuestionList();

            for (Question QuestionElement : QuestionList ){
                System.out.println("B++++++++++++       Assignment ID: " + AssignmentElement.getAssignmentID() + "    Assignment Name: " + AssignmentElement.getAssignmentName()
                        + "     Question: " + QuestionElement.getQuestionText() + "     QID: " + QuestionElement.getQuestionID() );
            }

        }

    }
}
