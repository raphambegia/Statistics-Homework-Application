package Main;

public class Student extends User {
    private String fName;
    private String lName;
    private int studentId;

    public Student (String id, String pw, String fname, String lname, int stid){
        this.fName = fname;
        this.lName = lname;
        this.studentId = stid;
        this.setLoginID(id);
        this.setLoginPW(pw);
        this.setType('s');
        Data.AddUser(id,pw);
    }

    String getName(){
        return this.fName+" "+this.lName;
    }
    int getID(){
        return this.studentId;
    }

}
