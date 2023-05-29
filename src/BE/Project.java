package BE;

import java.time.LocalDate;
import java.util.List;

public class Project {

    private int projectid;
    private String name;
    private LocalDate date;
    private String layout;
    private String description;
    private String Images;
    private boolean status;
    private List<Integer> techniciansIds;
    private int customerid;

    public Project(int projectid, String name, LocalDate date, String layout, String description, String images, boolean status, List<Integer> techniciansid, int customerid) {
        this.projectid = projectid;
        this.name = name;
        this.date = date;
        this.layout = layout;
        this.description = description;
        this.Images = images;
        this.status = status;
        this.techniciansIds = techniciansid;
        this.customerid = customerid;
    }

    public Project(String name, LocalDate date, String layout, String description, String images, boolean status, List<Integer> techniciansid, int customerid) {
        this.name = name;
        this.date = date;
        this.layout = layout;
        this.description = description;
        this.Images = images;
        this.status = status;
        this.techniciansIds = techniciansid;
        this.customerid = customerid;

    }

    public Project() {

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

    public List<Integer> getTechniciansIds() {
        return techniciansIds;
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setProjectid(int projectid) {
        this.projectid = projectid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTechniciansIds(List<Integer> techniciansIds) {
        this.techniciansIds = techniciansIds;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectid=" + projectid +
                ", name='" + name + '\'' +
                ", layout='" + layout + '\'' +
                ", description='" + description + '\'' +
                ", Images='" + Images + '\'' +
                ", techniciansid=" + techniciansIds +
                ", customerid=" + customerid +
                '}';
    }
}
