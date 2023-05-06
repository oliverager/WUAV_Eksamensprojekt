package GUI.Controller;

import GUI.Model.ModelsHandler;
import GUI.Util.ExceptionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuController extends BaseController {
    @FXML
    private StackPane contentArea;
    @FXML
    private Label lblUsertype;
    @FXML
    private Button btnClose, btnLogOut, btnMinimize, btnMaximized;
    @FXML
    private BorderPane borderPaneMenu;
    @FXML
    private Button btn1, btn2, btn3, btn4;

    private Alert alert;



    private void openCreateProjectView() throws IOException {
        Parent view = FXMLLoader.load(getClass().getResource("GUI/View/CreateProjectView.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().add(view);
    }
    private void openSeeAllProjectView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("src/GUI/View/SeeAllProjectView.fxml"));
        Parent view = loader.load();
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(view);
    }

    public void openImage() throws IOException{
        ImageView image = new ImageView("GUI/Images/WUAVlogo.png");
        contentArea.getChildren().add(image);
    }

    @FXML
    public void handleButton1(ActionEvent event) throws IOException {
        openCreateProjectView();
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

    @Override
    public void setup() {
        dragScreen();
        try {
            openImage();
        } catch (IOException ex) {
            Logger.getLogger(ModuleLayer.Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
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
