import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Properties;

/**
 * @author guowf
 * @mail: guowf_buaa@163.com
 * @date created in 2020/6/15 20:41
 * @description:
 */
public class ConfigTest {

    @Test
    public void testConfig() {
        Properties properties = new Properties();
        try {
            properties.load(ConfigTest.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String initTankCount = (String)properties.get("initTankCount");
        Assertions.assertEquals(2, Integer.valueOf(initTankCount));
    }
}
