package GUI.Controller;

import BE.Project;
import BE.UserType.User;
import GUI.Controller.BaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.io.IOException;

public class ProjectController extends BaseController {
    @FXML
    private ListView<User> lvAssignTechnician;
    @FXML
    private Label lblProjectName;
    @FXML
    private TextArea txaProjectDescription;
    @FXML
    private Label lblProjectDate, lblCustomerName, lblProjectStatus;


    @Override
    public void setup() throws IOException {

        try {
            setProjectInfo();
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Kunne ikke loade projektets information", e);
        }
    }

    private void setProjectInfo() throws Exception {
        getModelsHandler().getAdminModel().getCurrentProjectTechnician().clear();
        getModelsHandler().getAdminModel().getCurrentProjectTechnician().addAll(getModelsHandler().getAdminModel().getUsersWorkingOnProject(project));
        lvAssignTechnician.setItems(getModelsHandler().getAdminModel().getCurrentProjectTechnician());


        lblProjectName.setText(String.valueOf(project.getName()));
        lblProjectDate.setText(String.valueOf(project.getDate()));
        lblCustomerName.setText(String.valueOf(project.getCustomerid()));
        txaProjectDescription.appendText(project.getDescription());

        if (project.isStatus() == false) {
            lblProjectStatus.setText("i gang");
        } else {
            lblProjectStatus.setText("Afsluttet");
        }
    }

    @FXML
    private void handlePrintDocumentation(ActionEvent actionEvent) {
    }

    @FXML
    private void handleEditProject(ActionEvent actionEvent) {
    }

    @FXML
    private void handleDeleteProject(ActionEvent actionEvent) {
    }

    @FXML
    private void handleAssignTechnicians(ActionEvent actionEvent) {
    }

    @FXML
    private void handleRemoveTechnicians(ActionEvent actionEvent) {
    }
}
