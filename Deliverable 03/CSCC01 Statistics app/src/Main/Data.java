package Main;

import java.util.*;

public class Data {
    static Map Users = new HashMap();
    static ArrayList <Student> studentList;

    public Data (){
    }

    public static void AddUser (String id, String pw){
        Users.put(id,pw);
    }

    public static void RemoveUser (String id){
        for (Student theStudent : studentList){
            if (id == theStudent.getLoginID()) Users.remove(id);
        }
    }
}
