package GUI.Model;

import BE.Project;
import BE.UserType.User;
import BE.UserType.UserType;
import BLL.AdminManager;
import BLL.Interfaces.IAdminManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdminModel {
    private IAdminManager adminManager;
    private List<User> allUsers;
    private ObservableList<User> userObservableList;
    private ObservableList<User> currentProjectTechnician;
    public ObservableList<User> getCurrentProjectTechnician() {
        return currentProjectTechnician;
    }
    public ObservableList<User> getUserObservableList() {
        return userObservableList;
    }

    public AdminModel() throws Exception {
        adminManager = new AdminManager();
        allUsers = new ArrayList<>();
        userObservableList = FXCollections.observableArrayList();
        currentProjectTechnician = FXCollections.observableArrayList();
    }
    public void createUser(User user) throws Exception {
        User newUser = adminManager.createUser(user);
        userObservableList.add(newUser);
        allUsers.add(newUser);
    }
    public void deleteUser(User user) throws Exception{
        userObservableList.remove(user);
        allUsers.remove(user);
        adminManager.deleteUser(user);
    }
    public List<User> getAllUsers() {
        return allUsers;
    }

    public void retreiveAllUsers() throws Exception {
        List<User> techniciansList = adminManager.getAllUsers();
        userObservableList.clear();
        allUsers.clear();
        userObservableList.addAll(techniciansList);
        allUsers.addAll(userObservableList);
    }

    public void updateUser(User updatedUser, User oldUser) throws Exception{
        userObservableList.remove(oldUser);
        allUsers.remove(oldUser);
        adminManager.updateUser(updatedUser);
        userObservableList.add(updatedUser);
        allUsers.add(updatedUser);
    }

    public boolean checkUserName(String userName) throws Exception{
        return adminManager.checkUserName(userName);
    }

    public void searchUsers (String query) {
        if (allUsers.isEmpty())
            allUsers.addAll(userObservableList);
        else
            userObservableList.clear();

        for (User m: allUsers)
        {
            boolean nameContains = m.getName().toLowerCase().contains(query);


            boolean addUser = nameContains ;

            if (addUser) userObservableList.add(m);
        }
    }

    public User getLocalUserFromId(int id){
        for (User u: allUsers){
            if (u.getUserId() == id)
                return u;
        }
        return null;
    }
    public void clearSearch() {
        userObservableList.clear();
        userObservableList.addAll(allUsers);
    }

    public List<UserType> getAllUserTypes() throws Exception{
        return adminManager.getAllUserTypes();
    }
    public void deleteProject(Project project) throws Exception {
        adminManager.deleteProjectRelations(project);
    }
    public List<User> getUsersWorkingOnProject(Project project) throws Exception{
        List<User> allUsersOnProject = new ArrayList<>();
        List<Integer> allUsersId = adminManager.getUsersWorkingOnProject(project);
        for (Integer i:allUsersId){
            allUsersOnProject.add(getLocalUserFromId(i));
        }
        return allUsersOnProject;
    }
}
