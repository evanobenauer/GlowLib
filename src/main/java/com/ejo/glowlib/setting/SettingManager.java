package com.ejo.glowlib.setting;

import com.ejo.glowlib.file.CSVManager;
import com.ejo.glowlib.math.Vector;
import com.ejo.glowlib.misc.ColorE;
import com.ejo.glowlib.time.DateTime;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The SettingManager class is able to manage all settings that are assigned to it. This class determines where to save all settings
 * assigned to it and is able to group settings into separate CSV files based on the manager.
 */
public class SettingManager {

    /**
     * If the setting manager is not defined in a setting, the manager will be set to default. This is the default setting manager and will
     * manage all settings without a set manager
     */
    private static final SettingManager defaultManager = new SettingManager("setting","settings");


    private final HashMap<String, Setting> settingList = new HashMap<>();

    private String settingPath;
    private String settingFileName;


    public SettingManager(String settingPath, String settingFileName) {
        this.settingPath = settingPath;
        this.settingFileName = settingFileName;
    }

    /**
     * In order to save, a setting must be STATIC so that there is only 1 setting
     * @param path
     * @param fileName
     * @return
     */
    @SuppressWarnings(value = "all")
    private boolean saveAll(String path, String fileName) {
        ArrayList<ArrayList<String>> dataList = new ArrayList<>();

        for (Setting setting : getSettingList().values()) {
            ArrayList<String> settingDataList = new ArrayList<>();
            settingDataList.add(setting.getKey());
            settingDataList.add(setting.get() + "");
            settingDataList.add(setting.getType());

            dataList.add(settingDataList);
        }
        return CSVManager.saveAsCSV(dataList, path, fileName);
    }

    public boolean saveAll() {
        return saveAll(getSettingPath(),getSettingFileName());
    }

    /**
     * Data is loaded as a string from the .CSV file and placed into the settings with a key provided in the document. Settings have to
     * be parsed. If a setting does not meet the requirements of being an Integer, Float, Double, Boolean, or String, it will be unable
     * to be properly loaded with the current system
     * @param path
     * @param fileName
     * @return
     */
    @SuppressWarnings(value = "all")
    private boolean loadAll(String path, String fileName) {
        ArrayList<String[]> dataList = CSVManager.getDataFromCSV(path, fileName);

        try {
            for (String[] data : dataList) {
                Setting setting = getSettingList().get(data[0]);
                String savedVal = data[1];
                String datatype = data[2];

                if (datatype.equals("unloadable")) continue;

                if (savedVal.equals("null") || datatype.equals("nullType")) setting.set(null);
                else if (datatype.equals("string")) setting.set(savedVal);
                else if (datatype.equals("integer")) setting.set(Integer.parseInt(savedVal));
                else if (datatype.equals("float")) setting.set(Float.parseFloat(savedVal));
                else if (datatype.equals("double")) setting.set(Double.parseDouble(savedVal));
                else if (datatype.equals("boolean")) setting.set(Boolean.parseBoolean(savedVal));
                else if (datatype.equals("datetime")) setting.set(new DateTime(savedVal));
                else if (datatype.equals("vector")) {
                    String[] vecVals = savedVal.replace("<","").replace(">","").split("\\|");
                    setting.set(new Vector(Double.parseDouble(vecVals[0]),Double.parseDouble(vecVals[1]),Double.parseDouble(vecVals[2])));
                } else if (datatype.equals("colore")) {
                    String[] vecVals = savedVal.substring(9).replace("]","").split("\\|");
                    setting.set(new ColorE(Integer.parseInt(vecVals[0]),Integer.parseInt(vecVals[1]),Integer.parseInt(vecVals[2]),Integer.parseInt(vecVals[3])));
                }
                //If we reach here, the setting will be set to its default value as it is un-readable
            }
            return true;
        } catch (Exception e) {
            //If the data cannot be set because, return false
            return false;
        }
    }

    public boolean loadAll() {
        return loadAll(getSettingPath(),getSettingFileName());
    }


    public void setSettingPath(String settingPath) {
        this.settingPath = settingPath;
    }

    public void setSettingFileName(String settingFileName) {
        this.settingFileName = settingFileName;
    }


    public String getSettingPath() {
        return settingPath;
    }

    public String getSettingFileName() {
        return settingFileName;
    }


    public HashMap<String, Setting> getSettingList() {
        return settingList;
    }


    public static SettingManager getDefaultManager() {
        return defaultManager;
    }

}
