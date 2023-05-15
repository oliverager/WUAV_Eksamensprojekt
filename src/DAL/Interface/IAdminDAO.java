package DAL.Interface;

import BE.Project;
import BE.UserType.User;
import BE.UserType.UserType;

import java.util.List;

public interface IAdminDAO {

    User createUser(User user) throws Exception;

    void deleteUser(User user) throws Exception;

    List<User> getAllUsers() throws Exception;

    List<UserType> getAllUserTypes() throws Exception;

    void updateUser(User user) throws Exception;

    boolean checkUserName(String userName) throws Exception;

    void deleteProjectRelations(Project project) throws Exception;
}
