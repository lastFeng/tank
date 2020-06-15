package server;

import java.io.IOException;
import java.util.Properties;

/**
 * @author guowf
 * @mail: guowf_buaa@163.com
 * @date created in 2020/6/15 20:49
 * @description:
 */
public class PropertyManager {
    private static Properties properties;

    static {
        properties = new Properties();
        try {
            properties.load(PropertyManager.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String get(String key) {
        return (String)properties.get(key);
    }
}
