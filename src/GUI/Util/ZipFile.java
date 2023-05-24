package GUI.Util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipFile {

    public static void main(String args[]) throws IOException {
        Files.createDirectory(Paths.get("files"));
        //compressZip(Paths.get("test.zip"), Paths.get("test.json"), Paths.get("test.xml"));
        decompressZip(Paths.get("test.zip"), Paths.get("files"));
    }

    public static void compressZip(Path outPutFile, Path... filesToZip) throws IOException {

        try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(outPutFile))) {
            for (Path file : filesToZip) {
                ZipEntry zipEntry = new ZipEntry(file.getFileName().toString());
                zipOutputStream.putNextEntry(zipEntry);
                Files.copy(file, zipOutputStream);
            }
        }
    }
    public static void decompressZip(Path zipFile, Path outputDir) throws IOException {
        try (ZipInputStream zipInputStream = new ZipInputStream(Files.newInputStream(zipFile))) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                Path outputFile = outputDir.resolve(entry.getName());
                Files.copy(zipInputStream, outputFile);
            }
            zipInputStream.closeEntry();
        }
    }
}
