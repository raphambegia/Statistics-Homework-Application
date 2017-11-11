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

    public Student student;
    private Assignment assignment;
    private ArrayList<Question> questionList = new ArrayList<Question>();
    private ArrayList<ToggleGroup> toggleList = new ArrayList<ToggleGroup>();
    private ArrayList<String> questionOrder = new ArrayList<>();
    private ArrayList<String> ansIndex = new ArrayList<>();


    /**
     * Gets assignment from prev scene, loads the questions
     * @param assign
     */
    public void initAssignment(Assignment assign, Student stu) {
        student = stu;
        assgnTitle.setText(assign.getAssignmentName());
        assignment = assign;

        if(!assignment.isAvailable()) {
            submitAssignButton.setDisable(true);
        }

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

    public void aBackToList() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("studentAssignment.fxml"));
        Parent root = loader.load();
        Stage stage  = (Stage) assgnBackToList.getScene().getWindow();
        stage.setScene(new Scene(root, 650, 400));
        stuAssignmentPageController controller = loader.<stuAssignmentPageController>getController();
        controller.passStudent(student);
        stage.show();
    }

    public void submitAssignment() {
        Question q = null;
        for(int i = 0; i < questionList.size(); i++) {
            q = questionList.get(i);
            questionOrder.add(q.getTheQuestion());
        }

        for(int j = 0; j < q.getMcAnswers().size(); j++) {
            // Get the student's selected answer
            ToggleGroup tg = toggleList.get(j);
            Object selectedAns = tg.getSelectedToggle().getUserData();
            ansIndex.add(selectedAns.toString());

            // CAN COMPARE TO ACTUAL ANSWER HERE
        }

        if(questionOrder.size() != ansIndex.size()) {
            System.out.println("q size: " + questionOrder.size() + " vs a order: " + ansIndex.size());
            System.out.println("why didn't you just answer all the questions??? why!!!!!");
        }

        student.addAssgnAttempt(assignment.getAssignmentName(), questionOrder, ansIndex);

        ////////
        // Check if line above really works
        // think about how to calc marks
        // think about how to display this assign with selected answers again
        // use index to select toggle??
    }
}
