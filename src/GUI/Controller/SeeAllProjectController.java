package GUI.Controller;

import BE.Project;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SeeAllProjectController extends BaseController implements Initializable {
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

        tbcId.setCellValueFactory(new PropertyValueFactory<Project, Integer>("Id"));
        tbcName.setCellValueFactory(new PropertyValueFactory<Project, String>("Projekt"));
        tbcCustomer.setCellValueFactory(new PropertyValueFactory<Project, String>("Kunde"));
        tbcDate.setCellValueFactory(new PropertyValueFactory<Project, LocalDate>("Data"));
        tbcActive.setCellValueFactory(new PropertyValueFactory<Project, String>("Aktivere"));

        tbvProject.setItems(getModelsHandler().getPmModel().getProjectObservableList());
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
