package GUI.Controller;

import BE.Customer.Customer;
import BE.UserType.User;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

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
    private String lastSelectedItemType;

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
            getModelsHandler().getSalesPersonModel().searchCustomers(search);
        else if (search == null){
            getModelsHandler().getSalesPersonModel().clearSearch();
        }
    }

    public Customer getSelectedCustomer() {
        Customer customer = tbvCustomer.getSelectionModel().getSelectedItem();
        if (customer != null) {
            return customer;
        } else
            return null;
    }

    @FXML
    private void handleSearch(KeyEvent keyEvent) {
        search();
    }

    public void clickOnProject(MouseEvent mouseEvent) throws Exception {
        if (mouseEvent.getButton() == MouseButton.PRIMARY && mouseEvent.getClickCount() == 2) {
            lastSelectedItemType = "Customer";
            checkSelectedItemType();
        }
    }

    private void checkSelectedItemType() throws Exception {
        if (tbvCustomer.getSelectionModel().getSelectedItem() != null && lastSelectedItemType.equals("Project")) {
            setMainController(mainController.openCreateCustomerView());
        }
    }
}
