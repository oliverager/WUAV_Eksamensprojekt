package GUI.Controller;

import GUI.Model.ModelsHandler;

import java.io.IOException;

public abstract class BaseController {

    private ModelsHandler modelsHandler;

    public void setModel(ModelsHandler modelsHandler) {
        this.modelsHandler = modelsHandler;
    }

    public ModelsHandler getModelsHandler() {
        return modelsHandler;
    }

    public abstract void setup() throws IOException;
}
