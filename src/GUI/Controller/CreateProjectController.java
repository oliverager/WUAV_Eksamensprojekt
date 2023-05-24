package GUI.Controller;

import BE.Customer.Customer;
import BE.Project;
import BE.UserType.Technician;
import BE.UserType.User;
import GUI.Util.AlertOpener;
import GUI.Util.ExceptionHandler;
import GUI.Util.SlideshowTask;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CreateProjectController extends BaseController{
    @FXML
    private ListView<Technician> lvAssignTechnicians;
    @FXML
    private Button assignTechnicians;
    @FXML
    private ImageView imageView, imageView1, imageView2, imageView3, imageView4, imageView5;
    @FXML
    private ComboBox<Customer> cbCustomer;
    @FXML
    private TextArea txaDescription;
    @FXML
    private TextField txtProjectName;
    @FXML
    private DatePicker txtDate;
    private final List<Image> images = new ArrayList<>();

    private int currentImageIndex = 0;

    private SlideshowTask slideshowTask;

    @Override
    public void setup() throws IOException {
        cbCustomer.setItems(getModelsHandler().getSalesPersonModel().getAllCustomer());

    }

    private void createProject() { 
        String name = txtProjectName.getText();
        LocalDate date = txtDate.getValue();
        Image layout = imageView.getImage();
        String description = txaDescription.getText();
        Image image = imageView1.getImage();
        boolean status = false;
        int assignTechnicians = lvAssignTechnicians.getEditingIndex();
        //int customer = cbCustomer.getValue();


        Project newProject = null;
        Technician newTechnician = null;

        try {
            //newProject = new Project(name, date, layout, description, image, status, assignTechnicians, customer);
            newTechnician = getModelsHandler().getLoginModel().getLoggedInTechnician();

            getModelsHandler().getProjectManagerModel().createProject(newProject, newTechnician);
        } catch (Exception e) {
            ExceptionHandler.displayError(new Exception("", e));
        }
    }

    @FXML
    private void handleAssignTechnicians(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/AssignTechniciansView.fxml"));
        Parent root = null;

        try {
            root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.getIcons().add(new Image("/GUI/Images/WUAVlogo.png"));

            AssignTechniciansController controller = loader.getController();
            controller.setModel(getModelsHandler());
            controller.setOpenedProject(project);

            controller.setup();

            stage.showAndWait();

        } catch (IOException e) {
            ExceptionHandler.displayError(new Exception("Kunne ikke åbne tilføre tekniker vinduet", e));
        }
    }

    @FXML
    private void handleRemoveTechnicians(ActionEvent actionEvent) {
        if (lvAssignTechnicians.getSelectionModel().getSelectedItem() != null) {
            try {
                getModelsHandler().getProjectManagerModel().removeUserFromProject(lvAssignTechnicians.getSelectionModel().getSelectedItem(), project);
            } catch (Exception e) {
                ExceptionHandler.displayError(new Exception("Kunne ikke fjerne teknikerne fra projektet", e));
            }
        }
    }

    @FXML
    private void handleCreateProject(ActionEvent actionEvent) throws Exception {
        if (checkTextFieldsNotNull()) {
            createProject();
            exit();
        }
        else
            AlertOpener.validationError("Mangler at udfylde felter");
    }

    private boolean checkTextFieldsNotNull() {
        if (checktxtName()) {
            return true;
        }
        else
            return false;
    }

    private boolean checktxtName() {
        if (!txtProjectName.getText().isEmpty() && txtProjectName.getText() != null) return true;
        else return false;
    }

    @FXML
    private void handleUploadImages(ActionEvent actionEvent) {
        fileChooser();

    }

    private void exit() throws Exception {
        setMainController(mainController.openSeeAllProjectView());
    }
    
    private void fileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select image files");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images",
                "*.png", "*.jpg", "*.gif", "*.tif", "*.bmp"));
        List<File> files = fileChooser.showOpenMultipleDialog(new Stage());

        if (!files.isEmpty())
        {
            files.forEach((File f) ->
            {
                images.add(new Image(f.toURI().toString()));
            });
            displayImage();
        }
    }

    private void displayImage() {
        if (!images.isEmpty()) {
            for (Image im : images) {
                
            }

            imageView.setImage(images.get(currentImageIndex));
            imageView1.setImage(images.get(currentImageIndex + 1));
            imageView2.setImage(images.get(currentImageIndex + 2));
            imageView3.setImage(images.get(currentImageIndex + 3));
            imageView4.setImage(images.get(currentImageIndex + 4));
            imageView5.setImage(images.get(currentImageIndex + 5));

        }
    }
}
