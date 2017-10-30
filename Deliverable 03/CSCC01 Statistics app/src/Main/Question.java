package Main;

import java.util.ArrayList;

public class Question {
    private String theQuestion;
    private ArrayList<String> mcAnswers;
    private int solnInd;

    public Question(String theQuestion, ArrayList<String> mcAnswers, int solnInd) {
        this.theQuestion = theQuestion;
        this.mcAnswers = mcAnswers;
        this.solnInd = solnInd;
    }

    public String getTheQuestion() {
        return theQuestion;
    }

    public ArrayList<String> getMcAnswers() {
        return mcAnswers;
    }

    public int getSolnInd() {
        return solnInd;
    }

}
