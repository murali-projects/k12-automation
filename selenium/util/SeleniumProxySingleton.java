package util;

import java.net.Socket;
import org.openqa.selenium.server.RemoteControlConfiguration;
import org.openqa.selenium.server.SeleniumServer;

import webPageContainers4Testing.EnvironmentProperties;

public abstract class SeleniumProxySingleton {
    protected static SeleniumServer jettyProxy;
    protected static boolean jettyProxyWasStartedByATest = false;

    public static void makeSureWeHaveAJettyProxyRunning() throws Exception {
        Socket socket = null;

        try {
            socket = new Socket(EnvironmentProperties.getInstance().getSeleniumServerHost(), EnvironmentProperties.getInstance().getSeleniumServerPort());

        } catch(Exception e) {
            System.out.println("Nothing is listening on port " + EnvironmentProperties.getInstance().getSeleniumServerPort());
            startJettyProxy();
            jettyProxyWasStartedByATest = true;
        }

        finally {
            if (socket != null) {
                socket.close();
            }
        }
    }



    protected static void startJettyProxy() throws Exception {
        RemoteControlConfiguration config = new RemoteControlConfiguration();
        config.setPort(EnvironmentProperties.getInstance().getSeleniumServerPort());
        jettyProxy = new SeleniumServer(config);
        jettyProxy.start();
    }


    public static void stopJettyProxy() {
        if(jettyProxyWasStartedByATest) {
            jettyProxy.stop();
        }
    }

}

