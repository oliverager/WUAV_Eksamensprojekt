package GUI.Controller;

import BE.UserType.*;
import GUI.Util.ExceptionHandler;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;

public class CreateUserController extends BaseController {

    @FXML
    private ComboBox<String> cbUserType;
    @FXML
    private Button btnCreateUser;
    @FXML
    private TextField txtName, txtUsername, txtPassWord;
    private MainController mainController;


    @Override
    public void setup() throws IOException {
        cbUserType.setItems(FXCollections.observableArrayList("Admin", "Technician", "ProjectManager", "SalesPerson"));
    }
    @FXML
    private void handleCreateUser(ActionEvent actionEvent) {
        createUser();
    }

    private void exit() throws Exception {
        mainController.openProjectView();
    }

    private void createUser() {
        String passWord = txtPassWord.getText();
        String userName = txtUsername.getText();
        String name = txtName.getText();

        User newUser = null;

        try {
            if (getModelsHandler().getAdminModel().checkUserName(userName)) {
                System.out.println("username is not the same");
                if (cbUserType.getItems().equals(Admin.class.getSimpleName())) {
                    newUser = new Admin(passWord, userName, name);
                } else if (cbUserType.getItems().equals(Technician.class.getSimpleName())) {
                    newUser = new Technician(passWord, userName, name);
                } else if (cbUserType.getItems().equals(ProjectManager.class.getSimpleName())) {
                    newUser = new ProjectManager(passWord, userName, name);
                } else if (cbUserType.getItems().equals(SalesPerson.class.getSimpleName())) {
                    newUser = new SalesPerson(passWord, userName, name);
                }
                if (newUser != null) {
                    getModelsHandler().getAdminModel().createUser(newUser);
                    exit();
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Username is already taken");
                alert.showAndWait();
            }
        } catch (Exception e) {
            ExceptionHandler.displayError(new Exception("Failed to create user please try again", e));
        }
    }

    @FXML
    private void getSelectedType(ActionEvent actionEvent) {
    }
}
