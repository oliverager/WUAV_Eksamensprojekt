package BLL;

import BE.Project;
import BE.UserType.Technician;
import BLL.Interfaces.ITechnicianManager;
import DAL.DB.TechnicianDAO_DB;
import DAL.Interface.ITechnicianDAO;

import java.io.IOException;
import java.util.List;

public class TechnicianManager implements ITechnicianManager {

    private ITechnicianDAO databaseAcces;

    public TechnicianManager() throws IOException {
        databaseAcces = new TechnicianDAO_DB();
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
}
