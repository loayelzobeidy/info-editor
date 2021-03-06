
package com.linkedin.replica.editInfo.config;

import com.linkedin.replica.editInfo.cache.handlers.CacheEditInfoHandler;
import com.linkedin.replica.editInfo.commands.Command;
import com.linkedin.replica.editInfo.database.handlers.EditInfoHandler;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class Configuration {
    private Properties commandConfig = new Properties();
    private Properties appConfig = new Properties();
    private Properties arangoConfig = new Properties();
    private Properties redisConfig = new Properties();
    private Properties controllerConfig = new Properties();

    private static Configuration instance;
    private boolean isAppConfigModified;
    private boolean isArangoConfigModified;
    private boolean isCommandsConfigModified;


    private String appConfigPath;
    private String arangoConfigPath;
    private String commandsConfigPath;
    private String redisConfigPath;

    private Configuration(String appConfigPath, String arangoConfigPath, String commandsConfigPath,
                          String controllerConfigPath,String redisConfigPath) throws IOException {
        populateWithConfig(this.appConfigPath = appConfigPath, appConfig);
        populateWithConfig(this.arangoConfigPath = arangoConfigPath, arangoConfig);
        populateWithConfig(this.commandsConfigPath = commandsConfigPath, commandConfig);
        populateWithConfig(controllerConfigPath, controllerConfig);
        populateWithConfig(redisConfigPath, redisConfig);

        this.appConfigPath = appConfigPath;
        this.arangoConfigPath = arangoConfigPath;
        this.commandsConfigPath = commandsConfigPath;
        this.redisConfigPath = redisConfigPath;
    }

    public static void init(String appConfigPath, String arangoConfigPath, String commandsConfigPath, String controllerConfigPath,String redisConfigPath) throws IOException {
        instance = new Configuration(appConfigPath, arangoConfigPath, commandsConfigPath, controllerConfigPath,redisConfigPath);
    }

    public static Configuration getInstance() {
        return instance;
    }

    private static void populateWithConfig(String configFilePath, Properties properties) throws IOException {
        FileInputStream inputStream = new FileInputStream(configFilePath);
        properties.load(inputStream);
        inputStream.close();
    }

    public Class getCommandClass(String commandName) throws ClassNotFoundException {
        String commandsPackageName = Command.class.getPackage().getName() + ".impl";
        String commandClassPath = commandsPackageName + '.' + commandConfig.get(commandName);
        return Class.forName(commandClassPath);
    }

    public Class getDatabaseHandlerClass(String commandName) throws ClassNotFoundException {
        String handlerPackageName = EditInfoHandler.class.getPackage().getName() + ".impl";
        String handlerClassPath = handlerPackageName + "." + commandConfig.get(commandName + ".handler");
        return Class.forName(handlerClassPath);
    }

    public String getAppConfigProp(String key) {
        return appConfig.getProperty(key);
    }
    public String getRedisConfigProp(String key) {
        return redisConfig.getProperty(key);
    }
    public String getArangoConfigProp(String key) {
        return arangoConfig.getProperty(key);
    }

    public Class getHandlerClass(String commandName) throws ClassNotFoundException {
        String handlerPackageName = EditInfoHandler.class.getPackage().getName() + ".impl";
        String handlerClassPath = handlerPackageName + "." + commandConfig.get(commandName + ".handler");
        return Class.forName(handlerClassPath);
    }
    public Class getCacheHandlerClass(String commandName) throws ClassNotFoundException {
        String handlerPackageName = CacheEditInfoHandler.class.getPackage().getName() + ".impl";
        String handlerClassPath = handlerPackageName + "." + commandConfig.get(commandName + ".handler.cache");
        return Class.forName(handlerClassPath);
    }
    public String getControllerConfigProp(String key) {
        return controllerConfig.getProperty(key);
    }

    public String getCommandConfigProp(String key) {
        return commandConfig.getProperty(key);
    }

    public void setAppControllerProp(String key, String val) {
        if (val != null)
            appConfig.setProperty(key, val);
        else
            appConfig.remove(key); // remove property if val is null

        isAppConfigModified = true;
    }

    public void setArrangoConfigProp(String key, String val) {
        if (val != null)
            arangoConfig.setProperty(key, val);
        else
            arangoConfig.remove(key); // remove property if val is null

        isArangoConfigModified = true;
    }



    public void setCommandsConfigProp(String key, String val) {
        if (val != null)
            commandConfig.setProperty(key, val);
        else
            commandConfig.remove(key); // remove property if val is null

        isCommandsConfigModified = true;
    }

    /**
     * Commit changes to write modifications in configuration files
     *
     * @throws IOException
     */
    public void commit() throws IOException {
        if (isAppConfigModified) {
            writeConfig(appConfigPath, appConfig);
            isAppConfigModified = false;
        }

        if (isArangoConfigModified) {
            writeConfig(arangoConfigPath, arangoConfig);
            isArangoConfigModified = false;
        }


        if (isCommandsConfigModified) {
            writeConfig(commandsConfigPath, commandConfig);
            isCommandsConfigModified = false;
        }
    }

    private void writeConfig(String filePath, Properties properties) throws IOException {
        // delete configuration file and then re-write it
        Files.deleteIfExists(Paths.get(filePath));
        OutputStream out = new FileOutputStream(filePath);
        properties.store(out, "");
        out.close();
    }
}