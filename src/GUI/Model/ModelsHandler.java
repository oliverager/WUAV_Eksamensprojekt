package GUI.Model;

import BE.Customer.CustomerType;
import BE.UserType.UserType;

import java.util.List;

public class ModelsHandler {

    private ProjectManagerModel projectManagerModel;
    private LoginModel loginModel;
    private AdminModel adminModel;
    private SalesPersonModel salesPersonModel;

    private List<UserType> userTypeList;
    private List<CustomerType> customerTypeList;

    public ModelsHandler() throws Exception {
        loginModel = new LoginModel();
        adminModel = new AdminModel();
        salesPersonModel = new SalesPersonModel();
        projectManagerModel = new ProjectManagerModel();

    }

    public LoginModel getLoginModel() {
        return loginModel;
    }

    public AdminModel getAdminModel() {
        return adminModel;
    }

    public ProjectManagerModel getProjectManagerModel() {
        return projectManagerModel;
    }

    public SalesPersonModel getSalesPersonModel() {
        return salesPersonModel;
    }

    public List<UserType> getUserTypeList() {
        return userTypeList;
    }

    public List<CustomerType> getCustomerTypeList() {
        return customerTypeList;
    }
}
