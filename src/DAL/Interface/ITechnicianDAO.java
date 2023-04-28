package DAL.Interface;

import BE.Project;
import BE.UserType.Technician;

import java.util.List;

public interface ITechnicianDAO {

    List<Project> getAllProject() throws Exception;

    Project createProject(Project project, Technician technician) throws Exception;

    void updateProject(Project project) throws Exception;
}
