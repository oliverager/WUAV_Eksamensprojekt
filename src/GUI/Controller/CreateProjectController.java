package GUI.Controller;

import BE.Customer.Customer;
import BE.Project;
import BE.UserType.Technician;
import BE.UserType.User;
import GUI.Util.AlertOpener;
import GUI.Util.ExceptionHandler;
import GUI.Util.ImageZipper;
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
    private ListView<User> lvAssignTechnicians;
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

    private MainController mainController;



    @Override
    public void setup() throws IOException {
        cbCustomer.setItems(getModelsHandler().getSalesPersonModel().getAllCustomer());

    }

    private void createProject() {
        String name = txtProjectName.getText();
        LocalDate date = txtDate.getValue();
        String description = txaDescription.getText();
        boolean status = false;
        /**
        Image layout = imageView.getImage();
        Image image = imageView1.getImage();
        Image image2 = imageView2.getImage();
        Image image3 = imageView3.getImage();
        Image image4 = imageView4.getImage();
        Image image5 = imageView5.getImage();

        ArrayList<Image> images = new ArrayList<>();
        images.add(layout);
        images.add(image);
        images.add(image2);
        images.add(image3);
        images.add(image4);
        images.add(image5);


        String zipFileName = txtProjectName.getText().trim() +".images.zip";
        try {
            ImageZipper.createImageZip(images, zipFileName);
            System.out.println("ZIP file created successfully.");
        } catch (IOException e) {
            System.out.println("Error creating the ZIP file: " + e.getMessage());
        }
        */

        String layout = null;
        String image = null;

        ObservableList<User> technicians = lvAssignTechnicians.getItems();
        List<Integer> technicianIds = new ArrayList<>();

        // Retrieve technician IDs
        for (User user : technicians) {
            if (user instanceof Technician) {
                Technician technician = (Technician) user;
                int technicianId = user.getUserId();
                technicianIds.add(technicianId);
            }
        }

        // Assign default technician ID if the list is empty
        int defaultTechnicianId = 0;
        if (technicianIds.isEmpty()) {
            technicianIds.add(defaultTechnicianId);
        }

        // Retrieve the selected customer ID
        Customer customer = cbCustomer.getValue();
        int customerId = customer.getCustomerid();


        Project newProject = null;
        Technician newTechnician = null;

        try {
            newProject = new Project(name, date, layout, description, image, status, technicianIds, customerId);
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

    private MainController exit() throws Exception {
        mainController.openSeeAllProjectView();
        return mainController;
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
            int startIndex = currentImageIndex;
            int endIndex = currentImageIndex + 5;
            List<Image> displayImages = images.subList(startIndex, Math.min(endIndex, images.size()));

            for (int i = 0; i < displayImages.size(); i++) {
                Image image = displayImages.get(i);
                switch (i) {
                    case 0:
                        imageView.setImage(image);
                        break;
                    case 1:
                        imageView1.setImage(image);
                        break;
                    case 2:
                        imageView2.setImage(image);
                        break;
                    case 3:
                        imageView3.setImage(image);
                        break;
                    case 4:
                        imageView4.setImage(image);
                        break;
                    case 5:
                        imageView5.setImage(image);
                        break;
                    default:
                        break;
                }
            }
        }
    }

}