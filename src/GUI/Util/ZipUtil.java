package GUI.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtil {

    public void zip(File path) {
        File[] files = path.listFiles();
        try {
            if (files.length == 0) {
                throw new IllegalArgumentException("No files in path " + path.getAbsolutePath());
            }
            FileOutputStream fileOutputStream = new FileOutputStream("result/zipImages.zip");
            ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);

            for (File zipThis : files) {
                FileInputStream fileInputStream = new FileInputStream(zipThis);
                ZipEntry zipEntry = new ZipEntry(zipThis.getName());
                zipOutputStream.putNextEntry(zipEntry);
                byte[] bytes = new byte[2048];
                int length;
                while ((length = fileInputStream.read(bytes)) >= 0) {
                    zipOutputStream.write(bytes, 0, length);
                }
                fileInputStream.close();
            }
            zipOutputStream.close();
            fileOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }






/**
    public static void main(String args[]) throws IOException {
        Files.createDirectory(Paths.get("files"));
        //compressZip(Paths.get("test.zip"), Paths.get("test.json"), Paths.get("test.xml"));
        decompressZip(Paths.get("test.zip"), Paths.get("files"));
    }

    public static void compressZip(Path outPutFile, Path... filesToZip) {

        try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(outPutFile))) {
            for (Path file : filesToZip) {
                ZipEntry zipEntry = new ZipEntry(file.getFileName().toString());
                zipOutputStream.putNextEntry(zipEntry);
                Files.copy(file, zipOutputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
            ExceptionHandler.displayError(new Exception("compressZip error", e));
        }
    }
    public static void decompressZip(Path zipFile, Path outputDir) {
        try (ZipInputStream zipInputStream = new ZipInputStream(Files.newInputStream(zipFile))) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                Path outputFile = outputDir.resolve(entry.getName());
                Files.copy(zipInputStream, outputFile);
            }
            zipInputStream.closeEntry();
        } catch (IOException e) {
            e.printStackTrace();
            ExceptionHandler.displayError(new Exception("decompressZip error", e));
        }
    }
 */
}
