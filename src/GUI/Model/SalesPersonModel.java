package GUI.Model;

import BE.Customer.Customer;
import BE.UserType.SalesPerson;
import BLL.Interfaces.ISalesPersonManager;
import BLL.SalesPersonManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class SalesPersonModel {
    private ISalesPersonManager salesPersonManager;
    private List<Customer> allCustomer;
    private ObservableList<Customer> customerObservableList;
    public ObservableList<Customer> getAllCustomer() {
        return customerObservableList;
    }

    public SalesPersonModel() throws Exception {
        salesPersonManager = new SalesPersonManager();
        allCustomer = new ArrayList<>();
        customerObservableList = FXCollections.observableArrayList();
        //customerObservableList.addAll(salesPersonManager.getAllCustomers());
    }
}
