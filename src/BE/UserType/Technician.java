package BE.UserType;

public class Technician extends User {
    //DEFAULT TECHNICIAN usertype = 2

    public Technician(String name, String username, String password, int userid) {
        super(name, username, password, 2, userid);
    }
}
