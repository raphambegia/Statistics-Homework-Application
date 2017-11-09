package Main;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.ToggleGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Student extends User{
    public SimpleStringProperty fName;
    SimpleStringProperty lName;
    SimpleIntegerProperty studentId;
    public ArrayList<Assignment> completedAssgns = new ArrayList<Assignment>();
    public ArrayList<Integer> selectedAns = new ArrayList<>();

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

    public ArrayList<Assignment> getCompletedAssgns() {
        return completedAssgns;
    }

    public ArrayList<Integer> getSelectedAns() {
        return selectedAns;
    }

    public void addCompletedAssgn(Assignment assgn, Integer ansIndex) {
        completedAssgns.add(assgn);
        selectedAns.add(ansIndex);
    }
}
