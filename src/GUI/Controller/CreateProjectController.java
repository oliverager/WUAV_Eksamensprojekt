package GUI.Controller;

import BE.Customer.Customer;
import BE.Project;
import BE.UserType.Technician;
import GUI.Util.AlertOpener;
import GUI.Util.ExceptionHandler;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.time.LocalDate;

public class CreateProjectController extends BaseController{
    @FXML
    private ListView<Technician> lvAssignTechnicians;
    @FXML
    private ComboBox<Technician> assignTechnicians;
    @FXML
    private ImageView imgLayout, imgImage;
    @FXML
    private ComboBox<Customer> cbCustomer;
    @FXML
    private TextArea txaDescription;
    @FXML
    private TextField txtProjectName;
    @FXML
    private DatePicker txtDate;

    @Override
    public void setup() throws IOException {
        cbCustomer.setItems(getModelsHandler().getSalesPersonModel().getAllCustomer());
    }

    private void createProject() {
        String name = txtProjectName.getText();
        LocalDate date = txtDate.getValue();
        Image layout = imgLayout.getImage();
        String description = txaDescription.getText();
        Image image = imgImage.getImage();
        boolean status = false;
        int assignTechnicians = lvAssignTechnicians.getEditingIndex();
        //int customer = cbCustomer.getValue();


        Project newProject = null;
        Technician newTechnician = null;

        try {
            //newProject = new Project(name, date, layout, description, image, status, assignTechnicians, customer);
            newTechnician = getModelsHandler().getLoginModel().getLoggedInTechnician();

            getModelsHandler().getProjectManagerModel().createProject(newProject, newTechnician);
        } catch (Exception e) {
            ExceptionHandler.displayError(new Exception("", e));
        }
    }

    @FXML
    private void handleAssignTechnicians(ActionEvent actionEvent) {
    }

    @FXML
    private void handleRemoveTechnicians(ActionEvent actionEvent) {
    }

    @FXML
    private void handleCreateProject(ActionEvent actionEvent) throws Exception {
        if (checkTextFieldsNotNull()) {
            createProject();
            exit();
        }
        else
            AlertOpener.validationError("Mangler at udfylde felter");
    }

    private boolean checkTextFieldsNotNull() {
        if (checktxtName()) {
            return true;
        }
        else
            return false;
    }

    private boolean checktxtName() {
        if (!txtProjectName.getText().isEmpty() && txtProjectName.getText() != null) return true;
        else return false;
    }

    @FXML
    private void handleUploadLayout(ActionEvent actionEvent) {
    }

    @FXML
    private void handleUploadImages(ActionEvent actionEvent) {
    }

    private void exit() throws Exception {
        setMainController(mainController.openSeeAllProjectView());
    }
}
