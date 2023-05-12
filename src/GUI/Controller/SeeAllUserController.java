package GUI.Controller;

import BE.UserType.User;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

public class SeeAllUserController extends BaseController {
    @FXML
    private TextField txtSearchBar;
    @FXML
    private TableView<User> tbvUser;
    @FXML
    private TableColumn<User, Integer> tbcId;
    @FXML
    private TableColumn<User, String> tbcName;
    @FXML
    private TableColumn<User, String> tbcUserType;

    @Override
    public void setup() throws IOException {

        tbcId.setCellValueFactory(new PropertyValueFactory<User, Integer>("Id"));
        tbcName.setCellValueFactory(new PropertyValueFactory<User, String>("Navn"));
        tbcUserType.setCellValueFactory(new PropertyValueFactory<User, String>("Bruger Type"));

        tbvUser.setItems(getModelsHandler().getAdminModel().getUserObservableList());
    }
    private void search() {
        String search = txtSearchBar.getText().toLowerCase();

        if(search != null)
            getModelsHandler().getAdminModel().searchUsers(search);
        else if (search == null){
            getModelsHandler().getAdminModel().clearSearch();
        }
    }

    @FXML
    private void handleSearch(KeyEvent keyEvent) {
        search();
    }
}
