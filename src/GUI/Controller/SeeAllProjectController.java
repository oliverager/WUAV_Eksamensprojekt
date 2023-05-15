package GUI.Controller;

import BE.Project;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

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

    @Override
    public void setup() throws IOException {

        try {
            getModelsHandler().getProjectManagerModel().getAllProject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        tbcId.setCellValueFactory(new PropertyValueFactory<Project, Integer>("projectid"));
        tbcName.setCellValueFactory(new PropertyValueFactory<Project, String>("name"));
        tbcCustomer.setCellValueFactory(new PropertyValueFactory<Project, String>("customerid"));
        tbcDate.setCellValueFactory(new PropertyValueFactory<Project, LocalDate>("date"));
        tbcActive.setCellValueFactory(new PropertyValueFactory<Project, String>("status"));

        tbvProject.setItems(getModelsHandler().getProjectManagerModel().getProjectObservableList());
    }
    private void search() {
        String search = txtSearchBar.getText().toLowerCase();

        if(search != null)
            getModelsHandler().getAdminModel().searchUsers(search);
        else if (search == null){
            getModelsHandler().getAdminModel().clearSearch();
        }
    }
    public void handleSearch(KeyEvent keyEvent) {
        //search();
    }
}
