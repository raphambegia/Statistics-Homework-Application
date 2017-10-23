package Main;

import java.util.*;

public class Data {
    Map Users = new HashMap();

    public Data (){
        Users.put("admin","pw");
    }

    public void AddUser(String id, String pw){
        Users.put(id,pw);
    }
}
