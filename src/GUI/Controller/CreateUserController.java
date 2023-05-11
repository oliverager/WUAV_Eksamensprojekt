package GUI.Controller;

import BE.UserType.*;
import GUI.Util.ExceptionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CreateUserController extends BaseController {

    @FXML
    private Button btnCreateUser;
    @FXML
    private TextField txtName, txtUserType, txtUsername, txtPassWord;

    @Override
    public void setup() throws IOException {

    }
    @FXML
    private void handleCreateUser(ActionEvent actionEvent) {
        createUser();
    }

    private void exit() {

    }

    private void createUser() {
        String passWord = txtPassWord.getText();
        String userName = txtUsername.getText();
        String name = txtName.getText();

        User newUser = null;

        try {
            if (getModelsHandler().getAdminModel().checkUserName(userName)) {
                System.out.println("username is not the same");
                if (txtUserType.getText().equals(Admin.class.getSimpleName())) {
                    newUser = new Admin(passWord, userName, name);
                } else if (txtUserType.getText().equals(Technician.class.getSimpleName())) {
                    newUser = new Technician(passWord, userName, name);
                } else if (txtUserType.getText().equals(ProjectManager.class.getSimpleName())) {
                    newUser = new ProjectManager(passWord, userName, name);
                } else if (txtUserType.getText().equals(SalesPerson.class.getSimpleName())) {
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
}
