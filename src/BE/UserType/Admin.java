package BE.UserType;

public class Admin extends User {
    //DEFAULT ADMIN usertype = 1

    public Admin(int userId, String passWord, String userName, String name) {
        super(userId, passWord, userName, name, 1);
    }

    public Admin(String passWord, String userName, String name) {
        super(passWord, userName, name, 1);
    }


}
