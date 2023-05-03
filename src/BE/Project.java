package BE;

import java.sql.Date;
import java.time.LocalDate;

public class Project {

    private int projectid;
    private String name;
    private LocalDate date;
    private String layout;
    private String description;
    private String Images;
    private int techniciansid;
    private int customerid;

    public Project(int projectid, String name, LocalDate date, String layout, String description, String images, int techniciansid, int customerid) {
        this.projectid = projectid;
        this.name = name;
        this.date = date;
        this.layout = layout;
        this.description = description;
        Images = images;
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
