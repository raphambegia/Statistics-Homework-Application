package Main;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Student extends User{
    public SimpleStringProperty fName;
    SimpleStringProperty lName;
    SimpleIntegerProperty studentId;
//    // Mapping explained below
//    private HashMap<String, ArrayList<ArrayList<String>>> completedAssignments = new HashMap<>();
//    private HashMap<String, ArrayList<Double>> assignmentMarks = new HashMap<>();

    private HashMap<String, Double> assignmentBestMark = new HashMap<>();
    private HashMap<String, Integer> assignmentNumAttempts = new HashMap<>();

    public Student(String fname, String lname, int stid){
        this.fName = new SimpleStringProperty(fname);
        this.lName = new SimpleStringProperty(lname);
        this.studentId = new SimpleIntegerProperty(stid);
        this.setLoginID(fname);
        this.setLoginPW(lname);
        this.setType('s');
        Data.AddUser(this);
    }

    String getName(){
        return this.fName+" "+this.lName;
    }

    public String getFName(){
        return fName.get();
    }

    public void setFName(String firstname){
        fName.set(firstname);
        Data.RemoveUser(this.studentId.getValue());
        Data.addStudent(fName.get(), lName.get(), studentId.getValue());
    }

    public void setLName(String lastname){
        lName.set(lastname);
        Data.RemoveUser(this.studentId.getValue());
        Data.addStudent(fName.get(), lName.get(), studentId.getValue());
    }

    public StringProperty fNameProperty() {
        return fName;
    }

    public StringProperty lNameProperty() {
        return lName;
    }

    String getLName (){
        return lName.get();
    }

    public IntegerProperty studentIdProperty() {
        return studentId;
    }

    int getStudentId(){
        return studentId.getValue();
    }

    public Double getAssignmentBestMark(Assignment assign) {
        return assignmentBestMark.get(assign.getAssignmentName());
    }

    public String getAssignmentBestMarkStr(Assignment assign) {
        String mark = "";
        if (assignmentBestMark.containsKey(assign.getAssignmentName())) {
            mark = String.format("%.2f", assignmentBestMark.get(assign.getAssignmentName()));
        } else {
            mark = "--";
        }
        return mark;
    }

    public void addAttempt(String assignName) {
        if(!assignmentNumAttempts.containsKey(assignName)) {
            assignmentNumAttempts.put(assignName, 1);
            MongoDB.addToAttempts(assignName, this.getStudentId(), 1);
        } else {
            int attempts = assignmentNumAttempts.get(assignName);
            attempts++;
            assignmentNumAttempts.replace(assignName, attempts);
            MongoDB.addToAttempts(assignName, this.getStudentId(), attempts);
        }
    }

    public int getNumAttempts(Assignment assign) {
        return assignmentNumAttempts.get(assign.getAssignmentName());
    }

    public Double getBestMarkFor(String assgnName) {
        Double bestMark = 0.0;
        if(assignmentBestMark.containsKey(assgnName)) {
            bestMark = assignmentBestMark.get(assgnName);
        }
        return bestMark;
    }

    public String getStrBestMarkFor(String assgnName) {
        Double bestMark = 0.0;
        if(assignmentBestMark.containsKey(assgnName)) {
            bestMark = assignmentBestMark.get(assgnName);
        }
        return String.format("%.1f", bestMark);
    }

    public void overwriteAssgnMarks(String assgnName, String newMark) {
        Double mark = Double.valueOf(newMark);
        if(assignmentBestMark.containsKey(assgnName)) {
            assignmentBestMark.replace(assgnName, mark);
            MongoDB.addToScores(assgnName, this.getStudentId(), mark);
        } else {
            assignmentBestMark.put(assgnName, mark);
            MongoDB.addToScores(assgnName, this.getStudentId(), mark);
        }

    }

    public void setAssignmentMark(String assgnName, Double mark) {
        addAttempt(assgnName);
        if(assignmentBestMark.containsKey(assgnName)) {
            if(mark > assignmentBestMark.get(assgnName)) {
                assignmentBestMark.replace(assgnName, mark);
                MongoDB.addToScores(assgnName, this.getStudentId(), mark);
            }
            // otherwise if the new mark is <= to best mark, no change
        } else {
            assignmentBestMark.put(assgnName, mark);
            MongoDB.addToScores(assgnName, this.getStudentId(), mark);
        }
    }

    public ArrayList<String> getCompletedAssignNames() {
        ArrayList<String> completedAssignments = new ArrayList<>(assignmentBestMark.keySet());
        return completedAssignments;
    }

    public int getNumAttempts(String assign) {
        return assignmentNumAttempts.get(assign);
    }
//    /*
//    MAP KEY: assignment name
//    MAPPED TO: an arraylist containing 2 string arraylists
//        First list: the questions listed in order (just the actual question string ie. "What is SS16?")
//        Second list: the indexes of the selected answers (they're all strings though)
//        Looks like something like:  "A1" --> [  ["Q1", "Q2", "Q3"], ["1", "0", "3"]  ]
//     */
//    public HashMap<String, ArrayList<ArrayList<String>>> getCompletedAssignments() {
//        return completedAssignments;
//    }
//
//    public void addAssgnAttempt(String assgnName, ArrayList<String> questionOrder, ArrayList<String> ansIndex) {
//        ArrayList<ArrayList<String>> qAndA = new ArrayList<>();
//        if(completedAssignments.containsKey(assgnName)) {
//            qAndA = completedAssignments.get(assgnName);
//            qAndA.add(questionOrder);
//            qAndA.add(ansIndex);
//            completedAssignments.replace(assgnName, qAndA);
//        } else {
//            qAndA.add(questionOrder);
//            qAndA.add(ansIndex);
//            completedAssignments.put(assgnName, qAndA);
//        }
//    }
}
