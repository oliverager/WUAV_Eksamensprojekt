package DAL.DB;

import BE.Customer.B2B;
import BE.Customer.B2C;
import BE.Customer.B2G;
import BE.Customer.Customer;
import BE.Project;
import BE.UserType.Technician;
import BE.UserType.User;
import DAL.DatabaseConnector;
import DAL.Interface.IProjectManagerDAO;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProjectManagerDAO_DB implements IProjectManagerDAO {

    private DatabaseConnector dbConnector;

    public ProjectManagerDAO_DB() throws IOException {
        dbConnector = new DatabaseConnector();
    }

    @Override
    public List<Project> getAllProject() throws Exception {
        String sql = "SELECT * FROM Project";
        try (Connection conn = dbConnector.getConnection()) {

            PreparedStatement statement = conn.prepareStatement(sql);
            List<Project> projects = new ArrayList<>();

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {

                int id = rs.getInt("Id");
                String name = rs.getString("Name");
                LocalDate date = rs.getDate("Date").toLocalDate();
                String layout = rs.getString("Layout");
                String description = rs.getString("Description");
                String images = rs.getString("Image");
                int techniciansid = rs.getInt("Assign_Technicians_Id");
                int customerid = rs.getInt("Customer_Id");

                Project project = new Project(id, name, date, layout, description, images, techniciansid, customerid);
                projects.add(project);
            }
            return projects;
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("failed to found current projects", e);
        }
    }

    @Override
    public Project createProject(Project project, Technician technician) throws Exception {
        String sql = "INSERT INTO Project(Name,Date,Layout,Description,Image,Assign_Technicians_Id,Customer_Id) VALUES(?,?,?,?,?,?,?);";
        try (Connection connection = dbConnector.getConnection(); PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, project.getName());
            statement.setDate(2, (java.sql.Date.valueOf(project.getDate())));
            statement.setString(3, project.getLayout());
            statement.setString(4, project.getDescription());
            statement.setString(5, project.getImages());
            statement.setInt(6, project.getTechniciansid());
            statement.setInt(7, project.getCustomerid());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            int id = 0;
            if(rs.next()) {
                id = rs.getInt(1);
            }

            Project project1 = new Project(id, project.getName(), project.getDate(), project.getLayout(),
                    project.getDescription(), project.getImages(), project.getTechniciansid(), project.getCustomerid());
            return project1;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateProject(Project project) throws Exception {
        String sql = "UPDATE [Event] SET Name=?, Date=?, Layout=?, DESCRIPTION=?, IMAGE=?, Assign_Technicians_Id=?, Customer_Id=? WHERE Id=?;";
        try (Connection connection = dbConnector.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1, project.getName());
            statement.setDate(2, (java.sql.Date.valueOf(project.getDate())));
            statement.setString(3, project.getLayout());
            statement.setString(4, project.getDescription());
            statement.setString(5, project.getImages());
            statement.setInt(6, project.getTechniciansid());
            statement.setInt(7, project.getCustomerid());
            statement.setInt(8, project.getProjectid());

            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("failed to update event", e);
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
            throw new Exception("Failed to update customer", e);
        }
    }
}
