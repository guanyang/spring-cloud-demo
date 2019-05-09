package org.gy.framework.demoutil.properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 功能描述：读取配置信息
 */
public class PropertiesUtil {

    /**
     * 记录日志的变量
     */
    private static final Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    public static final String DEFAULT_LOCATION = "conf/default.properties";

    private static final Map<String, Properties> cacheMap = new HashMap<>();

    private static final Properties properties;

    static {
        properties = getProperties(DEFAULT_LOCATION);
    }

    /**
     * 私有构造函数
     */
    private PropertiesUtil() {
    }

    /**
     * 功能描述:获取指定key对应的value
     *
     * @param key
     * @return
     */
    public static String getValue(String key,
                                  String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public static Properties getProperties(String location) {
        Properties properties = cacheMap.get(location);
        if (properties == null) {
            synchronized (cacheMap) {
                if ((properties = cacheMap.get(location)) == null) {
                    properties = loadProperties(location);
                    cacheMap.put(location, properties);
                }
            }
        }
        return properties;
    }

    private static Properties loadProperties(String location) {
        Properties properties = new Properties();
        try (InputStream is = PropertiesUtil.class.getClassLoader().getResourceAsStream(location)) {
            properties.load(is);
        } catch (Exception e) {
            logger.error("加载配置文件异常：" + e.getMessage(), e);
        }
        return properties;
    }

}
