package GUI.Controller;

import BE.Customer.Business;
import BE.Customer.Private;
import BE.Customer.Government;
import BE.Customer.Customer;
import BE.UserType.User;
import GUI.Util.AlertOpener;
import GUI.Util.ExceptionHandler;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CreateCustomerController extends BaseController {
    @FXML
    private TextField txtName, txtAddress, txtCvr;
    @FXML
    private ComboBox<String> cbCustomerType;

    private Customer selectedCustomer;

    public void setCreateCustomer(Customer customer) {
        this.selectedCustomer = customer;
    }

    @Override
    public void setup() throws IOException {
        cbCustomerType.setItems(FXCollections.observableArrayList("Business", "Private", "Government"));
    }

    @FXML
    private void handleCreateUser(ActionEvent event) throws Exception {
        if (checkTextFieldsNotNull()) {
            createCustomer();
            exit();
        }
        else
            AlertOpener.validationError("Mangler at udfylde felter");
    }
    private void exit() throws Exception {
        setMainController(mainController.openSeeAllCustomerView());
    }

    private void createCustomer() {
        String name = txtName.getText();
        String address = txtAddress.getText();
        String cvr = txtCvr.getText();

        Customer newCustomer = null;

        try {

            if (cbCustomerType.getValue().equals(Business.class.getSimpleName())) {
                newCustomer = new Business(name, address, cvr);
            } else if (cbCustomerType.getValue().equals(Private.class.getSimpleName())) {
                newCustomer = new Private(name, address ,cvr);
            } else if (cbCustomerType.getValue().equals(Government.class.getSimpleName())) {
                newCustomer = new Government(name, address, cvr);
            }
            if (newCustomer != null) {
                getModelsHandler().getSalesPersonModel().createCustomer(newCustomer);

            }

        } catch (Exception e) {
            e.printStackTrace();
            ExceptionHandler.displayError(new Exception("Failed to create Customer please try again", e));
        }
    }

    private boolean checkTextFieldsNotNull() {
        if (checktxtName() && checktxtAddress() && checkcbCustomerType()){
            return true;
        }
        else
            return false;
    }

    private boolean checkcbCustomerType() {
        if (!cbCustomerType.getValue().isEmpty() && cbCustomerType.getValue() != null) return true;
        else return false;
    }

    private boolean checktxtAddress() {
        if (!txtAddress.getText().isEmpty() && txtAddress.getText() != null) return true;
        else return false;
    }

    private boolean checktxtName() {
        if (!txtName.getText().isEmpty() && txtName.getText() != null) return true;
        else return false;
    }
}
