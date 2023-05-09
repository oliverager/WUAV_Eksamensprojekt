package BE.UserType;

public class SalesPerson extends User {
    //DEFAULT SALESPERSON usertype = 4

    public SalesPerson(int userId, String passWord, String userName, String name) {
        super(userId, passWord, userName, name, 4);
    }

    public SalesPerson(String passWord, String userName, String name) {
        super(passWord, userName, name, 4);
    }


}
