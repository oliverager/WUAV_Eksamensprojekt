package DAL.Interface;

import BE.Customer.Customer;
import BE.Project;

import java.util.List;

public interface ISalesPersonDAO {

    List<Customer> getAllCustomers() throws Exception;

    Customer createCustomer(Customer customer) throws Exception;

    void updateCustomer(Customer customer) throws Exception;

    void deleteCustomer(Customer customer) throws Exception;
}
