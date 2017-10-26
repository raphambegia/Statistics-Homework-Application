package Main;

public class User {
    private String LoginID;
    private String LoginPW;
    private char Type;

    public User (){

    }

    public User (String id, String pw, char type){
        LoginID = id;
        LoginPW = pw;
        Type = type;
    }

    public String getLoginID(){
        return LoginID;
    }

    public String getLoginPW(){
        return LoginPW;
    }

    public char getType(){
        return Type;
    }

}
