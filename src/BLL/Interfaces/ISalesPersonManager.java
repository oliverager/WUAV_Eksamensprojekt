package BLL.Interfaces;

import BE.Customer.Customer;
import BE.Customer.CustomerType;
import BE.Project;
import BE.UserType.Technician;
import BE.UserType.UserType;

import java.util.List;

public interface ISalesPersonManager {

    List<Customer> getAllCustomers() throws Exception;

    Customer getCustomerById(int customerId) throws Exception;

    CustomerType getCustomerTypeById(int typeId) throws Exception;

    Customer createCustomer(Customer customer) throws Exception;

    void updateCustomer(Customer customer) throws Exception;

    void deleteCustomer(Customer customer) throws Exception;
}
