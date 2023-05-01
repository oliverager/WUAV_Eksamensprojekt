package GUI.Controller;

import BE.UserType.Admin;
import BE.UserType.ProjectManager;
import BE.UserType.SalesPerson;
import BE.UserType.Technician;
import GUI.Util.ExceptionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class LoginController extends BaseController {
    @FXML
    private BorderPane borderPaneLogin;
    @FXML
    private Button BtnClose,BtnLogin,btnMinimize,btnMaximized;
    @FXML
    private TextField txtPassword, txtUsername;
    private ExceptionHandler exceptionHandler;

    @FXML
    void handleLogin() {
        try {
            String username = txtUsername.getText();
            String password = txtPassword.getText();
            getModelsHandler().getLoginModel().loginAction(username, password);
            Admin admin = getModelsHandler().getLoginModel().getLoggedInAdmin();
            Technician technician = getModelsHandler().getLoginModel().getLoggedInTechnician();
            ProjectManager pm = getModelsHandler().getLoginModel().getLoggedInProjectManager();
            SalesPerson sp = getModelsHandler().getLoginModel().getLoggedInSalesPerson();
            if (admin != null) {
                openMenuView();
                Stage stage = (Stage) BtnLogin.getScene().getWindow();
                stage.close();
            } else if (technician != null) {
                openMenuView();
                Stage stage = (Stage) BtnLogin.getScene().getWindow();
                stage.close();
            } else if (pm != null) {
                openMenuView();
                Stage stage = (Stage) BtnLogin.getScene().getWindow();
                stage.close();
            } else if (sp != null) {
                openMenuView();
                Stage stage = (Stage) BtnLogin.getScene().getWindow();
                stage.close();
            }

        }catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING,"Incorrect username or password, please try again");
            txtPassword.clear();
            txtUsername.clear();
            alert.showAndWait();
        }


    }
    private void openMenuView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/MenuView.fxml"));

        Parent root = loader.load();
        Stage stage = new Stage();

        BaseController controller = loader.getController();
        controller.setModel(super.getModelsHandler());
        controller.setup();

        stage.setScene(new Scene(root));
        stage.setTitle("MainMenu");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.getIcons().add(new Image("GUI/Images/WUAVlogo.png"));
        stage.show();
    }


    public void handleClose(ActionEvent actionEvent) {
        Stage stage = (Stage) BtnClose.getScene().getWindow();
        stage.close();
    }

    @Override
    public void setup() {
        exceptionHandler = new ExceptionHandler();
        dragScreen();
    }

    public void handleEnter(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)){
            openMenuView();
        }
    }
    private void dragScreen(){
        borderPaneLogin.setOnMousePressed(pressEvent -> {
            borderPaneLogin.setOnMouseDragged(dragEvent -> {
                borderPaneLogin.getScene().getWindow().setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                borderPaneLogin.getScene().getWindow().setY(dragEvent.getScreenY() - pressEvent.getSceneY());
            });
        });
    }

    public void handleMinimize(ActionEvent actionEvent) {
        Stage stage = (Stage) btnMinimize.getScene().getWindow();
        stage.setIconified(true);
    }

    public void handleMaximized(ActionEvent actionEvent) {
        Stage stage = (Stage) btnMaximized.getScene().getWindow();
        if (stage.isMaximized()) {
            stage.setMaximized(false);
        } else {
            stage.setMaximized(true);
        }

    }
}
