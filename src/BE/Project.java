package BE;

import javafx.scene.image.Image;

import java.sql.Date;
import java.time.LocalDate;

public class Project {

    private int projectid;
    private String name;
    private LocalDate date;
    private String layout;
    private String description;
    private String Images;
    private boolean status;
    private int techniciansid;
    private int customerid;

    public Project(int projectid, String name, LocalDate date, String layout, String description, String images, boolean status, int techniciansid, int customerid) {
        this.projectid = projectid;
        this.name = name;
        this.date = date;
        this.layout = layout;
        this.description = description;
        this.Images = images;
        this.status = status;
        this.techniciansid = techniciansid;
        this.customerid = customerid;
    }

    public Project(String name, LocalDate date, String layout, String description, String images, boolean status, int techniciansid, int customerid) {
        this.name = name;
        this.date = date;
        this.layout = layout;
        this.description = description;
        this.Images = images;
        this.status = status;
        this.techniciansid = techniciansid;
        this.customerid = customerid;

    }

    public int getProjectid() {
        return projectid;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getLayout() {
        return layout;
    }

    public String getDescription() {
        return description;
    }

    public String getImages() {
        return Images;
    }

    public boolean isStatus() {
        return status;
    }

    public int getTechniciansid() {
        return techniciansid;
    }

    public int getCustomerid() {
        return customerid;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectid=" + projectid +
                ", name='" + name + '\'' +
                ", layout='" + layout + '\'' +
                ", description='" + description + '\'' +
                ", Images='" + Images + '\'' +
                ", techniciansid=" + techniciansid +
                ", customerid=" + customerid +
                '}';
    }
}
