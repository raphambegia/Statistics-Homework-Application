package Main;


public class Question {

    //For info on Question ID, see AssignmentID in Assignment.java
    private int QuestionID;
    private String QuestionName;
    private String QuestionText;

    public Question(String Name, String Text){
        this.QuestionName = Name;
        this.QuestionText = Text;
    }

    public int getQuestionID() { return QuestionID;}
    public String getQuestionName(){
        return QuestionName;
    }
    public String getQuestionText(){
        return QuestionText;
    }

    public void changeQuestionID(int newID){
        QuestionID = newID;
    }

}
