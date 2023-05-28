package GUI.Model;

import BE.Project;
import BE.UserType.Technician;
import BE.UserType.User;
import BLL.Interfaces.IProjectManagerManager;
import BLL.ProjectManagerManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class ProjectManagerModel {

    private IProjectManagerManager projectManagerManager;
    private List<Project> allProjects;
    private ObservableList<Project> projectObservableList;
    private ObservableList<User> currentProjectTechnician;


    public ProjectManagerModel() throws Exception {
        projectManagerManager = new ProjectManagerManager();
        allProjects = new ArrayList<>();
        projectObservableList = FXCollections.observableArrayList();
        projectObservableList.addAll(projectManagerManager.getAllProject());
    }

    public ObservableList<User> getCurrentProjectTechnician() {
        return currentProjectTechnician;
    }

    public ObservableList<Project> getProjectObservableList() {
        return projectObservableList;
    }
    public void createProject(Project project, Technician technician) throws Exception {
        Project newProject = projectManagerManager.createProject(project, technician);
        projectObservableList.add(newProject);
        allProjects.add(newProject);
    }
    public void getAllProject() throws Exception {
        List<Project> projectList = projectManagerManager.getAllProject();
        projectObservableList.addAll(projectList);
    }
    public void updateProject(Project updatedProject, Project oldProject) throws Exception {
        projectObservableList.remove(oldProject);
        projectManagerManager.updateProject(updatedProject);
        projectObservableList.add(updatedProject);
    }

    public void updateStatus(Project updatedProject, Project oldProject) throws Exception {
        projectObservableList.remove(oldProject);
        projectManagerManager.updateStatus(updatedProject);
        projectObservableList.add(updatedProject);
    }

    public void assignProjectToUser(User user, Project project) throws Exception {
        projectManagerManager.assignProjectToUser(user, project);
        currentProjectTechnician.add(user);
    }

    public void removeUserFromProject(User user, Project project) throws Exception {
        projectManagerManager.removeUserFromProject(user, project);
        currentProjectTechnician.remove(user);

    }

    public void searchProjects(String query) {
        if (allProjects.isEmpty())
            allProjects.addAll(projectObservableList);
        else projectObservableList.clear();

        for (Project p : allProjects) {

            boolean nameContains = p.getName().toLowerCase().contains(query);

            boolean addProject = nameContains;

            if (addProject) projectObservableList.add(p);
        }
    }
    public void clearSearch() {
        projectObservableList.clear();
        projectObservableList.addAll(allProjects);
    }

    public void removeProjectFromLocal(Project project) {
        projectObservableList.remove(project);
    }
}
