package GUI.Controller;

import BE.Project;
import BE.UserType.User;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

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
    private TableColumn<User, Integer> tbcUserType;
    private String lastSelectedItemType;

    @FXML
    private void handleSearch(KeyEvent keyEvent) {
        search();
    }

    @Override
    public void setup() throws IOException {

        try {
            getModelsHandler().getAdminModel().retreiveAllUsers();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        tbcId.setCellValueFactory(new PropertyValueFactory<User, Integer>("userId"));
        tbcName.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        tbcUserType.setCellValueFactory(new PropertyValueFactory<User, Integer>("userType"));

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

    public User getSelectedUser() {
        User selectedUser = tbvUser.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            return selectedUser;
        } else
            return null;
    }

    public void clickOnProject(MouseEvent mouseEvent) throws Exception {
        lastSelectedItemType = "User";
        if (mouseEvent.getButton() == MouseButton.PRIMARY && mouseEvent.getClickCount() == 2) {
            checkSelectedItemType();
        }
    }

    private MainController checkSelectedItemType() throws Exception {
        if (tbvUser.getSelectionModel().getSelectedItem() != null && lastSelectedItemType.equals("User")) {
            mainController.openCreateCustomerView();
        }
        return mainController;
    }
}
