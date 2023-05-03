package GUI.Controller;

import BE.Project;
import GUI.Model.ModelsHandler;
import GUI.Util.ExceptionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.time.LocalDate;
import java.util.Optional;

public class MenuController extends BaseController {
    @FXML
    private Label lblUsertype;
    @FXML
    private Button BtnClose,BtnLogOut,btnMinimize,btnMaximized;
    @FXML
    private BorderPane borderPaneMenu;
    @FXML
    private Button btn1,btn2,btn3,btn4;
    @FXML
    private Button btnLogOut;
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

    private Alert alert;

    @FXML
    public void handleButton1(ActionEvent event) {

    }

    @FXML
    public void handleButton2(ActionEvent event) {

    }

    @FXML
    public void handleButton3(ActionEvent event) {

    }

    @FXML
    public void handleButton4(ActionEvent event) {

    }

    @FXML
    public void handleClose(ActionEvent event) {
        Stage stage = (Stage) btnLogOut.getScene().getWindow();
        stage.close();
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

                stage1.setTitle("EventMaster");
                stage1.initStyle(StageStyle.UNDECORATED);
                stage1.getIcons().add(new Image("/GUI/Images/EA.png"));
                stage1.setScene(scene);
                stage1.show();

                Stage stage = (Stage) btnLogOut.getScene().getWindow();
                stage.close();
            }

        } catch (Exception e) {
            ExceptionHandler.displayError(new Exception("Failed to logout please try again", e));}

    }
    private void dragScreen(){
        borderPaneMenu.setOnMousePressed(pressEvent -> {
            borderPaneMenu.setOnMouseDragged(dragEvent -> {
                borderPaneMenu.getScene().getWindow().setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                borderPaneMenu.getScene().getWindow().setY(dragEvent.getScreenY() - pressEvent.getSceneY());
            });
        });
    }
    @Override
    public void setup() {
        dragScreen();

        tbcId.setCellValueFactory(new PropertyValueFactory<Project, Integer>("id"));
        tbcName.setCellValueFactory(new PropertyValueFactory<Project, String>("name"));
        tbcDate.setCellValueFactory(new PropertyValueFactory<Project, LocalDate>("date"));
        tbcCustomer.setCellValueFactory(new PropertyValueFactory<Project, String>("customer"));
        tbcActive.setCellValueFactory(new PropertyValueFactory<Project, String>("active"));

        tbvProject.setItems(getModelsHandler().getPmModel().getProjectObservableList());

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
    private void search() {
        String search = txtSearchBar.getText().toLowerCase();

        if(search != null)
            getModelsHandler().getAdminModel().searchUsers(search);
        else if (search == null){
            getModelsHandler().getAdminModel().clearSearch();
        }
    }
    public void handleSearch(KeyEvent keyEvent) {
        search();
    }
}
