package Main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class practiceAssignmentController {

    @FXML
    private Label pAssgnTitle;
    @FXML
    private Label pMarkLabel;
    @FXML
    private VBox pAssgnVbox;
    @FXML
    private Label viewPAssgnLabel;
    @FXML
    private Button checkAnsButton;
    @FXML
    private Button pAssgnToPList;
    @FXML
    private Button newPracticeButton;
    @FXML
    private Button pAssgnToAssgn;
    @FXML
    private ScrollPane pScrollPane;

    private Assignment assignment;
    private Student student;
    private ArrayList<Question> questionList = new ArrayList<Question>();
    private ArrayList<ToggleGroup> toggleList = new ArrayList<>();
    private ArrayList<String> questionOrder = new ArrayList<>();
    private ArrayList<String> ansIndex = new ArrayList<>();
    private ArrayList<Label> solnList = new ArrayList<>();

    public void initAssignment(Assignment assgn, Student stu) {
        student = stu;
        assignment = assgn;
        pAssgnTitle.setText(assignment.getAssignmentName() + " Practice");

        loadQuestions();
    }

    public void loadQuestions() {
        pAssgnVbox.getChildren().clear();
        toggleList.clear();
        solnList.clear();

        // Gets the questions
        questionList = assignment.getRandomQuestionList();

        // Displays all the questions
        for(int i = 0; i < questionList.size(); i++) {
            Label questionLabel = new Label();
            questionLabel.setText(i+1 + ". " + questionList.get(i).getTheQuestion());
            ToggleGroup mcAnsToggle = new ToggleGroup();
            toggleList.add(mcAnsToggle);
            pAssgnVbox.getChildren().add(questionLabel);
            for(int j = 0; j < questionList.get(i).getMcAnswers().size(); j++) {
                RadioButton mcAnsOption = new RadioButton(questionList.get(i).getMcAnswers().get(j));
                mcAnsOption.setUserData(String.valueOf(j));
                mcAnsOption.setToggleGroup(mcAnsToggle);
                pAssgnVbox.getChildren().add(mcAnsOption);
            }
            Separator sep = new Separator();
            sep.setVisible(false);
            pAssgnVbox.getChildren().add(sep);

            Label solnLabel = new Label();
            solnLabel.setManaged(false);
            solnList.add(solnLabel);
            pAssgnVbox.getChildren().add(solnLabel);

            Separator sep1 = new Separator();
            sep1.setVisible(false);
            pAssgnVbox.getChildren().add(sep1);
        }
    }

    public void checkAnswers() {
        questionOrder.clear();
        ansIndex.clear();
        int numCorrect = 0;

        for(int i = 0; i < questionList.size(); i++) {
            Label lb = solnList.get(i);
            lb.setManaged(true);

            Question q = questionList.get(i);
            questionOrder.add(q.getTheQuestion());

            ToggleGroup tg = toggleList.get(i);
            if(tg.getSelectedToggle() != null) {
                String selectedAns = tg.getSelectedToggle().getUserData().toString();
                ansIndex.add(selectedAns);

                // Checking the answer
                if(selectedAns.equals(q.getSolnIndStr())) {
                    numCorrect++;
                    lb.setStyle("-fx-text-fill: green;");
                    lb.setText("Correct!");
                } else {
                    lb.setStyle("-fx-text-fill: red;");
                    lb.setText("Incorrect. Correct answer is: " + q.getMcAnswers().get(q.getSolnInd()));
                }
            } else {
                lb.setStyle("-fx-text-fill: red;");
                lb.setText("Incorrect. Correct answer is: " + q.getMcAnswers().get(q.getSolnInd()));
            }
        }

        // Calc and show mark
        pMarkLabel.setManaged(true);
        Double mark = (double) numCorrect / questionOrder.size() * 100;
        pMarkLabel.setText("You got: " + numCorrect + " out of " + questionOrder.size() + ", " + mark + "%!");

        toggleList.clear();
        checkAnsButton.setManaged(false);
        checkAnsButton.setVisible(false);
        newPracticeButton.setManaged(true);
        newPracticeButton.setVisible(true);
    }

    public void newPracticeSet() {
        viewPAssgnLabel.setText("");
        pMarkLabel.setText("");
        pMarkLabel.setManaged(false);
        checkAnsButton.setManaged(true);
        checkAnsButton.setVisible(true);
        newPracticeButton.setManaged(false);
        newPracticeButton.setVisible(false);

        loadQuestions();
    }

    public void backToAssign() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("viewAssignmentPage.fxml"));
        Parent root = loader.load();
        Stage stage  = (Stage) pAssgnToAssgn.getScene().getWindow();
        stage.setScene(new Scene(root, 650, 400));
        viewAssignmentController controller = loader.<viewAssignmentController>getController();
        controller.initAssignment(assignment, student);
        stage.show();
    }

    public void backToPList() {

    }

    /*
     If we could disable clicking that would be great
     */
    public void hovering() {
        if(newPracticeButton.isVisible()) {
            viewPAssgnLabel.setText("Start a new practice set to try again!");
        }
    }
}
