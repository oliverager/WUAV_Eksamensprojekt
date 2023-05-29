package GUI.Controller;

import BE.Customer.Customer;
import BE.Project;
import GUI.Model.ModelsHandler;
import GUI.Util.PDFGenerator;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.time.LocalDate;

public class SeeAllProjectController extends BaseController {
    @FXML
    private TextField txtSearchBar;
    @FXML
    private TableColumn<Project, String> tbcActive;
    @FXML
    private TableColumn<Project, String> tbcCustomer;
    @FXML
    private TableColumn<Project, LocalDate> tbcDate;
    @FXML
    private TableColumn<Project, Integer> tbcId;
    @FXML
    private TableColumn<Project, String> tbcName;
    @FXML
    private TableView<Project> tbvProject;
    private String lastSelectedItemType;

    @Override
    public void setup() {

        tbcId.setCellValueFactory(new PropertyValueFactory<Project, Integer>("projectid"));
        tbcName.setCellValueFactory(new PropertyValueFactory<Project, String>("name"));
        tbcCustomer.setCellValueFactory(cellData -> {
            StringProperty customerName = new SimpleStringProperty();
            int customerId = cellData.getValue().getCustomerid();

            try {
                Customer customer = getModelsHandler().getSalesPersonModel().getCustomerById(customerId);
                if (customer != null) {
                    customerName.set(customer.getName());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return customerName;
        });
        tbcDate.setCellValueFactory(new PropertyValueFactory<Project, LocalDate>("date"));
        tbcActive.setCellValueFactory(new PropertyValueFactory<Project, String>("status"));

        tbvProject.setItems(getModelsHandler().getProjectManagerModel().getProjectObservableList());
    }
    @FXML
    private void clickOnProject(MouseEvent mouseEvent) throws Exception {
        if (mouseEvent.getButton() == MouseButton.PRIMARY && mouseEvent.getClickCount() == 2) {

            lastSelectedItemType = "Project";
            checkSelectedItemType();
        }
    }

    private MainController checkSelectedItemType() throws Exception {
        if (tbvProject.getSelectionModel().getSelectedItem() != null && lastSelectedItemType.equals("Project")) {
            mainController.openProjectView();
        }
        return mainController;
    }
    public Project getSelectedProject() {
        Project selectedProject = tbvProject.getSelectionModel().getSelectedItem();
        if (selectedProject != null) {
            return selectedProject;
        } else
            return null;
    }


    private void search() {
        String search = txtSearchBar.getText().toLowerCase();

        if(search != null)
            getModelsHandler().getProjectManagerModel().searchProjects(search);
        else if (search == null){
            getModelsHandler().getProjectManagerModel().clearSearch();
        }
    }
    @FXML
    private void handleSearch(KeyEvent keyEvent) {
        search();
    }


    public void PDF(ActionEvent actionEvent) {
        Project selectedProject = tbvProject.getSelectionModel().getSelectedItem();

        if (selectedProject != null) {
            String filePath = selectedProject.getName().trim() + ".pdf";
            PDFGenerator.generatePDF(filePath, selectedProject);
        }
    }
}
