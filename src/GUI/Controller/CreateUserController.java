package GUI.Controller;

import BE.Project;
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
    private Button btnCreateUser, btnEditUser, btnDeleteUser;
    @FXML
    private TextField txtName, txtUsername, txtPassWord;

    private User selectedUser;



    @Override
    public void setup() throws IOException {
        setUpUserInfo();
        cbUserType.setItems(FXCollections.observableArrayList("Admin", "Technician", "ProjectManager", "SalesPerson"));
    }

    private void setUpUserInfo() {
        setEditAbleUser(false);
        if (!checkShouldEdit()){
            btnEditUser.setDisable(true);
            btnEditUser.setVisible(false);
            btnCreateUser.setVisible(false);
            btnDeleteUser.setDisable(true);
            setEditAbleUser(true);
        } else if (checkShouldEdit()) {
            setUserInfoInTxt();
        }
    }

    private void setUserInfoInTxt() {
        txtName.setText(selectedUser.getName());
        txtUsername.setText(selectedUser.getUserName());
        txtPassWord.setText(selectedUser.getPassWord());
        cbUserType.setValue(selectedUser.getClass().getSimpleName());
    }

    private void setEditAbleUser(boolean ableToEdit) {
        if (ableToEdit) {
            cbUserType.setDisable(false);
            txtName.setEditable(true);
            txtUsername.setEditable(true);
            txtPassWord.setEditable(true);
        } else if (!ableToEdit) {
            cbUserType.setDisable(true);
            txtName.setEditable(false);
            txtUsername.setEditable(false);
            txtPassWord.setEditable(false);
        }
    }
    private boolean checkShouldEdit() {
        if (selectedUser != null)
            return true;
        else
            return false;
    }

    @FXML
    private void handleCreateUser(ActionEvent actionEvent) throws Exception {
        if (checkTextFieldsNotNull()) {
            if (!checkShouldEdit()) {
                createUser();
                exit();
            } else if (checkShouldEdit()) {
                editUser();
                exit();
            }

        }
        else
            AlertOpener.validationError("Mangler at udfylde felter");
    }

    private void editUser() {
        String passWord = txtPassWord.getText();
        String userName = txtUsername.getText();
        String name = txtName.getText();

        User user1 = null;

        try {
            if (cbUserType.getValue().equals(Admin.class.getSimpleName())) {
                user1 = new Admin(passWord, userName, name);
            } else if (cbUserType.getValue().equals(Technician.class.getSimpleName())) {
                user1 = new Technician(passWord, userName, name);
            } else if (cbUserType.getValue().equals(ProjectManager.class.getSimpleName())) {
                user1 = new ProjectManager(passWord, userName, name);
            } else if (cbUserType.getValue().equals(SalesPerson.class.getSimpleName())) {
                user1 = new SalesPerson(passWord, userName, name);
            }
            if (checkIfTwoUserAreSame(user1)) {
                exit();
            }
            else {
                if (checkUsernameIsSame(user1)){
                    if (user1.getClass().getSimpleName().equals(selectedUser.getClass().getSimpleName())) {
                        getModelsHandler().getAdminModel().updateUser(user1, selectedUser);
                        exit();
                    }else {
                        getModelsHandler().getAdminModel().deleteUser(selectedUser);
                        getModelsHandler().getAdminModel().createUser(user1);
                        exit();
                    }
                } else if (getModelsHandler().getAdminModel().checkUserName(user1.getUserName())) {
                    if (user1.getClass().getSimpleName().equals(selectedUser.getClass().getSimpleName())) {
                        getModelsHandler().getAdminModel().updateUser(user1, selectedUser);
                        exit();
                    }
                    else {
                        getModelsHandler().getAdminModel().deleteUser(selectedUser);
                        getModelsHandler().getAdminModel().createUser(user1);
                        exit();
                    }
                }
                else
                    AlertOpener.validationError("username is already taken!");
            }
        } catch (Exception e) {
            ExceptionHandler.displayError(new Exception("Failed to edit user please try again"));
        }
    }

    private boolean checkUsernameIsSame(User user2) {
        if (user2.getUserName().equals(selectedUser.getUserName())) {
            return true;
        }
        else
            return false;
    }

    private boolean checkIfTwoUserAreSame(User user1){
        if (user1.getUserId() == selectedUser.getUserId()){
            if (user1.getClass().getSimpleName().equals(selectedUser.getClass().getSimpleName()))
                if (user1.getName().equals(selectedUser.getName()))
                    if (user1.getUserName().equals(selectedUser.getUserName()))
                        if (user1.getPassWord().equals(selectedUser.getPassWord()))
                            return true;
        }
        return false;
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

    @FXML
    private void handleDeleteUser(ActionEvent actionEvent) {
        if (selectedUser != null) {
            try {
                for (Project p: getModelsHandler().getProjectManagerModel().getProjectObservableList()) {

                }
                getModelsHandler().getAdminModel().deleteUser(selectedUser);
            } catch (Exception e) {
                ExceptionHandler.displayError(new Exception("Kunne ikke slet Bruger", e));
            }
        }
    }

    @FXML
    private void handleEditUser(ActionEvent actionEvent) {
        setEditAbleUser(true);
    }
}
