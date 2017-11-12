package Main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 *  STUDENT VIEWS SPECIFIC ASSIGNMENT FOR REVIEWING OR ATTEMPTING
 */
public class viewAssignmentController {
    @FXML
    private Label assgnTitle;
    @FXML
    private Button assgnBackToList;
    @FXML
    private VBox assgnVbox;
    @FXML
    private Button submitAssignButton;
    @FXML
    private Label viewAssgnLabel;
    @FXML
    private Button assgnToPractice;
    @FXML
    private Label markLabel;

    public Student student;
    private Assignment assignment;
    private ArrayList<Question> questionList = new ArrayList<Question>();
    private ArrayList<ToggleGroup> toggleList = new ArrayList<ToggleGroup>();
    private ArrayList<String> questionOrder = new ArrayList<>();
    private ArrayList<String> ansIndex = new ArrayList<>();


    /*
     Gets assignment from prev scene, loads the questions
     */
    public void initAssignment(Assignment assign, Student stu) {
        student = stu;
        assgnTitle.setText(assign.getAssignmentName());
        assignment = assign;

        if(!assignment.isAvailable()) {
            submitAssignButton.setDisable(true);
            assgnVbox.setDisable(true);
            assgnToPractice.setManaged(true);
            viewAssgnLabel.setText("Submission deadline passed. You may go into practice mode instead.");
        }

        attemptsRemaining();
        loadAssignment();
    }

    public void loadAssignment() {
        // Gets all the questions
        for(Assignment curr : Data.assignmentList) {
            if(curr.getAssignmentName().equals(assgnTitle.getText())) {
                questionList = curr.getRandomQuestionList();
                //questionList = curr.getQuestionList();
            }
        }

        // Displays all the questions
        for(int i = 0; i < questionList.size(); i++) {
            Label questionLabel = new Label();
            questionLabel.setText(i+1 + ". " + questionList.get(i).getTheQuestion());
            ToggleGroup mcAnsToggle = new ToggleGroup();
            toggleList.add(mcAnsToggle);
            assgnVbox.getChildren().add(questionLabel);
            for(int j = 0; j < questionList.get(i).getMcAnswers().size(); j++) {
                RadioButton mcAnsOption = new RadioButton(questionList.get(i).getMcAnswers().get(j));
                mcAnsOption.setUserData(String.valueOf(j));
                mcAnsOption.setToggleGroup(mcAnsToggle);
                assgnVbox.getChildren().add(mcAnsOption);
            }
            Separator sep = new Separator();
            sep.setVisible(false);
            assgnVbox.getChildren().add(sep);
        }
    }

    /*
    Returns TRUE if student still has attempts for this assignment
     */
    public boolean attemptsRemaining() {
        int maxAttempts = 2; // Allow admin to change? Or vary depending on assignment?
        if(student.getCompletedAssignments().get(assignment.getAssignmentName()) != null) {
            int numAttempts = student.getCompletedAssignments().get(assignment.getAssignmentName()).size() / 2;
            if(maxAttempts <= numAttempts) {
                viewAssgnLabel.setText("All attempts used. You may go into practice mode instead.");
                assgnToPractice.setManaged(true);
                submitAssignButton.setDisable(true);
                assgnVbox.setDisable(true);
                return false;
            }
        }
        return true;
    }

    public void submitAssignment() {
        questionOrder.clear();
        ansIndex.clear();
        int numCorrect = 0;

        Question q = null;
        for(int i = 0; i < questionList.size(); i++) {
            q = questionList.get(i);
            questionOrder.add(q.getTheQuestion());
        }

        for(int j = 0; j < q.getMcAnswers().size(); j++) {
            // Get the student's selected answer
            ToggleGroup tg = toggleList.get(j);
            if(tg.getSelectedToggle() != null) {
                Object selectedAns = tg.getSelectedToggle().getUserData();
                ansIndex.add(selectedAns.toString());

                // Checking the answer
                if(selectedAns.toString().equals(q.getSolnIndStr())) {
                    numCorrect++;
                }
            }
        }

        // Only submit if all questions are answered
        if(questionOrder.size() != ansIndex.size()) {
            System.out.println("Questions: " + questionOrder.size() + " vs. Answers: " + ansIndex.size());
            viewAssgnLabel.setStyle("-fx-text-fill: red;");
            viewAssgnLabel.setText("All questions must be answered in order to submit");
            questionOrder.clear();
            ansIndex.clear();
        } else {
            student.addAssgnAttempt(assignment.getAssignmentName(), questionOrder, ansIndex);
            viewAssgnLabel.setStyle("-fx-text-fill: green;");
            viewAssgnLabel.setText("Assignment submitted!");
            markLabel.setManaged(true);
            markLabel.setText("You got: " + numCorrect + " out of " + questionOrder.size() + "!");
        }

        if(!attemptsRemaining()) {
            viewAssgnLabel.setText("Assignment submitted. All attempts used. You may go into practice mode instead.");
        }
    }

    public void toPracticeAssgn() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("practiceAssignment.fxml"));
        Parent root = loader.load();
        Stage stage  = (Stage) assgnBackToList.getScene().getWindow();
        stage.setScene(new Scene(root, 650, 400));
        practiceAssignmentController controller = loader.<practiceAssignmentController>getController();
        controller.initAssignment(assignment);
        stage.show();
    }

    public void aBackToList() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("studentAssignment.fxml"));
        Parent root = loader.load();
        Stage stage  = (Stage) assgnBackToList.getScene().getWindow();
        stage.setScene(new Scene(root, 650, 400));
        stuAssignmentPageController controller = loader.<stuAssignmentPageController>getController();
        controller.passStudent(student);
        stage.show();
    }
}
