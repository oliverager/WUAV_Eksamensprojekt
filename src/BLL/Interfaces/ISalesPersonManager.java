package BLL.Interfaces;

import BE.Customer.Customer;
import BE.Project;
import BE.UserType.Technician;

import java.util.List;

public interface ISalesPersonManager {

    List<Customer> getAllCustomers() throws Exception;

    Customer createCustomer(Customer customer) throws Exception;

    void updateCustomer(Customer customer) throws Exception;

    void deleteCustomer(Customer customer) throws Exception;
}
