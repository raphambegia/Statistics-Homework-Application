package Main;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Student extends User{
    public SimpleStringProperty fName;
    SimpleStringProperty lName;
    SimpleIntegerProperty studentId;
    private HashMap<String, ArrayList<ArrayList<String>>> completedAssignments = new HashMap<>();

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

    public HashMap<String, ArrayList<ArrayList<String>>> getCompletedAssignments() {
        return completedAssignments;
    }

    public void addAssgnAttempt(String assgnName, ArrayList<String> questionOrder, ArrayList<String> ansIndex) {
        ArrayList<ArrayList<String>> qAndA = new ArrayList<>();
        if(completedAssignments.containsKey(assgnName)) {
            qAndA = completedAssignments.get(assgnName);
            qAndA.add(questionOrder);
            qAndA.add(ansIndex);
            completedAssignments.replace(assgnName, qAndA);
        } else {
            qAndA.add(questionOrder);
            qAndA.add(ansIndex);
            completedAssignments.put(assgnName, qAndA);
        }
    }
}
