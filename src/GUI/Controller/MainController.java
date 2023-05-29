package GUI.Controller;

import BE.Customer.Customer;
import BE.Customer.Private;
import BE.Project;
import BE.UserType.User;
import GUI.Model.ModelsHandler;
import GUI.Util.ExceptionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class MainController extends BaseController {
    @FXML
    private StackPane contentArea;
    @FXML
    private Label lblUsertype;
    @FXML
    private Button btnClose, btnLogOut, btnMinimize, btnMaximized;
    @FXML
    private BorderPane borderPaneMenu;
    @FXML
    private Button btn1, btn2, btn3;
    private Alert alert;


    protected void openCreateProjectView() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/CreateProjectView.fxml"));
        Parent view = loader.load();

        CreateProjectController controller = loader.getController();
        controller.setModel(new ModelsHandler());
        controller.setMainController(this);
        controller.setup();

        contentArea.getChildren().clear();
        contentArea.getChildren().add(view);
    }
    protected void openSeeAllProjectView() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/SeeAllProjectsView.fxml"));
        Parent view = loader.load();

        SeeAllProjectController controller = loader.getController();
        controller.setModel(new ModelsHandler());
        controller.setMainController(this);
        controller.setup();

        contentArea.getChildren().clear();
        contentArea.getChildren().add(view);

    }
    protected void openCreateUserView() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/CreateUserView.fxml"));
        Parent view = loader.load();

        CreateUserController controller = loader.getController();
        controller.setModel(new ModelsHandler());
        controller.setMainController(this);

        controller.setup();

        contentArea.getChildren().clear();
        contentArea.getChildren().add(view);
    }
    protected void openUserView() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/UserView.fxml"));
        Parent view = loader.load();

        UserController controller = loader.getController();
        controller.setModel(new ModelsHandler());
        controller.setMainController(this);

        SeeAllUserController seeAllUserController = new SeeAllUserController();
        User selectedUser = seeAllUserController.getSelectedUser();

        if (selectedUser != null) {
            controller.setOpenedUser(selectedUser);
            controller.setup();
        }

        contentArea.getChildren().clear();
        contentArea.getChildren().add(view);
    }
    protected void openProjectView() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/ProjectView.fxml"));
        Parent view = loader.load();

        ProjectController controller = loader.getController();
        controller.setModel(new ModelsHandler());
        controller.setMainController(this);

        // Retrieve the selected project from SeeAllProjectController
        SeeAllProjectController seeAllProjectController = new SeeAllProjectController();
        Project selectedProject = seeAllProjectController.getSelectedProject();

        if (selectedProject != null) {
            controller.setOpenedProject(selectedProject);
            controller.setup();

            contentArea.getChildren().clear();
            contentArea.getChildren().add(view);
        }
    }
    protected void openCreateCustomerView() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/CreateCustomerView.fxml"));
        Parent view = loader.load();

        CreateCustomerController controller = loader.getController();
        controller.setModel(new ModelsHandler());
        controller.setMainController(this);

        controller.setup();

        contentArea.getChildren().clear();
        contentArea.getChildren().add(view);
    }

    protected void openSeeAllUserView() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/SeeAllUserView.fxml"));
        Parent view = loader.load();

        SeeAllUserController controller = loader.getController();
        controller.setModel(new ModelsHandler());
        controller.setMainController(this);
        controller.setup();

        contentArea.getChildren().clear();
        contentArea.getChildren().add(view);

    }
    protected void openSeeAllCustomerView() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/SeeAllCustomerView.fxml"));
        Parent view = loader.load();

        SeeAllCustomerController controller = loader.getController();
        controller.setModel(new ModelsHandler());
        controller.setMainController(this);
        controller.setup();

        contentArea.getChildren().clear();
        contentArea.getChildren().add(view);
    }


    @FXML
    public void handleButton1(ActionEvent event) {
        try {
            if (btn1.getText().equals("Opret nyt \n Projekt")) {
                openCreateProjectView();
            } else if (btn1.getText().equals("Opret ny Bruger")) {
                openCreateUserView();
            } else if (btn1.getText().equals("Opret ny kunde")) {
                openCreateCustomerView();
            }
        } catch (Exception e) {
            e.printStackTrace();
            ExceptionHandler.displayError(new Exception("Failed to do action pleas try again", e));
        }
    }

    @FXML
    public void handleButton2(ActionEvent event) {
        try {
            if (btn2.getText().equals("Opret ny kunde")) {
                openCreateCustomerView();
            } else if (btn2.getText().equals("Se alle Bruger")) {
                openSeeAllUserView();
            } else if (btn2.getText().equals("Se alle kunder")) {
                openSeeAllCustomerView();
            }
        } catch (Exception e) {
            e.printStackTrace();
            ExceptionHandler.displayError(new Exception("Failed to do action pleas try again", e));
        }
    }

    @FXML
    public void handleButton3(ActionEvent event) {
        try {
            if (btn3.getText().equals("Opret ny kunde")) {
                openCreateCustomerView();
            }
        } catch (Exception e) {
            e.printStackTrace();
            ExceptionHandler.displayError(new Exception("Failed to do action pleas try again", e));
        }
    }

    @FXML
    public void HandlingReturningHome(MouseEvent mouseEvent) throws Exception {
        openSeeAllProjectView();
    }


    @FXML
    public void handleLogOut(ActionEvent event) {
        try{
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Error Message");
            alert.setContentText("Are you sure want to logout?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)){
                // Link your login form and show it
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/LoginView.fxml"));
                Parent root = loader.load();

                Stage stage1 = new Stage();
                Scene scene = new Scene(root);

                BaseController controller = loader.getController();
                controller.setModel(new ModelsHandler());
                controller.setup();

                stage1.initStyle(StageStyle.UNDECORATED);
                stage1.getIcons().add(new Image("GUI/Images/WUAVlogo.png"));
                stage1.setScene(scene);
                stage1.show();

                contentArea.getChildren().clear();
                Stage stage = (Stage) btnLogOut.getScene().getWindow();
                stage.close();
            }

        } catch (Exception e) {
            ExceptionHandler.displayError(new Exception("Failed to logout please try again", e));}
    }

    @Override
    public void setup() {
        dragScreen();

        try {
            checkUserAndSetup();
            //grantingAccess();
            openSeeAllProjectView();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private void checkUserAndSetup() throws Exception {
        if(getModelsHandler().getLoginModel().getLoggedInAdmin() != null) {
            setupAdmin();
        }
        else if (getModelsHandler().getLoginModel().getLoggedInProjectManager() != null) {
            setupProjectManager();
        }
        else if (getModelsHandler().getLoginModel().getLoggedInTechnician() != null) {
            setupTechnician();
        }
        else if (getModelsHandler().getLoginModel().getLoggedInSalesPerson() != null) {
            setupSalesPerson();
        }
    }
    private void grantingAccess() {
        if (getModelsHandler().getLoginModel().getLoggedInTechnician() != null) {
            boolean userAccess = false;
            int loggedInUserId = getModelsHandler().getLoginModel().getLoggedInTechnician().getUserId();
            for (User u : getModelsHandler().getAdminModel().getCurrentProjectTechnician()) {
                if (u.getUserId() == loggedInUserId) {
                    userAccess = true;
                }
            }
            if (!userAccess){

            }
        }
    }

    private void setupAdmin() {
        lblUsertype.setText("Admin");
        btn1.setText("Opret ny Bruger");
        btn2.setText("Se alle Bruger");
        btn3.setDisable(true);
        btn3.setVisible(false);
    }
    private void setupProjectManager() {
        lblUsertype.setText("Projekt Manager");
        btn1.setText("Opret nyt \n Projekt");
        btn2.setText("Se alle kunder");
        btn3.setText("Opret ny kunde");
    }
    private void setupTechnician() {
        lblUsertype.setText("Tekniker");
        btn1.setText("Opret nyt \n Projekt");
        btn2.setDisable(true);
        btn2.setVisible(false);
        btn3.setDisable(true);
        btn3.setVisible(false);
    }
    private void setupSalesPerson() {
        lblUsertype.setText("Sælger");
        btn1.setText("Opret ny kunde");
        btn2.setText("Se alle kunder");
        btn3.setDisable(true);
        btn3.setVisible(false);
    }


    @FXML
    public void handleClose(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
    private void dragScreen(){
        borderPaneMenu.setOnMousePressed(pressEvent -> {
            borderPaneMenu.setOnMouseDragged(dragEvent -> {
                borderPaneMenu.getScene().getWindow().setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                borderPaneMenu.getScene().getWindow().setY(dragEvent.getScreenY() - pressEvent.getSceneY());
            });
        });
    }
    @FXML
    public void handleMinimize(ActionEvent actionEvent) {
        Stage stage = (Stage) btnMinimize.getScene().getWindow();
        stage.setIconified(true);
    }
    @FXML
    public void handleMaximized(ActionEvent actionEvent) {
        Stage stage = (Stage) btnMaximized.getScene().getWindow();
        if (stage.isMaximized()) {
            stage.setMaximized(false);
        } else {
            stage.setMaximized(true);
        }
    }
}