package GUI.Controller;

import BE.UserType.Technician;
import BE.UserType.User;
import GUI.Util.ExceptionHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AssignTechniciansController extends BaseController {
    @FXML
    private Button btnExit;
    @FXML
    private TableView<User> tbvTechnician;
    @FXML
    private TableColumn<User, Integer> tbcId;
    @FXML
    private TableColumn<User, String> tbcName;

    private ObservableList<User> allTechnicians;

    @Override
    public void setup() throws IOException {
        showTechnicians();
        multiSelect();
    }

    public AssignTechniciansController() {
        allTechnicians = FXCollections.observableArrayList();
    }

    private void showTechnicians() {
        allTechnicians.addAll(getModelsHandler().getAdminModel().getCurrentProjectTechnician());

        for (User u : getModelsHandler().getAdminModel().getAllUsers()) {
            if (u.getClass().getSimpleName() == Technician.class.getSimpleName())
                if (!allTechnicians.contains(u))
                    allTechnicians.add(u);
                else
                    allTechnicians.remove(u);

        }
        tbvTechnician.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tbvTechnician.setItems(allTechnicians);
        tbcName.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        tbcId.setCellValueFactory(new PropertyValueFactory<User, Integer>("userId"));
    }

    @FXML
    private void handleExit() {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleConfirm(ActionEvent actionEvent) {
        try {
            for (User u : tbvTechnician.getSelectionModel().getSelectedItems()) {

                getModelsHandler().getProjectManagerModel().assignProjectToUser(u, project);
            }
            handleExit();
        } catch (Exception e) {
            ExceptionHandler.displayError(new Exception("Kunne ikke tilføre teknikerne til projektet", e));
        }
    }

    /**
     * Makes the tableview able to multiselect without holding control,
     * by using node instances and mouse events.
     */
    private void multiSelect(){
        tbvTechnician.addEventFilter(MouseEvent.MOUSE_PRESSED, evt -> {
            Node node = evt.getPickResult().getIntersectedNode();

            // Gets the row that was clicked.
            while (node != null && node != tbvTechnician && !(node instanceof TableRow)) {
                node = node.getParent();
            }

            // Don't use standard event, if node is a table row.
            if (node instanceof TableRow) {
                // Prevent further handling
                evt.consume();

                TableRow row = (TableRow) node;
                TableView tv = row.getTableView();

                // Focus the tableview
                tv.requestFocus();

                if (!row.isEmpty()) {
                    // Handle selection for non-empty nodes
                    int index = row.getIndex();

                    if (row.isSelected()) {
                        tv.getSelectionModel().clearSelection(index);
                    } else {
                        tv.getSelectionModel().select(index);
                    }
                }
            }
        });
    }
}
