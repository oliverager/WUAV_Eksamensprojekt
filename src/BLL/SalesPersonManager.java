package BLL;

import BE.Customer.Customer;
import BE.Project;
import BLL.Interfaces.ISalesPersonManager;
import DAL.DB.SalesPersonDAO_DB;
import DAL.Interface.ISalesPersonDAO;

import java.util.List;

public class SalesPersonManager implements ISalesPersonManager {

    private ISalesPersonDAO databaseAcces;

    public SalesPersonManager() throws Exception{
        databaseAcces = new SalesPersonDAO_DB();
    }

    @Override
    public List<Project> getAllProject() throws Exception {
        return databaseAcces.getAllProject();
    }

    @Override
    public Customer createCustomer(Customer customer) throws Exception {
        return databaseAcces.createCustomer(customer);
    }

    @Override
    public void updateCustomer(Customer customer) throws Exception {
        databaseAcces.updateCustomer(customer);
    }
}
