package BLL;

import BE.Customer.Customer;
import BE.Project;
import BE.UserType.Technician;
import BLL.Interfaces.IProjectManagerManager;
import DAL.DB.ProjectManagerDAO_DB;
import DAL.Interface.IProjectManagerDAO;

import java.io.IOException;
import java.util.List;

public class ProjectManagerManager implements IProjectManagerManager {

    private IProjectManagerDAO databaseAcces;

    public ProjectManagerManager() throws IOException {
        databaseAcces = new ProjectManagerDAO_DB();
    }
    @Override
    public List<Project> getAllProject() throws Exception {
        return databaseAcces.getAllProject();
    }

    @Override
    public Project createProject(Project project, Technician technician) throws Exception {
        return databaseAcces.createProject(project, technician);
    }

    @Override
    public void updateProject(Project project) throws Exception {
        databaseAcces.updateProject(project);
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
