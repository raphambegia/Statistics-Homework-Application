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
    private HashMap<String, ArrayList<Double>> assignmentMarks = new HashMap<>();

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

    public ArrayList<Double> getAssignmentMarks(Assignment assign) {
        if (!assignmentMarks.containsKey(assign.getAssignmentName())) {
            return null;
        }
        return assignmentMarks.get(assign.getAssignmentName());
    }

    public String getStrAssignmentMarks(Assignment assign) {
        String marks = "";
        if (assignmentMarks.containsKey(assign.getAssignmentName())) {
            for(Double mark : assignmentMarks.get(assign.getAssignmentName())) {
                if(marks == "") {
                    marks = String.format("%.1f", mark);
                } else {
                    marks = marks + "," + String.format("%.1f", mark);
                }
            }
        }
        if(marks == "") {
            marks = "--";
        }
        return marks;
    }

    public Double getBestMarkFor(String assgnName) {
        Double bestMark = 0.0;
        if(assignmentMarks.containsKey(assgnName)) {
            for(Double mark : assignmentMarks.get(assgnName)) {
                if(mark > bestMark) {
                    bestMark = mark;
                }
            }
        }
        return bestMark;
    }

    public String getStrBestMarkFor(String assgnName) {
        Double bestMark = 0.0;
        if(assignmentMarks.containsKey(assgnName)) {
            for(Double mark : assignmentMarks.get(assgnName)) {
                if(mark > bestMark) {
                    bestMark = mark;
                }
            }
        }
        return bestMark.toString();
    }

    public void overwriteAssgnMarks(String assgnName, String marks) {
        ArrayList<String> mrks = new ArrayList<>(Arrays.asList(marks.split(",")));
        ArrayList<Double> markList = new ArrayList<>();
        for(String mark : mrks) {
            markList.add(Double.valueOf(mark));
        }
        if(assignmentMarks.containsKey(assgnName)) {
            assignmentMarks.replace(assgnName, markList);
        } else {
            assignmentMarks.put(assgnName, markList);
        }

    }

    public void setAssignmentMarks(String assgnName, Double mark) {
        ArrayList<Double> markList = new ArrayList<>();
        if(assignmentMarks.containsKey(assgnName)) {
            markList = assignmentMarks.get(assgnName);
            markList.add(mark);
            assignmentMarks.replace(assgnName, markList);
        } else {
            markList.add(mark);
            assignmentMarks.put(assgnName, markList);
        }
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
