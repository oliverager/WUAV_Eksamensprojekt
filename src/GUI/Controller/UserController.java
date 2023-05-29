package GUI.Controller;

import BE.Project;
import BE.UserType.User;
import BE.UserType.UserType;
import GUI.Util.ExceptionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class UserController extends BaseController {
    @FXML
    private Label lblName, lblUserType, lblUserName, lblPassword;

    private User selectedUser;

    @Override
    public void setup() throws IOException {
        this.selectedUser = user;
        try {
            setUserInfo(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Kunne ikke loade Brugerens information", e);
        }
    }

    private void setUserInfo(User user) {
        try {
            if (user != null) {
                lblName.setText(user.getName());
                lblUserName.setText(user.getUserName());
                lblPassword.setText(user.getPassWord());

                int userTypeId = user.getUserType();
                try {
                    UserType userType = getModelsHandler().getAdminModel().getUserTypeById(userTypeId);
                    if (userType != null) {
                        lblUserType.setText(userType.getUserTypeName());
                    } else {
                        lblUserType.setText("Error BrugerType er null");
                    }
                } catch (Exception e) {
                    lblUserType.setText("Error Fetching User");
                    e.printStackTrace();
                }
            } else {
                lblName.setText("");
                lblUserName.setText("");
                lblPassword.setText("");
                lblUserType.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleDeleteUser(ActionEvent actionEvent) {
        if (selectedUser != null) {
            try {
                getModelsHandler().getAdminModel().deleteUser(selectedUser);
            } catch (Exception e) {
                ExceptionHandler.displayError(new Exception("Kunne ikke slet Bruger", e));
            }
        }
    }

    public void handleEditUser(ActionEvent actionEvent) {
    }
}
