package BLL;

import BE.UserType.User;
import BLL.Interfaces.ILoginManager;
import DAL.DB.LoginDAO_DB;
import DAL.Interface.ILoginDAO;

import java.io.IOException;

public class LoginManager implements ILoginManager {

    private ILoginDAO loginDAO;

    public LoginManager() throws IOException {
        loginDAO = new LoginDAO_DB();
    }

    /**
     * Sends two Strings to the Dal layer and gets an Admin object that contains matching Strings text
     * @param UserName
     * @param Password
     * @return an Admin object which matches username and password
     * @throws Exception
     */
    @Override
    public User LoginUser(String UserName, String Password) throws Exception {
        return loginDAO.LoginUser(UserName, Password);
    }
}
