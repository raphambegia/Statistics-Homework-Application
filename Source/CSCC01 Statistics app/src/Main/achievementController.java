package Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.image.*;
import javafx.scene.image.ImageView;
import java.io.IOException;

public class achievementController {
    @FXML
    private Button backButton;
    @FXML
    private ImageView firststepBadge;
    @FXML
    private Label firststepLabel;
    @FXML
    private ImageView starBadge;
    @FXML
    private Label starLabel;
    @FXML
    private ImageView hardworkBadge;
    @FXML
    private Label hardworkLabel;
    private Student currStudent;

    /** Back button to Student Page **/
    public void goStudentPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../StudentGUI/studentPage.fxml"));
        Parent root = loader.load();
        Stage stage  = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(root, 650, 400));
        studentPageController controller = loader.<studentPageController>getController();
        controller.passStudent(currStudent);
        stage.show();
    }

    public void checkFirststepBadge (){
        if(!currStudent.getCompletedAssignNames().isEmpty()){
            firststepBadge.setVisible(true);
        }

    }

    public void checkStarBadge(){
        double avgscore = 0;
        int count = 0;
        for(String name : currStudent.getCompletedAssignNames()){
            avgscore += currStudent.getBestMarkFor(name);
            count++;
        }
        avgscore = avgscore/count;
        if(avgscore>=80){
            starBadge.setVisible(true);
        }
    }

    public void checkHardworkBadge(){
        int count=0;
        for(String name : currStudent.getCompletedAssignNames()){
            count += currStudent.getNumAttempts(name);
        }
        if(count >=5) hardworkBadge.setVisible(true);
    }


    public void initStudent (Student thesStudent){
        currStudent = thesStudent;
    }

}
