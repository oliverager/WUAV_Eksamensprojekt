package GUI.Model;

import BE.Customer.Customer;
import BE.Project;
import BE.UserType.SalesPerson;
import BLL.Interfaces.ISalesPersonManager;
import BLL.SalesPersonManager;
import GUI.Util.PDFGenerator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
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
        customerObservableList.addAll(salesPersonManager.getAllCustomers());
    }

    public void createPdf(Project project, ObservableList<File> files) throws Exception {
        PDFGenerator pdfGenerator = new PDFGenerator(project, files);
        pdfGenerator.createDocument();
    }

    public List<Customer> getAllCustomers() throws Exception {
        return allCustomer;
    }

    public void createCustomer(Customer customer) throws Exception {
        Customer newCustomer = salesPersonManager.createCustomer(customer);
        customerObservableList.add(newCustomer);
        allCustomer.add(newCustomer);
    }

    public void updateCustomer(Customer updatedCustomer, Customer oldCustomer) throws Exception {
        customerObservableList.remove(oldCustomer);
        allCustomer.remove(oldCustomer);
        salesPersonManager.updateCustomer(updatedCustomer);
        customerObservableList.add(updatedCustomer);
        allCustomer.add(updatedCustomer);
    }
    public void deleteCustomer(Customer customer) throws Exception {
        customerObservableList.remove(customer);
        allCustomer.remove(customer);
        salesPersonManager.deleteCustomer(customer);
    }

    public void searchCustomers(String query) {
        if (allCustomer.isEmpty())
            allCustomer.addAll(customerObservableList);
        else customerObservableList.clear();

        for (Customer c : allCustomer) {

            boolean nameContains = c.getName().toLowerCase().contains(query);

            boolean addCustomers = nameContains;

            if (addCustomers) customerObservableList.add(c);
        }
    }
    public void clearSearch() {
        customerObservableList.clear();
        customerObservableList.addAll(allCustomer);
    }
}
