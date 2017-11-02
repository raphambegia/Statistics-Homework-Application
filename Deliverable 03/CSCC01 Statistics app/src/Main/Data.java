package Main;

import java.io.*;
import java.util.*;

public class Data {
    static Map Users = new HashMap();
    static ArrayList <Student> studentList;
    public static final String USERSTXT = "users.txt";
    public static final String TEMPTXT = "tempUsers.txt";

    public Data (){
    }

    public static void AddUser (String id, String pw){
        Users.put(id,pw);
        System.out.println(id+" added");
    }

    public void writeNewUser(String id, String pw) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(USERSTXT, true))) {
            bw.write(id + ", " + pw);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void RemoveUser (String id){
        Users.remove(id);
    }

    public void removeFromFile(String id) {
        try {
            File inputFile = new File(USERSTXT);
            File tempFile = new File(TEMPTXT);

            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));

            String currLine;
            while ((currLine = br.readLine()) != null) {
                if (currLine.split(",")[0].equals(id)) continue;
                bw.write(currLine + "\n");
            }

            bw.close();
            br.close();
            tempFile.renameTo(inputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean CheckUser (String id, String pw){
        if ( pw.equals(Users.get(id))) return true;
        return false;
    }
}
