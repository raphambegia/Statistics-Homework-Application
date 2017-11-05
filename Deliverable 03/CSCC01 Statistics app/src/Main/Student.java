package Main;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student extends User{
    public SimpleStringProperty firstName;
    SimpleStringProperty lName;
    SimpleIntegerProperty studentId;

    public Student(String firstName, String lname, int stid){
        this.firstName = new SimpleStringProperty(firstName);
        this.lName = new SimpleStringProperty(lname);
        this.studentId = new SimpleIntegerProperty(stid);
//        this.setLoginID(fname);
//        this.setLoginPW(lname);
//        this.setType('s');
        Data.AddUser(firstName,lname);
    }

    String getName(){
        return this.firstName+" "+this.lName;
    }

    public String getFirstName(){
        return firstName.get();
    }

    public void setFirstName(String firstname){
        firstName.set(firstname);
    }

    public StringProperty firstNameProperty() {
        return firstName;
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

}
