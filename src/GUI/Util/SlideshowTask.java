package GUI.Util;

import javafx.concurrent.Task;
import javafx.scene.image.Image;

import java.io.File;
import java.util.List;

public class SlideshowTask extends Task<Integer> {

    private List<Image> slide;
    private int secondsUntilNextImage;

    public SlideshowTask(List<Image> images, int secondsUntilNextImage) {
        this.slide = images;
        this.secondsUntilNextImage = secondsUntilNextImage;
    }

    @Override
    protected Integer call() throws Exception {
        for (int i = 0; i < slide.size(); i++) {
            Thread.sleep((long) secondsUntilNextImage * 1000);

            File file = new File(slide.get(i).getUrl());

            updateMessage(file.getName());
            updateValue(i);

            if (i == slide.size() - 1) i = 0;

            if (isCancelled()) break;
        }
        return null;
    }
}
