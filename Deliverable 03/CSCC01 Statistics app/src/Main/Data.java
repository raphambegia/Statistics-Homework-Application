package Main;

import java.util.*;

public class Data {
    static Map Users = new HashMap();
    static ArrayList <Student> studentList;

    public Data (){
    }

    public static void AddUser (String id, String pw){
        Users.put(id,pw);
        System.out.println(id+" added");
    }

    public static void RemoveUser (String id){
        Users.remove(id);
    }

    public static boolean CheckUser (String id, String pw){
        if ( pw.equals(Users.get(id))) return true;
        return false;
    }

    public static void RemoveUser (String id){
        for (Student theStudent : studentList){
            if (id == theStudent.getLoginID()) Users.remove(id);
        }
    }
}
