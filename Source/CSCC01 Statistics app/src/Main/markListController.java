package Main;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

public class markListController {

    @FXML
    Button toSListButton;

    @FXML
    TableView<Student> marksTable;
    @FXML
    TableColumn<Student,String> fnameCol;
    @FXML
    TableColumn<Student,String> lnameCol;
    @FXML
    TableColumn<Student,Integer> sidCol;

    private ArrayList<TableColumn> assignCols = new ArrayList<>();

    public void initialize() {
        loadMarksTable();
    }

    public void loadMarksTable(){
        ObservableList<Student> students = FXCollections.observableArrayList(Data.studentList);

        fnameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("fName"));
        lnameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("lName"));
        sidCol.setCellValueFactory(new PropertyValueFactory<Student, Integer>("studentId"));

        for(Assignment assgn : Data.getAssignmentList()) {
            TableColumn newCol = new TableColumn(assgn.getAssignmentName());
            newCol.setMinWidth(30);
            newCol.setPrefWidth(85);
            assignCols.add(newCol);
            marksTable.getColumns().add(newCol);

            // Loads the assignment marks into their cells
            newCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Student, String> c) {
                    return new SimpleStringProperty(c.getValue().getAssignmentBestMarkStr(assgn));
                }
            });

            // Allows admin to adjust marks by double clicking
            newCol.setCellFactory(TextFieldTableCell.forTableColumn());
            newCol.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Student, String>>() {
                        @Override
                        public void handle(TableColumn.CellEditEvent<Student, String> t) {
                            (t.getTableView().getItems().get(
                                    t.getTablePosition().getRow())
                            ).overwriteAssgnMarks(assgn.getAssignmentName(), t.getNewValue());
                        }
                    }
            );
        }

        marksTable.setItems(students);
    }

    public void toSListPage() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../AdminGUI/studentList.fxml"));
        Stage stage  = (Stage) toSListButton.getScene().getWindow();
        stage.setScene(new Scene(root, 650, 400));
        stage.show();
    }
}
