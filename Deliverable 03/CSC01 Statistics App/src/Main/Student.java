package Main;

public class Student {
    String fName;
    String lName;
    int studentId;

    public Student(String fname, String lname, int stid){
        this.fName = fname;
        this.lName = lname;
        this.studentId = stid;
    }

    String getName(){
        return this.fName+" "+this.lName;
    }
    int getID(){
        return this.studentId;
    }
}
