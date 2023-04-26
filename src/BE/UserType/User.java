package BE.UserType;

public class User {

    private String name;
    private String username;
    private String password;
    private int usertype;
    private int userid;

    public User(String name, String username, String password, int usertype, int userid) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.usertype = usertype;
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getUsertype() {
        return usertype;
    }

    public int getUserid() {
        return userid;
    }

    @Override
    public String toString() {
        return userid + "\t" + name + " " + "\t" + this.getClass().getSimpleName();
    }
}
