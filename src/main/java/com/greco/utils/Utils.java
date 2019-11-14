package com.greco.utils;

import com.greco.exception.BadRequestException;
import com.greco.messages.GenericCheckingMessage;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

public class Utils {
    private static String PASSWORD_PATTERN = "^[a-zA-Z0-9]+$";
    public static boolean hasSpecialCharacters(String text) {
        //Regex: ^[a-zA-Z0-9]+$
        //Pattern pattern = Pattern.compile(regex);
        //Matcher matcher = pattern.matcher(name);
        //matcher.matches() // true or false
        return !text.matches(PASSWORD_PATTERN);
    }

    public static boolean isEmpty(String s) {
        return s == null || s.isEmpty();
    }

    public static Timestamp getTimestamp() {
        Instant instant = Instant.now();
        Timestamp timestamp = Timestamp.from(instant);
        return timestamp;
    }

    public static String getRandomUUID(){
        return UUID.randomUUID().toString();
    }

    public static void createDirectory(String folderName) {
        File directory = new File(folderName);
        if (! directory.exists())
            directory.mkdirs();
    }

    public static void deleteFolder(String destination) throws Exception {
        FileUtils.deleteDirectory(new File(destination));
    }
    public static void deleteFile(String filename, String folder) {
        if (filename.isEmpty())
            throw new BadRequestException(GenericCheckingMessage.UPLOAD_FILENAME_EMPTY.toString());
        File file = new File(folder + filename);
        if (file.exists()) {
            file.delete();
        }
    }
}
