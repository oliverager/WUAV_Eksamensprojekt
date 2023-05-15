package DAL.DB;


import BE.Project;
import BE.UserType.Technician;
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
        List<Project> allProjects = new ArrayList<>();

        try (Connection conn = dbConnector.getConnection();
            Statement statement = conn.createStatement())
        {
            String sql = "SELECT * FROM Project";

            ResultSet rs = statement.executeQuery(sql);
            while (rs.next())
            {
                int id = rs.getInt("Id");
                String name = rs.getString("Name");
                LocalDate date = rs.getDate("Date").toLocalDate();
                String layout = rs.getString("Layout");
                String description = rs.getString("Description");
                String images = rs.getString("Image");
                boolean status = rs.getBoolean("Status");
                int techniciansid = rs.getInt("Assign_Technicians_Id");
                int customerid = rs.getInt("Customer_Id");

                Project project = new Project(id, name, date, layout, description, images, status, techniciansid, customerid);
                allProjects.add(project);
            }
            return allProjects;
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Could not get projects from database", e);
        }
    }

    @Override
    public Project createProject(Project project, Technician technician) throws Exception {
        String sql = "INSERT INTO Project(Name,Date,Layout,Description,Image,Assign_Technicians_Id,Customer_Id) VALUES(?,?,?,?,?,?,?);";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

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
                    project.getDescription(), project.getImages(), project.isStatus(), project.getTechniciansid(), project.getCustomerid());
            return project1;
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Kunne ikke oprette projektet", e);
        }
    }

    @Override
    public void updateProject(Project project) throws Exception {
        String sql = "UPDATE [Project] SET Name=?, Date=?, Layout=?, DESCRIPTION=?, IMAGE=?, Assign_Technicians_Id=?, Customer_Id=? WHERE Id=?;";
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
            throw new Exception("Kunne ikke opdatere projektet", e);
        }
    }
    @Override
    public void updateStatus(Project project) throws Exception {
        String sql = "UPDATE [Project] SET Status=? WHERE Id=?;";
        try (Connection connection = dbConnector.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setBoolean(1, project.isStatus());

            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Kunne ikke opdatere projektet status", e);
        }
    }




}
