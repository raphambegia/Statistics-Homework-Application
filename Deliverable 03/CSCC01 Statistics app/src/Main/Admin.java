package Main;

import java.util.ArrayList;

public class Admin {
    static ArrayList<Student> studentList;
    public Admin(){

    }
    public void createStudent(String fname, String lname, int stid){
        Student newStudent = new Student(fname, lname, stid);
        studentList.add(newStudent);
    }
}
