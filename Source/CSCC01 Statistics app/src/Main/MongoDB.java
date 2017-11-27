package Main;

import com.mongodb.*;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import com.mongodb.DB;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.IndexOptions;

import com.mongodb.client.model.Indexes;
import org.bson.BSON;
import org.bson.Document;

import java.util.*;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;

public class MongoDB {
    public static MongoClient mongoClient;
    public static int key = 0;
    public MongoDB() {
        MongoClientURI connection = new MongoClientURI("mongodb://raphael:raphael24@ds157475.mlab.com:57475/cscc01");
        this.mongoClient = new MongoClient( connection );
    }
    public void connect(){
        MongoDatabase newDatabase = this.mongoClient.getDatabase("cscc01");
        MongoCollection userCollection = newDatabase.getCollection("Users");
        IndexOptions indexOptions = new IndexOptions().unique(true);
        userCollection.createIndex(Indexes.ascending("stID"), indexOptions);
        MongoCollection assgnCollection = newDatabase.getCollection("AssignDueDate");
        assgnCollection.createIndex(Indexes.ascending("assignName"), indexOptions);
        MongoCollection assgnNoDueCollection = newDatabase.getCollection("AssignNoDueDate");
        assgnNoDueCollection.createIndex(Indexes.ascending("assignName"), indexOptions);
        MongoCollection questions = newDatabase.getCollection("Questions");
        //questions.deleteMany(new Document());
        questions.createIndex(Indexes.ascending("question"), indexOptions);


    }
    public static void addToStudents(String fName, String lName, int stID){
        MongoDatabase userDatabase = mongoClient.getDatabase("cscc01");
        MongoCollection userCollection = userDatabase.getCollection("Users");
        Document student = new Document("fName", fName).append("lName", lName).append("stID", stID);
        try {
            userCollection.insertOne(student);
        }catch (MongoWriteException e){

        }
    }
    public static List<Document> getStudents(){
        MongoDatabase userDatabase = mongoClient.getDatabase("cscc01");
        MongoCollection userCollection = userDatabase.getCollection("Users");
        List<Document> users = (List<Document>) userCollection.find().into(new ArrayList<Document>());
        return users;
    }

    public static void removeStudent(int stID){
        MongoDatabase userDatabase = mongoClient.getDatabase("cscc01");
        MongoCollection userCollection = userDatabase.getCollection("Users");
        userCollection.deleteOne(new Document("stID",  stID));
    }

    public static void printStudent(){
        MongoDatabase userDatabase = mongoClient.getDatabase("cscc01");
        MongoCollection userCollection = userDatabase.getCollection("Users");
        List<Document> users = (List<Document>) userCollection.find().into(new ArrayList<Document>());
        for(Document document: users){
            System.out.println(document.get("fName"));
        }

    }
    public static void addToAssignmentWDueDate(String assignmentName, String dueDate){
        MongoDatabase userDatabase = mongoClient.getDatabase("cscc01");
        MongoCollection userCollection = userDatabase.getCollection("AssignDueDate");
        Document student = new Document("assignName", assignmentName).append("dueDate", dueDate);
        try {
            userCollection.insertOne(student);
        }catch (MongoWriteException e){
        }
    }
    public static List<Document> getAssignmentsWDueDate(){
        MongoDatabase userDatabase = mongoClient.getDatabase("cscc01");
        MongoCollection userCollection = userDatabase.getCollection("AssignDueDate");
        List<Document> assgn = (List<Document>) userCollection.find().into(new ArrayList<Document>());
        return assgn;
    }

    public static List<Document> getAssignmentsNoDueDate(){
        MongoDatabase userDatabase = mongoClient.getDatabase("cscc01");
        MongoCollection userCollection = userDatabase.getCollection("AssignNoDueDate");
        List<Document> assgn = (List<Document>) userCollection.find().into(new ArrayList<Document>());
        return assgn;
    }

    public static List<Document> getQuestions(){
        MongoDatabase userDatabase = mongoClient.getDatabase("cscc01");
        MongoCollection userCollection = userDatabase.getCollection("Questions");
        List<Document> questions = (List<Document>) userCollection.find().into(new ArrayList<Document>());
        return questions;
    }

