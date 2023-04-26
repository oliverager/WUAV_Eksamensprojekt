package BE;

public class Project {

    private String name;
    private String layout;
    private String description;
    private String Images;
    private int projectid;
    private int customerid;
    private int techniciansid;

    public Project(String name, String layout, String description, String images, int projectid, int customerid, int techniciansid) {
        this.name = name;
        this.layout = layout;
        this.description = description;
        Images = images;
        this.projectid = projectid;
        this.customerid = customerid;
        this.techniciansid = techniciansid;
    }

    public String getName() {
        return name;
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

    public int getProjectid() {
        return projectid;
    }

    public int getCustomerid() {
        return customerid;
    }

    public int getTechniciansid() {
        return techniciansid;
    }

    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                ", layout='" + layout + '\'' +
                ", description='" + description + '\'' +
                ", Images='" + Images + '\'' +
                ", projectid=" + projectid +
                ", customerid=" + customerid +
                ", techniciansid=" + techniciansid +
                '}';
    }
}
