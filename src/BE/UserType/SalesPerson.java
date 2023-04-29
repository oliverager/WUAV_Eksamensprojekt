package BE.UserType;

public class SalesPerson extends User{
    //DEFAULT SALESPERSON usertype = 4

    public SalesPerson(String name, String username, String password, int userid) {
        super(name, username, password, 4, userid);
    }
}
