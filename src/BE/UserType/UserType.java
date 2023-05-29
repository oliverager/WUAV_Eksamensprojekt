package BE.UserType;

public class UserType {
    private int userTypeId;
    private String userTypeName;

    public UserType(int userType, String userTypeName) {
        this.userTypeId = userType;
        this.userTypeName = userTypeName;
    }

    public int getUserTypeId() {
        return userTypeId;
    }

    public String getUserTypeName() {
        return userTypeName;
    }

    @Override
    public String toString() {
        return userTypeName;
    }
}
