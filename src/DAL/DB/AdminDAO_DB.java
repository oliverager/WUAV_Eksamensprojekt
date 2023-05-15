package DAL.DB;

import BE.Project;
import BE.UserType.*;
import DAL.DatabaseConnector;
import DAL.Interface.IAdminDAO;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO_DB implements IAdminDAO {

    private DatabaseConnector dbConnector;

    public AdminDAO_DB() throws IOException {
        dbConnector = new DatabaseConnector();
    }
    @Override
    public User createUser(User user) throws Exception {
        String sql = "INSERT INTO [User] (PassWord, UserName, Name, UserType) VALUES (?,?,?,?);";
        try (Connection connection = dbConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, user.getPassWord());
            statement.setString(2, user.getUserName());
            statement.setString(3, user.getName());
            statement.setInt(4, user.getUserType());

            statement.executeUpdate();

            // Get the generated Id from the DB
            ResultSet rs = statement.getGeneratedKeys();
            int id = 0;

            if (rs.next()) {
                id = rs.getInt(1);
            }

            User newUser = null;

            if (user.getClass().getSimpleName() == Admin.class.getSimpleName()) {
                newUser = new Admin(id, user.getPassWord(), user.getUserName(), user.getName());
            }
            else if (user.getClass().getSimpleName() == Technician.class.getSimpleName()) {
                newUser = new Technician(id, user.getPassWord(), user.getUserName(), user.getName());
            }
            else if (user.getClass().getSimpleName() == ProjectManager.class.getSimpleName()) {
                newUser = new ProjectManager(id, user.getPassWord(), user.getUserName(), user.getName());
            }
            else if (user.getClass().getSimpleName() == SalesPerson.class.getSimpleName()) {
                newUser = new SalesPerson(id, user.getPassWord(), user.getUserName(), user.getName());
            }
            return newUser;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Failed to create User", e);
        }
    }

    @Override
    public void deleteUser(User user) throws Exception {
        deleteUserRelation(user);

        String sql = "DELETE FROM [User] WHERE Id = ?;";
        try (Connection connection = dbConnector.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, user.getUserId());

            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Failed to remove " + user.getClass().getSimpleName(), e);
        }
    }
    private void deleteUserRelation(User user) throws Exception {
        String sql = "DELETE FROM WorkingOnProject WHERE Technicians_Id = ?;";
        try (Connection connection = dbConnector.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, user.getUserId());

            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Failed to remove " + user.getClass().getSimpleName(), e);
        }
    }

    @Override
    public List<User> getAllUsers() throws Exception {
        String sql = "SELECT * FROM [User];";

        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            List<User> userList = new ArrayList<>();

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("Id");
                String passWord = resultSet.getString("PassWord");
                String userName = resultSet.getString("UserName");
                String name = resultSet.getString("Name");
                int userTypes = resultSet.getInt("UserType");

                if (userTypes == 1) {
                    userList.add(new Admin(id, passWord, userName, name));
                } else if (userTypes == 2) {
                    userList.add(new Technician(id, passWord, userName, name));
                } else if (userTypes == 3) {
                    userList.add(new ProjectManager(id, passWord, userName, name));
                } else if (userTypes == 4) {
                    userList.add(new SalesPerson(id, passWord, userName, name));
                }
            }
            for (User u : userList) {
                if (u.getName().equals("NotAssigned") && u.getUserId() == 1) {
                    userList.remove(u);
                    return userList;
                }
            }
            return userList;
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Failed to retrieve Users", e);
        }
    }

    @Override
    public List<UserType> getAllUserTypes() throws Exception {
        String sql = "SELECT * FROM UserType;";

        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            List<UserType> userTypeList = new ArrayList<>();

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("Id");
                String type = rs.getString("UserType");

                userTypeList.add(new UserType(id, type));
            }
            return userTypeList;
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Failed to retrieve UserTypes", e);
        }
    }

    @Override
    public void updateUser(User user) throws Exception {
        String sql = "UPDATE [User] SET PassWord = ?, Mail = ?, Name = ? WHERE Id = ?;";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, user.getPassWord());
            statement.setString(2, user.getUserName());
            statement.setString(3, user.getName());
            statement.setInt(4, user.getUserId());
            //Run the specified SQL Statement
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Failed to update playlist", e);
        }
    }

    @Override
    public boolean checkUserName(String userName) throws Exception {
        String sql = "SELECT UserName FROM [User] WHERE UserName = ?;";

        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, userName);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String userName1 = resultSet.getString("UserName");
                if (userName1.equals(userName))
                    return false;
            }
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Failed to check userName", e);
        }
    }
    private List<Integer> getDokumentationIdFromProjectId(Project project) throws Exception {
        String sql = "SELECT [Dokumentation_Id] FROM [DokumentationToProjects] WHERE Project_Id=?";
        List<Integer> dokumentationIdList = new ArrayList<>();

        try (Connection connection = dbConnector.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, project.getProjectid());

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int dokumentationId = rs.getInt("");
                dokumentationIdList.add(dokumentationId);
            }
            return dokumentationIdList;
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Failed to remove " + project.getClass().getSimpleName(), e);
        }
    }


    @Override
    public void deleteProjectRelations(Project project) throws Exception {
        String sql = "DELETE FROM [Project] WHERE Id=?;";
        String sql_Dokumentation = "DELETE FROM [Dokumentation] WHERE Id=?;";
        String sql_WorkingOn = "DELETE FROM [WorkingOnProjects] WHERE Project_Id=?;";

        List<Integer> dokumentationIdList = getDokumentationIdFromProjectId(project);
        try (Connection connection = dbConnector.getConnection();) {
            connection.setAutoCommit(false);

            PreparedStatement workingOnTable = connection.prepareStatement(sql_WorkingOn);
            workingOnTable.setInt(1, project.getProjectid());
            workingOnTable.addBatch();

            PreparedStatement dokumentationTable = connection.prepareStatement(sql_Dokumentation);
            for (int i : dokumentationIdList) {
                dokumentationTable.setInt(1, i);
                dokumentationTable.addBatch();
            }
            dokumentationTable.executeBatch();

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, project.getProjectid());
            statement.executeUpdate();

            connection.commit();
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Failed to remove " + project.getClass().getSimpleName(), e);
        }
    }
}
