package GUI.Model;

import BE.Customer.CustomerType;
import BE.UserType.UserType;

import java.util.List;

public class ModelsHandler {

    private ProjectManagerModel pmModel;
    private LoginModel loginModel;

    private AdminModel adminModel;

    private List<UserType> userTypeList;
    private List<CustomerType> customerTypeList;

    public ModelsHandler() throws Exception {
        loginModel = new LoginModel();
        adminModel = new AdminModel();
        pmModel = new ProjectManagerModel();

    }

    public LoginModel getLoginModel() {
        return loginModel;
    }

    public AdminModel getAdminModel() {
        return adminModel;
    }

    public ProjectManagerModel getPmModel() {
        return pmModel;
    }

    public List<UserType> getUserTypeList() {
        return userTypeList;
    }

    public List<CustomerType> getCustomerTypeList() {
        return customerTypeList;
    }
}
