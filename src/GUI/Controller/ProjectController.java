package GUI.Controller;

import BE.Project;
import BE.UserType.User;
import GUI.Controller.BaseController;
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

    private Project openedProject;

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
        getModelsHandler().getAdminModel().getCurrentProjectTechnician().addAll(getModelsHandler().getAdminModel().getUsersWorkingOnProject(openedProject));
        lvAssignTechnician.setItems(getModelsHandler().getAdminModel().getCurrentProjectTechnician());


        lblProjectName.setText(openedProject.getName());
        lblProjectDate.setText(String.valueOf(openedProject.getDate()));
        lblCustomerName.setText(String.valueOf(openedProject.getCustomerid()));
        txaProjectDescription.appendText(openedProject.getDescription());

        if (openedProject.isStatus() == false) {
            lblProjectStatus.setText("i gang");
        } else {
            lblProjectStatus.setText("Afsluttet");
        }
    }

    public void setOpenedProject(Project openedProject) {
        this.openedProject = openedProject;
    }
}
