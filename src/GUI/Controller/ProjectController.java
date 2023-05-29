package GUI.Controller;

import BE.Customer.Customer;
import BE.Project;
import BE.UserType.User;
import GUI.Controller.BaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.List;

public class ProjectController extends BaseController {
    @FXML
    private ImageView imageView, imageView1 ,imageView2, imageView3, imageView4, imageView5;
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
            setProjectInfo(project);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Kunne ikke loade projektets information", e);
        }
    }

    private void setProjectInfo(Project project) throws Exception {
        try {
            if (project != null) {
                lblProjectName.setText(project.getName());
                txaProjectDescription.setText(project.getDescription());
                lblProjectDate.setText(project.getDate().toString());
                lblCustomerName.setText("");

                // Set project status
                if (project.isStatus() == false) {
                    lblProjectStatus.setText("i gang");
                } else {
                    lblProjectStatus.setText("Afsluttet");
                }

                int customerId = project.getCustomerid();
                try {
                    Customer customer = getModelsHandler().getSalesPersonModel().getCustomerById(project.getCustomerid());
                    if (customer != null) {
                        lblCustomerName.setText(customer.getName());
                    } else {
                        lblCustomerName.setText("Customer Not Found");
                    }
                } catch (Exception e) {
                    lblCustomerName.setText("Error Fetching Customer");
                    e.printStackTrace();
                }
            } else {
                lblProjectName.setText("");
                txaProjectDescription.setText("");
                lblProjectDate.setText("");
                lblCustomerName.setText("");
                lblProjectStatus.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handlePrintDocumentation(ActionEvent actionEvent) {
        //getModelsHandler().getSalesPersonModel().createPdf();
    }

    @FXML
    private void handleEditProject(ActionEvent actionEvent) {
    }

    @FXML
    private void handleDeleteProject(ActionEvent actionEvent) throws Exception {
        getModelsHandler().getProjectManagerModel().removeProjectFromLocal(project);
        getModelsHandler().getAdminModel().deleteProject(project);
        exit();
    }

    private MainController exit() throws Exception {
        mainController.openSeeAllProjectView();
        return mainController;
    }
}
