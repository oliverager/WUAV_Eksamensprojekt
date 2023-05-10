package BE.UserType;

public class User {

    private String name;
    private String userName;
    private String passWord;
    private int userType;
    private int userId;

    public User(int userId, String passWord, String userName, String name, int userType) {
        this.userId = userId;
        this.passWord = passWord;
        this.userName = userName;
        this.name = name;
        this.userType = userType;
    }
    public User(String passWord, String userName, String name, int userType) {
        this.passWord = passWord;
        this.userName = userName;
        this.name = name;
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public int getUserType() {
        return userType;
    }

    public int getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return userId + "\t" + name + " " + "\t" + this.getClass().getSimpleName();
    }
}
