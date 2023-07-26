package com.ejo.glowlib.file;

import com.ejo.glowlib.time.StopWatch;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class CSVManager {

    /**
     * Saves the 2D array as a .csv file (Comma Separated Values). This file is saved to the run directory of the program
     * @param list
     * @param folderPath
     * @param fileName
     */
    public static <T> boolean saveAsCSV(ArrayList<T[]> list, String folderPath, String fileName) {
        FileManager.createFolderPath(folderPath); //Creates the folder path if it does not exist
        String outputFile = folderPath + "/" + fileName + ".csv";
        try {
            FileWriter writer = new FileWriter(outputFile);
            for (T[] rowData : list) {
                writer.write(String.join(",", (String[])rowData) + "\n");
            }
            writer.close();
            return true;
        } catch (IOException e) {
            System.out.println("Error writing data to " + outputFile);
            e.printStackTrace();
            return false;
        }
    }

    public static <K,T> boolean saveAsCSV(HashMap<K,T[]> hashMap, String folderPath, String fileName) {
        FileManager.createFolderPath(folderPath); //Creates the folder path if it does not exist
        String outputFile = folderPath + "/" + fileName + ".csv";
        try {
            FileWriter writer = new FileWriter(outputFile);

            for (K key : hashMap.keySet()) {
                writer.write(key + "," + String.join(",", (String[])hashMap.get(key)) + "\n");
            }
            writer.close();
            return true;
        } catch (IOException e) {
            System.out.println("Error writing data to " + outputFile);
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves a 2D array as the table data fom the .csv file to be used within the program. This method is very
     * useful for retrieving saved data in csv format and saved tables
     * @param folderPath
     * @param fileName
     * @return
     */
    public static ArrayList<String[]> getDataFromCSV(String folderPath, String fileName) {
        File file = new File(folderPath + "/" + fileName + ".csv");
        ArrayList<String[]> rawDataList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                rawDataList.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rawDataList;
    }

    public static HashMap<String,String[]> getHMDataFromCSV(String folderPath, String fileName) {
        File file = new File(folderPath + "/" + fileName + ".csv");
        HashMap<String,String[]> rawDataHashMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                String key = row[0];
                String[] rowCut = line.replace(key + ",","").split(",");
                rawDataHashMap.put(row[0],rowCut);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rawDataHashMap;
    }

    /**
     * Combines all CSV files in a directory into one CSV file of a chosen directory. All combined files are deleted after the combination.
     * @param combineDirectory
     * @param outputDirectory
     * @param outputFileName
     * @return
     */
    public static boolean combineFiles(String combineDirectory, String outputDirectory, String outputFileName) {
        try {
            FileManager.createFolderPath(outputDirectory);
            List<String> files = getCSVFilesInDirectory(combineDirectory);
            FileWriter writer = new FileWriter(outputDirectory + "/" + outputFileName + ".csv");

            for (String file : files) {
                FileReader fileReader = new FileReader(combineDirectory + "/" + file);
                BufferedReader reader = new BufferedReader(fileReader);
                String line;
                while ((line = reader.readLine()) != null) {
                    writer.append(line);
                    writer.append("\n");
                }
                reader.close();
                fileReader.close();
                FileManager.deleteFile(combineDirectory,file);
            }
            writer.flush();
            writer.close();
            return true;
        } catch (IOException e) {
            System.out.println("Could not combine CSV Files");
            e.printStackTrace();
            return false;
        }
    }

    public static boolean clearDuplicates(String directory, String name) {
        HashSet<String> uniqueValues = new HashSet<>();
        try {
            FileReader reader = new FileReader(directory + "/" + name + ".csv");
            BufferedReader br = new BufferedReader(reader);
            String line;
            FileWriter writer = new FileWriter(directory + "/" + name + "_temp" + ".csv");
            while ((line = br.readLine()) != null) {
                if (uniqueValues.add(line)) {
                    writer.append(line);
                    writer.append("\n");
                }
            }
            writer.flush();
            writer.close();
            reader.close();
            FileManager.deleteFile(directory,name + ".csv");
            FileManager.renameFile(directory,name + "_temp" + ".csv",name + ".csv");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<String> getCSVFilesInDirectory(String directory) {
        File folder = new File(directory);
        List<String> files = new ArrayList<>();
        for (File file : folder.listFiles()) {
            if (file.isFile() && file.getName().endsWith(".csv")) {
                files.add(file.getName());
            }
        }
        return files;
    }

}
