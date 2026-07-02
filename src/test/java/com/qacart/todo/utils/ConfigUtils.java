package com.qacart.todo.utils;

import java.util.Properties;

public class ConfigUtils {
    private  Properties properties;
    private static ConfigUtils configUtils;
    private ConfigUtils(){
        String env = System.getProperty("env","production");
        switch (env){
            case "production":
                properties = PropertiesUtils.loadProperties("src/test/java/com/qacart/todo/config/production.properties");
                break;
            case "local":
                properties = PropertiesUtils.loadProperties("src/test/java/com/qacart/todo/config/local.properties");
                break;
            default:
                throw new RuntimeException("Environment not found");
        }


    }
    public  static ConfigUtils getInstance(){
        if(configUtils==null){
            configUtils = new ConfigUtils();
        }
        return configUtils;
    }
    public  String getBaseUrl(){

        String prob =  properties.getProperty("baseUrl");
        if(prob==null || prob.equals("")){
            throw new RuntimeException("can't get baseUrl");
        }
        return prob;
    }
    public  String getEmail() {

        String prob = properties.getProperty("email");
        if (prob == null || prob.equals("")) {
            throw new RuntimeException("can't get email");
        }
        return prob;
    }
    public  String getPassword() {

        String prob = properties.getProperty("password");
        if (prob == null || prob.equals("")) {
            throw new RuntimeException("can't get password");
        }
        return prob;
    }
}
