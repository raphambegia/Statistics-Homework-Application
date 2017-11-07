package Main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class adminAssgnPageController {

    @FXML
    Button backButton;
    @FXML
    GridPane gridPane;
    @FXML
    Button addAssignment;

    public void initialize() {
        adminDisplayAssignment();
    }

    public void adminDisplayAssignment(){
        for(int i=0;i<Data.assignmentList.size();i++){
            Button assgnButton = new Button(Data.assignmentList.get(i).getAssignmentName());
            assgnButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("addQuestion.fxml"));
                    try {
                        Parent root = loader.load();
                        Stage stage = (Stage) backButton.getScene().getWindow();
                        stage.setScene(new Scene(root, 650, 400));
                        questionController controller = loader.<questionController>getController();
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            assgnButton.setMinWidth(180);
            assgnButton.setMinHeight(10);
            gridPane.setHalignment(assgnButton, HPos.CENTER);
            gridPane.add(assgnButton, 0,i);
        }
    }

    public void assignmentAdd(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("createAssignment.fxml"));
        Stage stage  = (Stage) addAssignment.getScene().getWindow();
        stage.setScene(new Scene(root, 650, 400));
        stage.show();
    }

    public void backMain(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("adminPage.fxml"));
        Stage stage  = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(root, 650, 400));
        stage.show();
    }
}
