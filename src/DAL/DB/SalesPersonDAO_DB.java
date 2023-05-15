package DAL.DB;

import BE.Customer.B2B;
import BE.Customer.B2C;
import BE.Customer.B2G;
import BE.Customer.Customer;
import BE.Project;
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
        String sql = "SELECT * FROM [User];";

        try (Connection connection = dbConnector.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            List<Customer> customerList = new ArrayList<>();

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("Id");
                String name = rs.getString("Name");
                int cvr = rs.getInt("Cvr");
                String adress = rs.getString("Adress");
                int customerType = rs.getInt("CustomerType");


                Customer customer = new Customer(id, name, adress, cvr, customerType);
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
    public Customer createCustomer(Customer customer) throws Exception {
        String sql = "INSERT INTO [Customer] (Name, Cvr, Adress, CustomerType) VALUES (?,?,?,?);";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, customer.getName());
            statement.setDouble(2, customer.getCvrNummer());
            statement.setString(3, customer.getAddress());
            statement.setInt(4, customer.getCustomertype());

            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            int id = 0;

            if(rs.next()) {
                id = rs.getInt(1);
            }

            Customer newCustomer = null;

            if (customer.getClass().getSimpleName() == B2B.class.getSimpleName()) {
                newCustomer = new B2B(customer.getCustomerid(), customer.getName(), customer.getAddress(), customer.getCvrNummer());
            }
            else if (customer.getClass().getSimpleName() == B2C.class.getSimpleName()) {
                newCustomer = new B2C(customer.getCustomerid(), customer.getName(), customer.getAddress(), customer.getCvrNummer());
            }
            else if (customer.getClass().getSimpleName() == B2G.class.getSimpleName()) {
                newCustomer = new B2G(customer.getCustomerid(), customer.getName(), customer.getAddress(), customer.getCvrNummer());
            }
            return newCustomer;
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Kunne ikke oprette kunde", e);
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
}
