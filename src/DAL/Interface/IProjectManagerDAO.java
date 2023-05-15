package DAL.Interface;

import BE.Customer.Customer;
import BE.Project;
import BE.UserType.Technician;

import java.util.List;

public interface IProjectManagerDAO {

    List<Project> getAllProject() throws Exception;

    Project createProject(Project project, Technician technician) throws Exception;

    void updateProject(Project project) throws Exception;

    void updateStatus(Project project) throws Exception;
}
