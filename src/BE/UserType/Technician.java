package BE.UserType;

public class Technician extends User {
    //DEFAULT TECHNICIAN usertype = 2

    public Technician(int userId, String passWord, String userName, String name) {
        super(userId, passWord, userName, name, 2);
    }

    public Technician(String passWord, String userName, String name) {
        super(passWord, userName, name, 2);
    }



}
