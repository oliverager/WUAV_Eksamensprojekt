package GUI.Util;

import javafx.scene.image.Image;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ImageZipper {
    public static void createImageZip(ArrayList<Image> images, String zipFileName) throws IOException {
        byte[] buffer = new byte[1024];

        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFileName))) {
            for (int i = 0; i < images.size(); i++) {
                Image image = images.get(i);
                String entryName = "image_" + i + ".png";
                ZipEntry zipEntry = new ZipEntry(entryName);
                zipOutputStream.putNextEntry(zipEntry);

                // Write the image data to the ZIP file
                byte[] imageData = getImageData(image);
                zipOutputStream.write(imageData, 0, imageData.length);

                zipOutputStream.closeEntry();
            }
        }
    }

    private static byte[] getImageData(Image image) {
        // Implement the logic to get the image data from the Image object
        // and return it as a byte array.
        // This implementation depends on how you obtain the image data in your specific scenario.
        // Replace this with your own implementation.

        // Example implementation:
        // Assuming Image class has a method "getData()" that returns the image data as a byte array.
        return null; //image.getData();
    }


}

