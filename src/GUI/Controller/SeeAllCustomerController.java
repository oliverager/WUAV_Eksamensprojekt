package GUI.Controller;

import BE.Customer.Customer;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

public class SeeAllCustomerController extends BaseController {
    @FXML
    private TableView<Customer> tbvCustomer;
    @FXML
    private TableColumn<Customer, Integer> tbcId;
    @FXML
    private TableColumn<Customer, String> tbcName;
    @FXML
    private TableColumn<Customer, Double> tbcCVR;
    @FXML
    private TableColumn<Customer, String> tbcCustomerType;
    @FXML
    private TextField txtSearchBar;

    @Override
    public void setup() throws IOException {

        tbcId.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("customerid"));
        tbcName.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
        tbcCVR.setCellValueFactory(new PropertyValueFactory<Customer, Double>("cvrNummer"));
        tbcCustomerType.setCellValueFactory(new PropertyValueFactory<Customer, String>("customertype"));

        tbvCustomer.setItems(getModelsHandler().getSalesPersonModel().getAllCustomer());
    }
    private void search() {
        String search = txtSearchBar.getText().toLowerCase();

        if(search != null)
            getModelsHandler().getAdminModel().searchUsers(search);
        else if (search == null){
            getModelsHandler().getAdminModel().clearSearch();
        }
    }

    @FXML
    private void handleSearch(KeyEvent keyEvent) {
        //search();
    }
}
