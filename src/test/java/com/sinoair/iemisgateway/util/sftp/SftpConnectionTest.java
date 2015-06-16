package com.sinoair.iemisgateway.util.sftp;

import ch.ethz.ssh2.Connection;
import com.sinoair.iemisgateway.util.PropertiesUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: WangXX4
 * Date: 15-6-15
 * Time: 下午1:05
 * To change this template use File | Settings | File Templates.
 */
public class SftpConnectionTest {
    SftpConnection sftpConnection;

    @Before
    public void setUp() throws Exception {
        sftpConnection = new SftpConnection();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetSFTPConnection() throws Exception {
        String hostname = PropertiesUtil.readProperty("correos", "correosUrl");
        String username = PropertiesUtil.readProperty("correos", "pfUsername");
        String fileName = PropertiesUtil.readProperty("correos", "keyFileManifest");
        Connection connection = sftpConnection.getSFTPConnection(hostname, username, fileName);
        Assert.assertEquals(false, connection == null);


    }
}
