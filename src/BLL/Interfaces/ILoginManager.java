package BLL.Interfaces;

import BE.UserType.User;

public interface ILoginManager {

    User LoginUser(String UserName, String Password) throws Exception;
}
