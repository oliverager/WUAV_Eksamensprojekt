package DAL.Interface;

import BE.Customer.Customer;
import BE.Customer.CustomerType;
import BE.Project;
import BE.UserType.User;

import java.util.List;

public interface ISalesPersonDAO {

    List<Customer> getAllCustomers() throws Exception;

    Customer getCustomerById(int customerId) throws Exception;

    CustomerType getCustomerTypeById(int typeId) throws Exception;

    Customer createCustomer(Customer customer) throws Exception;

    void updateCustomer(Customer customer) throws Exception;

    void deleteCustomer(Customer customer) throws Exception;

}
