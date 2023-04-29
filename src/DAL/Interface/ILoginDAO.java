package DAL.Interface;

import BE.UserType.User;

public interface ILoginDAO {

    User LoginUser(String UserName, String Password) throws Exception;
}
