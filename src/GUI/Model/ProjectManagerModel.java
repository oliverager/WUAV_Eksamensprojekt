package GUI.Model;

import BE.Project;
import BE.UserType.Technician;
import BE.UserType.User;
import BLL.Interfaces.IProjectManagerManager;
import BLL.ProjectManagerManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class ProjectManagerModel {

    private IProjectManagerManager pmm;
    private ObservableList<Project> projectObservableList;
    private ObservableList<User> currentProjectTechnician;

    public ProjectManagerModel() throws Exception {
        pmm = new ProjectManagerManager();
        projectObservableList = FXCollections.observableArrayList();
    }

    public ObservableList<User> getCurrentProjectTechnician() {
        return currentProjectTechnician;
    }

    public ObservableList<Project> getProjectObservableList() {
        return projectObservableList;
    }
    public void createProject(Project project, Technician technician) throws Exception {
        projectObservableList.add(pmm.createProject(project, technician));
    }
    public void getAllProject() throws Exception {
        List<Project> projectList = pmm.getAllProject();
        projectList.addAll(projectList);
    }
    public void updateProject(Project updatedProject, Project oldProject) throws Exception {
        projectObservableList.remove(oldProject);
        pmm.updateProject(updatedProject);
        projectObservableList.add(updatedProject);
    }
    public void removeProjectFromLocal(Project project) {
        projectObservableList.remove(project);
    }
}
