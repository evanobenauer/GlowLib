package com.ejo.glowlib.file;

import java.io.*;
import java.util.*;

public class CSVManager {

    /**
     * Saves the 2D array as a .csv file (Comma Separated Values). This file is saved to the run directory of the program
     *
     * @param list
     * @param folderPath
     * @param fileName
     */
    public static <T> boolean saveAsCSV(ArrayList<T[]> list, String folderPath, String fileName) {
        FileManager.createFolderPath(folderPath); //Creates the folder path if it does not exist
        String outputFile = folderPath + (folderPath.equals("") ? "" : "/") + fileName + ".csv";
        try {
            FileWriter writer = new FileWriter(outputFile);
            for (T[] rowData : list) {
                writer.write(String.join(",", (String[]) rowData) + "\n");
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
     * This method saves a HashMap as a CSV file in a specified location.
     *
     * @param hashMap
     * @param folderPath
     * @param fileName
     * @param <K>
     * @param <T>
     * @return
     */
    public static <K, T> boolean saveAsCSV(HashMap<K, T[]> hashMap, String folderPath, String fileName) {
        FileManager.createFolderPath(folderPath); //Creates the folder path if it does not exist
        String outputFile = folderPath + (folderPath.equals("") ? "" : "/") + fileName.replace(".csv","") + ".csv";
        try {
            FileWriter writer = new FileWriter(outputFile);

            for (K key : hashMap.keySet()) {
                writer.write(key + "," + Arrays.toString(hashMap.get(key)).replace("[","").replace("]","") + "\n");
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
     *
     * @param folderPath
     * @param fileName
     * @return
     */
    public static ArrayList<String[]> getDataFromCSV(String folderPath, String fileName) {
        File file = new File(folderPath + (folderPath.equals("") ? "" : "/") + fileName.replace(".csv","") + ".csv");
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

    /**
     * This method returns a HashMap with a key and String array from a CSV file
     *
     * @param folderPath
     * @param fileName
     * @return
     */
    public static HashMap<String, String[]> getHMDataFromCSV(String folderPath, String fileName) {
        File file = new File(folderPath + (folderPath.equals("") ? "" : "/") + fileName.replace(".csv","") + ".csv");
        HashMap<String, String[]> rawDataHashMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                String key = row[0];
                String[] rowCut = line.replace(key + ",", "").split(",");
                rawDataHashMap.put(row[0], rowCut);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rawDataHashMap;
    }

    /**
     * Combines all CSV files in a directory into one CSV file of a chosen directory. All combined files are deleted after the combination.
     *
     * @param combineDirectory
     * @param outputDirectory
     * @param outputFileName
     * @return
     */
    public static boolean combineFiles(String combineDirectory, String outputDirectory, String outputFileName) {
        try {
            FileManager.createFolderPath(outputDirectory);
            List<String> files = getCSVFilesInDirectory(combineDirectory);
            FileWriter writer = new FileWriter(outputDirectory + (outputDirectory.equals("") ? "" : "/") + outputFileName.replace(".csv","") + ".csv");

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
                FileManager.deleteFile(combineDirectory, file);
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


    /**
     * This method inputs a CSV file and deletes all duplicate rows, leaving only 1 copy of each row
     *
     * @param directory
     * @param name
     * @return
     */
    public static boolean clearDuplicates(String directory, String name) {
        HashSet<String> uniqueValues = new HashSet<>();
        try {
            FileReader reader = new FileReader(directory + (directory.equals("") ? "" : "/") + name.replace(".csv","") + ".csv");
            BufferedReader br = new BufferedReader(reader);
            String line;
            FileWriter writer = new FileWriter(directory + (directory.equals("") ? "" : "/") + name.replace(".csv","") + "_temp" + ".csv");
            while ((line = br.readLine()) != null) {
                if (uniqueValues.add(line)) {
                    writer.append(line);
                    writer.append("\n");
                }
            }
            writer.flush();
            writer.close();
            reader.close();
            FileManager.deleteFile(directory, name.replace(".csv","") + ".csv");
            FileManager.renameFile(directory, name.replace(".csv","") + "_temp" + ".csv", name.replace(".csv","") + ".csv");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * This method returns a list of all CSV files in a specified directory
     *
     * @param directory
     * @return
     */
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
