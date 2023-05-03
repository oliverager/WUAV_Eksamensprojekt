package DAL.DB;

import BE.Customer.Customer;
import BE.Project;
import BE.UserType.Technician;
import DAL.DatabaseConnector;
import DAL.Interface.IProjectManagerDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        return null;
    }

    @Override
    public void updateProject(Project project) throws Exception {

    }

    @Override
    public Customer createCustomer(Customer customer) throws Exception {
        return null;
    }

    @Override
    public void updateCustomer(Customer customer) throws Exception {

    }
}
