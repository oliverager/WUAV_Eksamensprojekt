package BLL.Interfaces;

import BE.Customer.Customer;
import BE.Project;
import BE.UserType.Technician;
import BE.UserType.User;

import java.util.List;

public interface IProjectManagerManager {

    List<Project> getAllProject() throws Exception;

    Project createProject(Project project, Technician technician) throws Exception;

    void updateProject(Project project) throws Exception;

    void updateStatus(Project project) throws Exception;

    void assignProjectToUser(User user, Project project) throws Exception;

    void removeUserFromProject(User user, Project project) throws Exception;
}
