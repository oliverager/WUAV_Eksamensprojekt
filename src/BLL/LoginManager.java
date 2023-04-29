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
    @Override
    public User LoginUser(String UserName, String Password) throws Exception {
        return null;
    }
}
