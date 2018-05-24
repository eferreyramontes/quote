package com.blackfox.quote.util;

import org.apache.log4j.Logger;

import java.io.FileReader;
import java.util.Properties;

public class PropertiesUtil {
    private static final Logger logger = Logger.getLogger(PropertiesUtil.class);

    private Properties properties;

    private static PropertiesUtil instance;

    private PropertiesUtil() {
        properties = new Properties();
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            properties.load(classLoader.getResourceAsStream("application.properties"));
        } catch (Exception ex) {
            logger.warn("File couldn't be read: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static synchronized PropertiesUtil getInstance() {
        if (instance == null) {
            instance = new PropertiesUtil();
        }
        return instance;
    }

    public int getLoanMonth() {
        return Integer.valueOf(properties.getProperty("loan.month"));
    }
}
