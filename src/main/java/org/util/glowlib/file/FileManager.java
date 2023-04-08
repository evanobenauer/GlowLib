package org.util.glowlib.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class FileManager {

    /**
     * This is the create folder method. When creating a folder, the parent folder MUST first exist in order for the method to return
     */
    private static void createFolder(String path) {
        File folder = new File(path);
        folder.mkdir();
    }

    /**
     * This will create an entire file path in the run directory of the program
     * @param path
     */
    public static void createFolderPath(String path) {
        String[] folderSet = path.split("/");
        String currentPath = "";
        for (String folder : folderSet) {
            currentPath += folder + "/";
            createFolder(currentPath);
        }
    }

    public static void createFolderPath(File file) {
        createFolderPath(getFormattedFilePath(file));
    }

    /**
     * Returns the file path of the file. It will replace all "\" with "/" to be in use for file management
     * @param file
     * @return
     */
    public static String getFormattedFilePath(File file) {
        return file.getPath().replace("\\","/");
    }

    /**
     * Creates a blank file of any name. Items can be written to this file when created
     * @param name
     * @param path
     */
    public static void createFile(String name, String path) {
        createFolderPath(path);
        try {
            File file = new File(path, name);
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            out.write("");
            out.close();
        } catch (Exception e) {
            //
        }
    }

}
