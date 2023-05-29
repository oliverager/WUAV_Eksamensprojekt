package DAL.DB;


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
import java.util.stream.Collectors;

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
                String techniciansIds = rs.getString("Assign_Technicians_Id");
                int customerid = rs.getInt("Customer_Id");

                List<Integer> technicianIdsList = new ArrayList<>();
                if (techniciansIds != null) {
                    String[] technicianIdsArray = techniciansIds.split(",");
                    for (String technicianId : technicianIdsArray) {
                        technicianIdsList.add(Integer.parseInt(technicianId));
                    }
                }

                Project project = new Project(id, name, date, layout, description, images, status, technicianIdsList, customerid);
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
            statement.setString(6, String.join(",", project.getTechniciansIds().stream().map(String::valueOf).collect(Collectors.toList())));
            statement.setInt(7, project.getCustomerid());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            int id = 0;

            if(rs.next()) {
                id = rs.getInt(1);
            }

            Project project1 = new Project(id, project.getName(), project.getDate(), project.getLayout(),
                    project.getDescription(), project.getImages(), project.isStatus(), project.getTechniciansIds(), project.getCustomerid());
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
            statement.setString(6, String.join(",", project.getTechniciansIds().stream().map(String::valueOf).collect(Collectors.toList())));
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

    @Override
    public void assignProjectToUser(User user, Project project) throws Exception {
        String sql = "INSERT INTO WorkingOnProjects (Project_Id, Technicians_Id) VALUES (?, ?);";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, project.getProjectid());
            statement.setInt(2, user.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Failed to create Event_Coordinator relations", e);
        }
    }

    @Override
    public void removeUserFromProject(User user, Project project) throws Exception {
        String sql = "DELETE INTO WorkingOnProjects WHERE Technicians_Id = ? AND Project_Id;";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, project.getProjectid());
            statement.setInt(2, user.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Failed to remove " + user.getClass().getSimpleName(), e);
        }
    }



}
