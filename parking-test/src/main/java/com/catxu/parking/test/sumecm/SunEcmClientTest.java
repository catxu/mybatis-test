package com.catxu.parking.test.sumecm;

import com.sunyard.client.SunEcmClientApi;
import com.sunyard.client.impl.SunEcmClientSocketApiImpl;
import org.junit.Test;

/**
 * @author kiven
 */
public class SunEcmClientTest {
    @Test
    public void client() throws Exception {
        SunEcmClientApi apiClient = new SunEcmClientSocketApiImpl("ip",1234);
        apiClient.login("userName", "password");
        // ...
        apiClient.logout("userName");
    }
}
