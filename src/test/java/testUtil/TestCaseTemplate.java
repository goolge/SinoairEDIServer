package testUtil;

import com.sinoair.iemisgateway.util.PropertiesUtil;

/**
 * Created by IntelliJ IDEA.
 * User: WangXX4
 * Date: 15-6-18
 * Time: 上午10:02
 * To change this template use File | Settings | File Templates.
 */
public class TestCaseTemplate {
    public static void showEnvironment() {
        String environmentName = PropertiesUtil.readProperty("common", "environment.name");
        String environmentDesc = PropertiesUtil.readProperty("common", "environment.desc");
        String historyRootPath = PropertiesUtil.readProperty("common", "historyRootPath");
        BaseLogger.info("environmentName = " + environmentName);
        BaseLogger.info("environmentDesc = " + environmentDesc);
        BaseLogger.info("historyRootPath = " + historyRootPath);
    }

}
