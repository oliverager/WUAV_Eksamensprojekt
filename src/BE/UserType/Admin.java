package BE.UserType;

import BE.UserType.User;

public class Admin extends User {
    //DEFAULT ADMIN usertype = 1

    public Admin(String name, String username, String password, int userid) {
        super(name, username, password, 1, userid);
    }
}
