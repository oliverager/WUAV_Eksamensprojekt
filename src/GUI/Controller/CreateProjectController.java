package GUI.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CreateProjectController extends BaseController{
    @FXML
    private TextField txtProjectName, txtAutoCustomer;
    @FXML
    private DatePicker txtDate;

    @Override
    public void setup() throws IOException {

    }
}
