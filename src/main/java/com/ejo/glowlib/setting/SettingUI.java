package com.ejo.glowlib.setting;

//TODO: Remove SettingUI
/**
 * The SettingUI Class is a modified setting class that is able to be used efficiently with visual data changers such as sliders
 * and selectors
 * @param <T>
 */
@Deprecated
public class SettingUI<T> extends Setting<T> {


    private final String name;
    private final String description;

    private Double min;
    private Double max;
    private Double step;

    private T[] modes;


    /**
     * Default Constructor. This is a general constructor which is not specialized and can hold any value which is good
     * for use of booleans, Strings, etc. as they do not require special parameters
     */
    private SettingUI(SettingManager settingManager, String key, String name, String description, T defaultVal) {
        super(settingManager,key,defaultVal);
        this.name = name;
        this.description = description;
    }

    private SettingUI(String key, String name, String description, T defaultVal) {
        super(key,defaultVal);
        this.name = name;
        this.description = description;
    }

    /**
     * Setting Number. This is a type of UI setting that has a minimum, maximum, and step value for slider UI systems
     * It is able to contain any number value needed to be saved and/or modified by the UI
     */
    public SettingUI(SettingManager settingManager, String key, String name, String description, T defaultValue, double min, double max, double step) {
        this(settingManager,key,name,description,defaultValue);
        this.min = min;
        this.max = max;
        this.step = step;
    }

    public SettingUI(String key, String name, String description, T defaultValue, double min, double max, double step) {
        this(key,name,description,defaultValue);
        this.min = min;
        this.max = max;
        this.step = step;
    }

    /**
     * Setting Mode. This is a type of UI setting that has a defined set of possible values to be cycled through.
     */
    @SafeVarargs
    public SettingUI(SettingManager settingManager, String key, String name, String description, T defaultValue, T... modes) {
        this(settingManager,key,name,description,defaultValue);
        this.modes = modes;
    }

    @SafeVarargs
    public SettingUI(String key, String name, String description, T defaultValue, T... modes) {
        this(key,name,description,defaultValue);
        this.modes = modes;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public T getMin() {
        return (T) min;
    }

    public T getMax() {
        return (T)max;
    }

    public T getStep() {
        return (T)step;
    }


    public T[] getModes() {
        return modes;
    }

}
