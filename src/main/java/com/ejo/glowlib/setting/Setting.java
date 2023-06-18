package com.ejo.glowlib.setting;

import com.ejo.glowlib.math.Vector;
import com.ejo.glowlib.misc.ColorE;
import com.ejo.glowlib.time.DateTime;
import com.ejo.glowlib.misc.Container;

/**
 * This is a setting class. Setting classes are classes that can have their values saved and loaded to the computer for future use.
 * This class holds a container to place a value as well as a name.
 * @param <T>
 */
public class Setting<T> extends Container<T> {

    private final SettingManager settingManager;

    private final T defaultVal;

    private final String key;

    /**
     * Default Constructor. This constructor is able to set a name and a default value to the setting
     *
     * @param key
     * @param defaultVal
     */
    public Setting(SettingManager settingManager, String key, T defaultVal) {
        super(defaultVal);
        this.key = key;
        this.defaultVal = defaultVal;

        this.settingManager = settingManager;
        this.settingManager.getSettingList().put(key, this);
    }

    public Setting(SettingManager settingManager, String key) {
        this(settingManager,key,null);
    }

    public Setting(String key, T defaultVal) {
        this(SettingManager.getDefaultManager(),key,defaultVal);
    }

    public Setting(String key) {
        this(SettingManager.getDefaultManager(),key,null);
    }


    public void reset() {
        set(getDefaultValue());
    }

    public boolean save() {
        return getSettingManager().saveAll();
    }

    public boolean load() {
        return getSettingManager().loadAll();
    }


    public String getKey() {
        return key;
    }

    public T getDefaultValue() {
        return defaultVal;
    }


    public boolean isLoadable() {
        return !getType().equals("unloadable");
    }

    /**
     * getType returns the type of data stored in the setting. It will only return a readable type if the data is one of a String, Integer,
     * Float, Double, Boolean, Vector, Color, or DateTime. If the setting is set to null at default and has an active null value, the type will
     * be presented as nullType. If the type is not one of our saveable types, it will be set to unreadable and will be absent from loading
     * @return
     */
    public String getType() {
        if (getDefaultValue() instanceof String || get() instanceof String) return "string";
        else if (getDefaultValue() instanceof Integer || get() instanceof Integer) return "integer";
        else if (getDefaultValue() instanceof Float || get() instanceof Float) return "float";
        else if (getDefaultValue() instanceof Double || get() instanceof Double) return "double";
        else if (getDefaultValue() instanceof Boolean || get() instanceof Boolean) return "boolean";
        else if (getDefaultValue() instanceof Vector || get() instanceof Vector) return "vector";
        else if (getDefaultValue() instanceof DateTime || get() instanceof DateTime) return "datetime";
        else if (getDefaultValue() instanceof ColorE || get() instanceof ColorE) return "colore";
        else if (getDefaultValue() == null || get() == null) return "nullType";
        else return "unloadable";
    }

    public SettingManager getSettingManager() {
        return settingManager;
    }


    @Override
    public String toString() {
        return "[" + getKey() + ": " + get() + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Setting<?> setting)) return false;
        return setting.toString().equals(toString()) && setting.getDefaultValue().equals(getDefaultValue());
    }

}