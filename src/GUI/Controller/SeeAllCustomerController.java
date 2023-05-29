package GUI.Controller;

import BE.Customer.Customer;
import BE.Customer.CustomerType;
import BE.UserType.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
        tbcCustomerType.setCellValueFactory(cellData -> {
            StringProperty customerTypeName = new SimpleStringProperty();
            int customerTypeId = cellData.getValue().getCustomertype();

            try {
                CustomerType customerType = getModelsHandler().getSalesPersonModel().getCustomerTypeById(customerTypeId);
                if (customerType != null) {
                    customerTypeName.set(customerType.getUserTypeName());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return customerTypeName;
        });
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
        lastSelectedItemType = "Customer";
        if (mouseEvent.getButton() == MouseButton.PRIMARY && mouseEvent.getClickCount() == 2) {
            checkSelectedItemType();
        }
    }

    private MainController checkSelectedItemType() throws Exception {
        if (tbvCustomer.getSelectionModel().getSelectedItem() != null && lastSelectedItemType.equals("Customer")) {
            mainController.openCreateCustomerView();
        }
        return mainController;
    }
}
