package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class Propertie {

    private static final Logger log = LogManager.getLogger(Propertie.class);
    private static Properties prop = new Properties();
    private static String path = Static.PATH_PROJECT + "/src/main/resources/test.properties";
    private Propertie() {
        throw new IllegalStateException("Utility class");
    }

    public static String getValue(String name) {
        try {
            prop.load(new FileInputStream(path));
        } catch (IOException e) {
            log.error(e.toString());
        }
        return prop.getProperty(name);
    }
}