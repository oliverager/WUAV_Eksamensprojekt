package DAL.Interface;

import BE.Customer.Customer;
import BE.Project;

import java.util.List;

public interface ISalesPersonDAO {

    List<Project> getAllProject() throws Exception;

    Customer createCustomer(Customer customer) throws Exception;

    void updateCustomer(Customer customer) throws Exception;
}
