package GUI.Util;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ImageZipper {
    public static void createImageZip(ArrayList<Image> images, String zipFileName) throws IOException {
        byte[] buffer = new byte[1024];
        String resultFolderPath = "result";

        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(resultFolderPath + "/" + zipFileName))) {
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
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return byteArrayOutputStream.toByteArray();
    }
}

