package Main;

public class Student extends User{
    String fName;
    String lName;
    int studentId;

    public Student(String fname, String lname, int stid){
        this.fName = fname;
        this.lName = lname;
        this.studentId = stid;
        this.setLoginID(fname);
        this.setLoginPW(lname);
        this.setType('s');
        Data.AddUser(fname,lname);
    }

    String getName(){
        return this.fName+" "+this.lName;
    }

    int getID(){
        return this.studentId;
    }

}
