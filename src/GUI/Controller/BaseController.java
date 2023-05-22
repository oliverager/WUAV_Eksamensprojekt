package GUI.Controller;

import BE.Customer.Customer;
import BE.Project;
import BE.UserType.User;
import GUI.Model.ModelsHandler;

import java.io.IOException;

public abstract class BaseController {

    private ModelsHandler modelsHandler;
    protected MainController mainController;

    protected Project project;

    protected User user;

    protected Customer customer;

    public void setModel(ModelsHandler modelsHandler) {
        this.modelsHandler = modelsHandler;
    }

    public void setMainController(MainController main)
    {
        this.mainController = main;
    }

    public void setOpenedProject(Project openedProject) {
        this.project = openedProject;
    }

    public void setOpenedUser(User openedUser) {
        this.user = openedUser;
    }

    public void setOpenedCustomer(Customer openedCustomer) {
        this.customer = openedCustomer;
    }

    public ModelsHandler getModelsHandler() {
        return modelsHandler;
    }

    public abstract void setup() throws IOException;
}
