package BE.UserType;

public class ProjectManager extends User{
    //DEFAULT PROJEKTMANAGER usertype = 3

    public ProjectManager(String name, String username, String password, int usertype, int userid) {
        super(name, username, password, 3, userid);
    }
}
