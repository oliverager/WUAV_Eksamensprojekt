package DAL.DB;

import BE.UserType.User;
import DAL.DatabaseConnector;
import DAL.Interface.ILoginDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO_DB implements ILoginDAO {

    public DatabaseConnector dbConnector;

    public LoginDAO_DB() throws IOException {
        dbConnector = new DatabaseConnector();
    }

    @Override
    public User LoginUser(String UserName, String Password) throws Exception {
        String sql = "SELECT * FROM [User] Where UserName = ?";
        User user = null;
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, UserName);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                int id = resultSet.getInt("Id");
                String PassWord = resultSet.getString("PassWord");
                String userName = resultSet.getString("UserName");
                String name = resultSet.getString("Name");
                int userTypes = resultSet.getInt("UserType");

                user = new User(id, PassWord, userName, name, userTypes);
            }
            return user;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new Exception("Failed to login");
        }
    }
}
