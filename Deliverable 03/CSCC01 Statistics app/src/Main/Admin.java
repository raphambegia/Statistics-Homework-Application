package Main;

import java.util.ArrayList;

public class Admin extends User {

    public Admin(String id, String pw){
        this.setLoginID(id);
        this.setLoginPW(pw);
        this.setType('a');
        Data.AddUser(id,pw);
    }

    public void createStudent(String id, String pw, String fname, String lname, int stid){
        Student newStudent = new Student(Integer.toString(stid), lname, fname, lname, stid);
        Data.studentList.add(newStudent);
    }

    public void removeStudent(int stid){
        for (Student thestudent : Data.studentList) {
            if(stid == thestudent.getID()) {
                Data.RemoveUser(thestudent.getLoginID());
            }
        }
    }

}
