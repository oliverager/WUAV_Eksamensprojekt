package BLL.Interfaces;

import BE.Project;
import BE.UserType.User;
import BE.UserType.UserType;

import java.util.List;

public interface IAdminManager {

    User createUser(User user) throws Exception;

    void deleteUser(User user) throws Exception;

    List<User> getAllUsers() throws Exception;

    List<UserType> getAllUserTypes() throws Exception;

    void updateUser(User user) throws Exception;

    boolean checkUserName(User user) throws Exception;

    List<Integer> getUsersWorkingOnProject(Project project) throws Exception;

    void deleteProjectRelations(Project project) throws Exception;

    void deleteFromWorkingOnProject(User user, Project project) throws Exception;

    void assignProjectToUser(User user, Project project) throws Exception;

}
