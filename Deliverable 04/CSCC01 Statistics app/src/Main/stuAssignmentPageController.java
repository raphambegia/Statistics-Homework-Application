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

public class stuAssignmentPageController {

    @FXML
    Button secretButton;
    @FXML
    GridPane studentGrid;
    @FXML
    Button assignmentBack;

    public void initialize() {
        studentDisplayAssignment();
    }

    public void studentDisplayAssignment(){
        System.out.println(Data.assignmentList.get(0).getAssignmentName());
        for(int i=0;i<Data.assignmentList.size();i++){
            Button assgnButton = new Button(Data.assignmentList.get(i).getAssignmentName());
            assgnButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("viewAssignmentPage.fxml"));
                    try {
                        Parent root = loader.load();
                        Stage stage = (Stage) secretButton.getScene().getWindow();
                        stage.setScene(new Scene(root, 650, 400));
                        viewAssignmentController controller = loader.<viewAssignmentController>getController();
                        controller.assgnName(assgnButton.getText());
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            assgnButton.setMinWidth(90);
            assgnButton.setMinHeight(10);
            studentGrid.setHalignment(assgnButton, HPos.CENTER);
            studentGrid.add(assgnButton, 0,i);
        }
    }

    public void studentBacktoMain(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("studentPage.fxml"));
        Stage stage = (Stage) assignmentBack.getScene().getWindow();
        stage.setScene(new Scene(root, 650, 400));
        stage.show();
    }

    /**
     * keep this
     * @param event
     * @throws IOException
     */
    public void assgnSelected(ActionEvent event) throws IOException {

    }
}
