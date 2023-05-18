package GUI.Controller;

import GUI.Model.ModelsHandler;

import java.io.IOException;

public abstract class BaseController {

    private ModelsHandler modelsHandler;
    protected MainController mainController;

    public void setModel(ModelsHandler modelsHandler) {
        this.modelsHandler = modelsHandler;
    }

    public void setMainController(MainController main)
    {
        this.mainController = main;
    }


    public ModelsHandler getModelsHandler() {
        return modelsHandler;
    }

    public abstract void setup() throws IOException;
}
