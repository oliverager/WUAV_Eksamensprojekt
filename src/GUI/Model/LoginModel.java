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

    public void loginAction(String Username, String Password) throws Exception {
        User user = loginManager.LoginUser(Username,Password);
        switch (user.getUsertype()) {
            case 1 : loggedInAdmin = new Admin(user.getName(), user.getUsername(), user.getPassword(), user.getUserid());
            break;
            case 2 : loggedInTechnician = new Technician(user.getName(), user.getUsername(), user.getPassword(), user.getUserid());
            break;
            case 3 : loggedInProjectManager = new ProjectManager(user.getName(), user.getUsername(), user.getPassword(), user.getUserid());
            break;
            case 4 : loggedInSalesPerson = new SalesPerson(user.getName(), user.getUsername(), user.getPassword(), user.getUserid());
            break;
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
