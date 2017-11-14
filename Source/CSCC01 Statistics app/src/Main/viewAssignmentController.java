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
    @FXML
    private Label attemptsTitle;

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
            assgnVbox.getChildren().clear();
            markLabel.setManaged(true);
            markLabel.setText("Mark submitted: " + student.getBestMarkFor(assignment.getAssignmentName()));
            viewAssgnLabel.setText("Submission deadline passed. You may go into practice mode instead.");
        }

        loadAssignment();
        if(!attemptsRemaining()) {
            markLabel.setManaged(true);
            markLabel.setText("Mark submitted: " + student.getBestMarkFor(assignment.getAssignmentName()) + "%");
        }
    }

    public void loadAssignment() {
        questionList = assignment.getRandomQuestionList();

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
            int numAttempts = student.getAssignmentMarks(assignment).size();
            attemptsTitle.setText("You have used " + numAttempts + " out of " + maxAttempts + " marked attempts");
            if(maxAttempts <= numAttempts) {
                viewAssgnLabel.setText("All attempts used. You may go into practice mode instead.");
                assgnToPractice.setManaged(true);
                submitAssignButton.setDisable(true);
                assgnVbox.setDisable(true);
                assgnVbox.getChildren().clear();
                return false;
            }
        } else {
            attemptsTitle.setText("You have used 0 out of " + maxAttempts + " marked attempts");
        }
        return true;
    }

    public void submitAssignment() {
        questionOrder.clear();
        ansIndex.clear();
        int numCorrect = 0;

        for(int i = 0; i < questionList.size(); i++) {
            Question q = questionList.get(i);
            questionOrder.add(q.getTheQuestion());

            ToggleGroup tg = toggleList.get(i);
            if(tg.getSelectedToggle() != null) {
                String selectedAns = tg.getSelectedToggle().getUserData().toString();
                ansIndex.add(selectedAns);

                // Checking the answer
                if(selectedAns.equals(q.getSolnIndStr())) {
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
            // Save the assignment
            student.addAssgnAttempt(assignment.getAssignmentName(), questionOrder, ansIndex);
            viewAssgnLabel.setStyle("-fx-text-fill: green;");
            viewAssgnLabel.setText("Assignment submitted!");

            // Calc and show mark
            markLabel.setManaged(true);
            Double mark = (double) numCorrect / questionOrder.size() * 100;
            student.setAssignmentMarks(assignment.getAssignmentName(), mark);
            markLabel.setText("You got: " + numCorrect + " out of " + questionOrder.size() + ", " + mark + "%!");

            // Offer new assignment
            assgnVbox.getChildren().clear();
            toggleList.clear();
            loadAssignment();
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
        controller.initAssignment(assignment, student);
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
