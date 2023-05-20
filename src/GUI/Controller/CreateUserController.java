package GUI.Controller;

import BE.UserType.*;
import GUI.Util.AlertOpener;
import GUI.Util.ExceptionHandler;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CreateUserController extends BaseController {

    @FXML
    private ComboBox<String> cbUserType;
    @FXML
    private Button btnCreateUser;
    @FXML
    private TextField txtName, txtUsername, txtPassWord;

    private User selectedUser;

    public void setCreateUser(User user) {
        this.selectedUser = user;
    }

    @Override
    public void setup() throws IOException {
        cbUserType.setItems(FXCollections.observableArrayList("Admin", "Technician", "ProjectManager", "SalesPerson"));
    }
    @FXML
    private void handleCreateUser(ActionEvent actionEvent) throws Exception {
        if (checkTextFieldsNotNull()) {
            createUser();
            exit();
        }
        else
            AlertOpener.validationError("Mangler at udfylde felter");
    }

    private void exit() throws Exception {
        setMainController(mainController.openSeeAllUserView());
    }

    private void createUser() {
        String passWord = txtPassWord.getText();
        String userName = txtUsername.getText();
        String name = txtName.getText();

        User newUser = null;

        try {
            if (getModelsHandler().getAdminModel().checkUserName(userName)) {
                System.out.println("username is not the same");
                if (cbUserType.getValue().equals(Admin.class.getSimpleName())) {
                    newUser = new Admin(passWord, userName, name);
                } else if (cbUserType.getValue().equals(Technician.class.getSimpleName())) {
                    newUser = new Technician(passWord, userName, name);
                } else if (cbUserType.getValue().equals(ProjectManager.class.getSimpleName())) {
                    newUser = new ProjectManager(passWord, userName, name);
                } else if (cbUserType.getValue().equals(SalesPerson.class.getSimpleName())) {
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
            e.printStackTrace();
            ExceptionHandler.displayError(new Exception("Failed to create user please try again", e));
        }
    }

    private boolean checkTextFieldsNotNull(){
        if (checktxtName() && checktxtUserName() && checktxtPassWord() && checkcbUserType()){
            return true;
        }
        else
            return false;
    }

    private boolean checkcbUserType() {
        if (!cbUserType.getValue().isEmpty() && cbUserType.getValue() != null) return true;
        else return false;
    }

    private boolean checktxtUserName() {
        if (!txtUsername.getText().isEmpty() && txtUsername.getText() != null) return true;
        else return false;
    }

    private boolean checktxtName() {
        if (!txtName.getText().isEmpty() && txtName.getText() != null) return true;
        else return false;
    }

    private boolean checktxtPassWord() {
        if (!txtPassWord.getText().isEmpty() && txtPassWord.getText() != null) return true;
        else return false;
    }
}
