package com.bookit.BICustomConfig;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class BICustomConfig extends JavaPlugin {
    private static final Map<String, Map<String, FileConfiguration>> configMap = new HashMap();

    @Override
    public void onEnable() {
        getLogger().info("Plugin Enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin Disabled.");
    }

    /***
     * get config
     *
     * @param plugin Plugin of config
     * @param fileName Name of config
     * @return Config file. if not loaded, return null
     */
    public static FileConfiguration getConfig(JavaPlugin plugin, String fileName) {
        if (!isLoaded(plugin, fileName)) {
            Bukkit.getLogger().warning("\"" + fileName + "\" not loaded.");
            return null;
        }

        return configMap.get(plugin.getName()).get(fileName);
    }

    /***
     * add config to map
     *
     * @param pluginName Name of Plugin
     * @param fileName Name of file
     * @param config Config to register
     */
    private static void registerConfig(String pluginName, String fileName, FileConfiguration config) {
        if (!configMap.containsKey(pluginName)) {
            configMap.put(pluginName, new HashMap<>());
        }

        Map<String, FileConfiguration> pluginMap = configMap.get(pluginName);
        if (pluginMap.containsKey(fileName)) {
            return ;
        }

        pluginMap.put(fileName, config);
        Bukkit.getLogger().info(fileName + " loaded.");
    }

    /***
     * check config already loaded
     *
     * @param plugin Plugin of config
     * @param fileName Name of file
     * @return True if already loaded
     */
    public static boolean isLoaded(JavaPlugin plugin, String fileName) {
        String pluginName = plugin.getName();
        return configMap.containsKey(pluginName) && configMap.get(pluginName).containsKey(fileName);
    }

    /***
     * load config file
     * if not exists, create new one.
     *
     * @param plugin Plugin of config
     * @param fileName Name of file
     */
    public static void loadConfig(JavaPlugin plugin, String fileName){
        File dataFolder = plugin.getDataFolder();
        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }

        File file = new File(dataFolder, fileName);
        System.out.println(file);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException exception) {
                Bukkit.getLogger().warning("IOException occured while load config \"" + fileName + "\"");
            }
        }

        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        registerConfig(plugin.getName(), fileName, config);
    }

    /***
     * reload config
     *
     * @param plugin Plugin of config
     * @param fileName Name of file
     */
    public static void reloadConfig(JavaPlugin plugin, String fileName) {
        if (!isLoaded(plugin, fileName)) {
            copyDefaultConfig(plugin, fileName);
        }

        Map<String, FileConfiguration> pluginMap = configMap.get(plugin.getName());
        File file = new File(plugin.getDataFolder(), fileName);
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        pluginMap.remove(fileName);
        pluginMap.put(fileName, config);
        Bukkit.getLogger().info(fileName + " reloaded.");
    }

    /***
     * copy default config from resources
     *
     * @param plugin Plugin of config
     * @param fileName Name of file
     */
    public static void copyDefaultConfig(JavaPlugin plugin, String fileName){
        File dataFolder = plugin.getDataFolder();
        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }

        File file = new File(dataFolder, fileName);
        if (file.exists()) {
            loadConfig(plugin, fileName);
            return ;
        }

        Reader reader = null;
        try {
            file.createNewFile();
            reader = new InputStreamReader(plugin.getResource(fileName), "UTF-8");
        } catch (UnsupportedEncodingException exception) {
            Bukkit.getLogger().warning("UnsupportedEncodingException occured while copy default config \"" + fileName + "\"");
        } catch (IOException exception) {
            Bukkit.getLogger().warning("IOException occured while copy default config \"" + fileName + "\"");
        }
        FileConfiguration config = YamlConfiguration.loadConfiguration(reader);
        registerConfig(plugin.getName(), fileName, config);
        saveConfig(plugin, fileName);
    }

    /***
     * save config
     *
     * @param plugin Plugin of config
     * @param fileName Name of file
     */
    public static void saveConfig(JavaPlugin plugin, String fileName) {
        FileConfiguration config = getConfig(plugin, fileName);
        try {
            config.save(new File(plugin.getDataFolder(), fileName));
        } catch (IOException exception) {
            Bukkit.getLogger().warning("IOException occured while save config \"" + fileName + "\"");
        }
    }
}