package props;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropsReadTest {

    private static final Logger logger = LoggerFactory
            .getLogger(PropsReadTest.class);

    public static void main(String[] args) {
        Properties props = loadProps("./test.properties");
        logger.info(getString(props, "server.port"));
        logger.info(getString(props, "name"));
        logger.info(getNumber(props, "age")+"");
    }

    public static Properties loadProps(String propsPath) {
        Properties props = new Properties();
        InputStream is = null;
        try {
            if (StringUtils.isEmpty(propsPath)) {
                throw new IllegalArgumentException();
            }
            String suffix = ".properties";
            if (propsPath.lastIndexOf(suffix) == -1) {
                propsPath += suffix;
            }
            is = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream(propsPath);
            if (is != null) {
                props.load(is);
            }
        } catch (Exception e) {
            logger.error("加载属性文件出错！", e);
            throw new RuntimeException(e);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                logger.error("释放资源出错！", e);
            }
        }
        return props;
    }

    public static String getString(Properties props, String key) {
        String value = "";
        if (props.containsKey(key)) {
            value = props.getProperty(key);
        }
        return value;
    }

    public static int getNumber(Properties props, String key) {
        int value = 0;
        if (props.containsKey(key)) {
            value = Integer.valueOf(props.getProperty(key));
        }
        return value;
    }

}
