package DAL.DB;

import BE.Customer.*;
import BE.Project;
import BE.UserType.User;
import DAL.DatabaseConnector;
import DAL.Interface.ISalesPersonDAO;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalesPersonDAO_DB implements ISalesPersonDAO {

    private DatabaseConnector dbConnector;

    public SalesPersonDAO_DB() throws IOException {
        dbConnector = new DatabaseConnector();
    }

    @Override
    public List<Customer> getAllCustomers() throws Exception {
        String sql = "SELECT * FROM [Customer];";

        try (Connection connection = dbConnector.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            List<Customer> customerList = new ArrayList<>();

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("Id");
                String name = rs.getString("Name");
                String cvr = rs.getString("Cvr");
                String address = rs.getString("Adress");
                int customerType = rs.getInt("CustomerType");


                Customer customer = new Customer(id, name, address, cvr, customerType);
                customerList.add(customer);
            }
            return customerList;
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Could not get customers from database", e);

        }
    }
    @Override
    public Customer getCustomerById(int customerId) throws Exception {
        String sql = "SELECT * FROM [Customer] WHERE Id = ?;";

        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, customerId);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("Id");
                String name = rs.getString("Name");
                String cvr = rs.getString("Cvr");
                String address = rs.getString("Adress");
                int customerType = rs.getInt("CustomerType");

                return new Customer(id, name, address, cvr, customerType);
            } else {
                throw new Exception("Customer with ID " + customerId + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error retrieving customer from database", e);
        }
    }
    @Override
    public CustomerType getCustomerTypeById(int Id) throws Exception {
        String sql = "SELECT * FROM CustomerType WHERE Id = ?";

        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, Id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("Id");
                String name = rs.getString("Type");

                return new CustomerType(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error retrieving customer type from the database", e);
        }

        return null; // Customer type not found
    }



    @Override
    public Customer createCustomer(Customer customer) throws Exception {
        String sql = "INSERT INTO [Customer] (Name, Cvr, Adress, CustomerType) VALUES (?,?,?,?);";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, customer.getName());
            statement.setString(2, customer.getCvrNummer());
            statement.setString(3, customer.getAddress());
            statement.setInt(4, customer.getCustomertype());

            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            int id = 0;

            if(rs.next()) {
                id = rs.getInt(1);
            }

            Customer newCustomer = null;

            if (customer.getClass().getSimpleName() == Business.class.getSimpleName()) {
                newCustomer = new Business(id, customer.getName(), customer.getAddress(), customer.getCvrNummer());
            }
            else if (customer.getClass().getSimpleName() == Private.class.getSimpleName()) {
                newCustomer = new Private(id, customer.getName(), customer.getAddress(), customer.getCvrNummer());
            }
            else if (customer.getClass().getSimpleName() == Government.class.getSimpleName()) {
                newCustomer = new Government(id, customer.getName(), customer.getAddress(), customer.getCvrNummer());
            }
            return newCustomer;
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Failed to create Customer", e);
        }
    }

    @Override
    public void updateCustomer(Customer customer) throws Exception {
        String sql = "UPDATE [Customer] SET Name=?, Address=? WHERE Id=?;";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, customer.getName());
            statement.setString(2, customer.getAddress());
            statement.setInt(3, customer.getCustomerid());

            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Kunne ikke opdatere kunde", e);
        }
    }

    @Override
    public void deleteCustomer(Customer customer) throws Exception {
        String sql = "DELETE FROM [Customer] WHERE Id = ?;";

        try (Connection connection = dbConnector.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setInt(1, customer.getCustomerid());
        statement.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
        throw new Exception("Could not delete customer from database", e);
    }
}


}
