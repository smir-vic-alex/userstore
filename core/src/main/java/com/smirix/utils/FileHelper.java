package com.smirix.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Class description
 *
 * @author Smirnov-VA
 * @created on 2019-05-11
 */
public class FileHelper {

    protected static Logger LOGGER = LoggerFactory.getLogger(FileHelper.class);

    public static File createFile(String filePAth, byte[] data) {

        File file = new File(filePAth);

        try (FileOutputStream fos = new FileOutputStream(file)){
            fos.write(data);
            return file;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String convertFileToBase64(String filePAth, byte[] data) {

        File file = createFile(filePAth, data);

        if (file != null) {
            String base64 = convertFileToBase64(file);
            if (!file.delete()) {
                LOGGER.error("Файл не удален! Имя: " + filePAth);
            }

            return base64;
        }

        return null;
    }

    public static String convertFileToBase64(File file) {
        try {
            byte[] encoded = Base64.encodeBase64(FileUtils.readFileToByteArray(file));
            return new String(encoded, StandardCharsets.US_ASCII);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static File createFileFromBase64(String filePath, String base64) {
        byte[] bytes = Base64.decodeBase64(base64);

        try {
            Path path2 = Paths.get(filePath);
            Files.write(path2, bytes);

            return new File(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
