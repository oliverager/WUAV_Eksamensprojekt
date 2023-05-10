package BE.UserType;

public class ProjectManager extends User {
    //DEFAULT PROJEKTMANAGER usertype = 3

    public ProjectManager(int userId, String passWord, String userName, String name) {
        super(userId, passWord, userName, name, 3);
    }

    public ProjectManager(String passWord, String userName, String name) {
        super(passWord, userName, name, 3);
    }


}
