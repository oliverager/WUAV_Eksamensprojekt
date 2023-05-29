package BLL;

import BE.Customer.Customer;
import BE.Customer.CustomerType;
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
    public List<Customer> getAllCustomers() throws Exception {
        return databaseAcces.getAllCustomers();
    }
    @Override
    public Customer getCustomerById(int customerId) throws Exception {
        return databaseAcces.getCustomerById(customerId);
    }

    @Override
    public CustomerType getCustomerTypeById(int typeId) throws Exception {
        return databaseAcces.getCustomerTypeById(typeId);
    }

    @Override
    public Customer createCustomer(Customer customer) throws Exception {
        return databaseAcces.createCustomer(customer);
    }

    @Override
    public void updateCustomer(Customer customer) throws Exception {
        databaseAcces.updateCustomer(customer);
    }

    @Override
    public void deleteCustomer(Customer customer) throws Exception {
        databaseAcces.deleteCustomer(customer);
    }
}
