package GUI.Model;

import BE.UserType.*;
import BLL.Interfaces.ILoginManager;
import BLL.LoginManager;

import java.io.IOException;

public class LoginModel {

    private ILoginManager loginManager;
    private Admin loggedInAdmin;
    private Technician loggedInTechnician;
    private ProjectManager loggedInProjectManager;
    private SalesPerson loggedInSalesPerson;

    public LoginModel() throws IOException{
        loginManager = new LoginManager();
    }

    /**
     *
     * @param Username
     * @param Password
     * @throws Exception
     */
    public void loginAction(String Username, String Password) throws Exception {
        User user = loginManager.LoginUser(Username,Password);
        if (user.getUserType() == 1 )
        {
            System.out.println("Admin");
            loggedInAdmin = new Admin(user.getUserId(), user.getPassWord(), user.getUserName(), user.getName());
        } else if (user.getUserType() == 2)
        {
            System.out.println("Technician");
            loggedInTechnician = new Technician(user.getUserId(), user.getPassWord(), user.getUserName(), user.getName());
        } else if (user.getUserType() == 3)
        {
            System.out.println("ProjectManager");
            loggedInProjectManager = new ProjectManager(user.getUserId(), user.getPassWord(), user.getUserName(), user.getName());
        } else if (user.getUserType() == 4)
        {
            System.out.println("SalesPerson");
            loggedInSalesPerson = new SalesPerson(user.getUserId(), user.getPassWord(), user.getUserName(), user.getName());
        }
    }

    public Admin getLoggedInAdmin() {
        return loggedInAdmin;
    }

    public Technician getLoggedInTechnician() {
        return loggedInTechnician;
    }

    public ProjectManager getLoggedInProjectManager() {
        return loggedInProjectManager;
    }

    public SalesPerson getLoggedInSalesPerson() {
        return loggedInSalesPerson;
    }
}
