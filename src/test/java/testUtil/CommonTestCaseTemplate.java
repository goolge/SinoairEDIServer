package testUtil;

import com.sinoair.iemisgateway.util.BaseLogger;
import com.sinoair.iemisgateway.util.PropertiesUtil;

/**
 * Created by IntelliJ IDEA.
 * User: WangXX4
 * Date: 15-6-18
 * Time: 上午10:02
 * To change this template use File | Settings | File Templates.
 */
public class CommonTestCaseTemplate {
    private static String mainRootPath;
    private static String testRootPath;
    private static String environmentName;
    private static String environmentDesc;
    private static String historyRootPath;

    static {
        testRootPath = CommonTestCaseTemplate.class.getResource("/").getPath().substring(1);
        mainRootPath = testRootPath.replace("test-classes", "classes");
        BaseLogger.debug("mainRootPath = " + mainRootPath);
        BaseLogger.debug("testRootPath = " + testRootPath);
        environmentName = PropertiesUtil.readProperty("common", "environment.name");
        environmentDesc = PropertiesUtil.readProperty("common", "environment.desc");
        historyRootPath = PropertiesUtil.readProperty("common", "historyRootPath");

    }

    public static String getMainRootPath() {
        return mainRootPath;
    }

    public static String getTestRootPath() {
        return testRootPath;
    }

    public static void showEnvironment() {
        BaseLogger.info("environmentName = " + environmentName);
        BaseLogger.info("environmentDesc = " + environmentDesc);
        BaseLogger.info("historyRootPath = " + historyRootPath);
    }

}
