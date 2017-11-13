package Main;


import com.mongodb.ReflectionDBObject;

public class User{
    private String loginID;
    private String loginPW;
    private char type;

    //default constructor
    public User (){
    }

    public User (String id, String pw, char mytype){
        loginID = id;
        loginPW = pw;
        type = mytype;
    }

    public String getLoginID(){
        return loginID;
    }

    public String getLoginPW(){
        return loginPW;
    }

    public char getType(){
        return type;
    }

    public void setLoginID(String id){
        loginID = id;
    }

    public void setLoginPW (String pw){
        loginPW = pw;
    }

    public void setType (char t){
        type = t;
    }

}