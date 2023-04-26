package GUI.Model;

import BE.Customer.CustomerType;
import BE.UserType.UserType;

import java.util.List;

public class ModelsHandler {
    private LoginModel loginModel;

    private AdminModel adminModel;

    private List<UserType> userTypeList;
    private List<CustomerType> customerTypeList;

    public ModelsHandler() throws Exception {
        loginModel = new LoginModel();
        adminModel = new AdminModel();

    }

    public LoginModel getLoginModel() {
        return loginModel;
    }

    public AdminModel getAdminModel() {
        return adminModel;
    }

    public List<UserType> getUserTypeList() {
        return userTypeList;
    }

    public List<CustomerType> getCustomerTypeList() {
        return customerTypeList;
    }
}
