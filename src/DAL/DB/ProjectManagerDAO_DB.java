package DAL.DB;

import BE.Customer.Customer;
import BE.Project;
import BE.UserType.Technician;
import DAL.Interface.IProjectManagerDAO;

import java.util.List;

public class ProjectManagerDAO_DB implements IProjectManagerDAO {

    @Override
    public List<Project> getAllProject() throws Exception {
        return null;
    }

    @Override
    public Project createProject(Project project, Technician technician) throws Exception {
        return null;
    }

    @Override
    public void updateProject(Project project) throws Exception {

    }

    @Override
    public Customer createCustomer(Customer customer) throws Exception {
        return null;
    }

    @Override
    public void updateCustomer(Customer customer) throws Exception {

    }
}
