package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class Propertie {

    public static final Logger log = LoggerFactory.getLogger("test");
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