    public static void addToAssignmentNoDueDate(String assignmentName){
        MongoDatabase userDatabase = mongoClient.getDatabase("cscc01");
        MongoCollection userCollection = userDatabase.getCollection("AssignNoDueDate");
        Document assignment = new Document("assignName", assignmentName);
        try {
            userCollection.insertOne(assignment);
        }catch (MongoWriteException e){
        }
    }
    public static void addToQuestions(String assignmentName, String qName, String mc1, String mc2, String mc3, String mc4, String mc5, int soln){
        MongoDatabase userDatabase = mongoClient.getDatabase("cscc01");
        MongoCollection userCollection = userDatabase.getCollection("Questions");
        Document question = new Document("assignName", assignmentName).append("question", qName).append("mc1", mc1).append("mc2", mc2).append("mc3", mc3).append("mc4", mc4).append("mc5", mc5).append("soln", soln);
        try {
            userCollection.insertOne(question);
        }catch (MongoWriteException e){
        }
    }
    public static void addToScores(String assignmentName, int stID, double score){
        MongoDatabase userDatabase = mongoClient.getDatabase("cscc01");
        MongoCollection userCollection = userDatabase.getCollection(assignmentName);
        IndexOptions indexOptions = new IndexOptions().unique(true);
        userCollection.createIndex(Indexes.ascending("stID"), indexOptions);
        Document studentScore = new Document("stID", stID).append("score", score);
        try {
            userCollection.insertOne(studentScore);
        }catch (MongoWriteException e){
            userCollection.updateOne(new Document("stID",  stID), new Document("score", score));
        }
    }
    public static void changeToScores(String assignmentName, int stID, double score){
        MongoDatabase userDatabase = mongoClient.getDatabase("cscc01");
        MongoCollection userCollection = userDatabase.getCollection(assignmentName);
        userCollection.updateOne(new Document("stID",  stID), set("score", score));

    }

    public static void addToAttempts(String assignmentName, int stID, int attempts){
        MongoDatabase userDatabase = mongoClient.getDatabase("cscc01");
        MongoCollection userCollection = userDatabase.getCollection(assignmentName);
        IndexOptions indexOptions = new IndexOptions().unique(true);
        userCollection.createIndex(Indexes.ascending("stID"), indexOptions);
        Document studentAttempts = new Document("stID", stID).append("attempts", attempts);
        try {
            userCollection.insertOne(studentAttempts);
        }catch (MongoWriteException e){
            userCollection.updateOne(new Document("stID",  stID), set("attempts", attempts));
        }
    }

    public static void changeToAttempts(String assignmentName, int stID, int attempts){
        MongoDatabase userDatabase = mongoClient.getDatabase("cscc01");
        MongoCollection userCollection = userDatabase.getCollection(assignmentName);
        userCollection.updateOne(new Document("stID",  stID), new Document("attempts", attempts));

    }

    public static List<Document> getScores(String assignmentName){
        MongoDatabase userDatabase = mongoClient.getDatabase("cscc01");
        MongoCollection userCollection = userDatabase.getCollection(assignmentName);
        List<Document> scores = (List<Document>) userCollection.find().into(new ArrayList<Document>());
        return scores;
    }

    public static void removeAssignment(String assignName){
        MongoDatabase userDatabase = mongoClient.getDatabase("cscc01");
        MongoCollection userCollection = userDatabase.getCollection("AssignNoDueDate");

        userCollection.deleteOne(new Document("assignName",  assignName));
        MongoCollection userCollection2 = userDatabase.getCollection("AssignDueDate");
        userCollection2.deleteOne(new Document("assignName",  assignName));
    }

    public static void update(){
        boolean req = false;
        List<Document> students = getStudents();
        Student newStudent = null;
        for(Document student:students){
            newStudent = new Student((String) student.get("fName"),(String) student.get("lName"),(int) student.get("stID"));
            for(Student ref: Data.studentList){
                if(((int) student.get("stID"))!= (ref.getStudentId())){
//                    Student newStudent = new Student((String) student.get("fName"),(String) student.get("lName"),(int) student.get("stID"));
//                    Data.studentList.add(newStudent);
                    req = true;
                }
            }
        }
        if(req) {
            Data.studentList.add(newStudent);
        }
    }

}
