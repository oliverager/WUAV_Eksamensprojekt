package BE.Customer;

public class CustomerType {
    private int usertype;
    private String usertypeName;

    public CustomerType(int usertype, String usertypeName) {
        this.usertype = usertype;
        this.usertypeName = usertypeName;
    }

    public int getUsertype() {
        return usertype;
    }

    public String getUsertypeName() {
        return usertypeName;
    }
}
