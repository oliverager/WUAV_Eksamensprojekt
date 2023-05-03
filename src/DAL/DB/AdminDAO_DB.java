package DAL.DB;

import BE.Project;
import BE.UserType.User;
import BE.UserType.UserType;
import DAL.Interface.IAdminDAO;

import java.util.List;

public class AdminDAO_DB implements IAdminDAO {
    @Override
    public User createUser(User user) throws Exception {
        return null;
    }

    @Override
    public void deleteUser(User user) throws Exception {

    }

    @Override
    public List<User> getAllUsers() throws Exception {
        return null;
    }

    @Override
    public List<UserType> getAllUserTypes() throws Exception {
        return null;
    }

    @Override
    public void updateUser(User user) throws Exception {

    }

    @Override
    public boolean checkUserName(User user) throws Exception {
        return false;
    }

    @Override
    public List<Integer> getUsersWorkingOnProject(Project project) throws Exception {
        return null;
    }

    @Override
    public void deleteProjectRelations(Project project) throws Exception {

    }
}
