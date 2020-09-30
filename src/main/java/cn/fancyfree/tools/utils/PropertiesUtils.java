package cn.fancyfree.tools.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * properties工具类
 * @author AlanMa247@Gmail.COM
 * @date 0:41 2020/9/12
**/
public class PropertiesUtils {
    public static Map<String, String> readProperties(String path) {
        try {
            Properties properties = new Properties();
            properties.load(new InputStreamReader(new FileInputStream(new File(path)), "UTF8"));
            Map<String, String> config = new HashMap<>();
            properties.forEach((k, v) -> {
                config.put((String)k, (String)v);
            });
            return config;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
