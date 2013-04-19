package util;

import webPageContainers4Testing.EnvironmentProperties;

import com.thoughtworks.selenium.DefaultSelenium;

public abstract class SeleniumSingletonFactory {
    private static DefaultSelenium selenium;
//	public static final String STANDARD_PAGE_LOAD_WAIT_TIME = "60000";
    protected static String url;

    public static DefaultSelenium getInstance() throws Exception {
        SeleniumProxySingleton.makeSureWeHaveAJettyProxyRunning();
        if (selenium == null) launchSeleniumBrowser();

        return selenium;
    }

    private static void launchSeleniumBrowser() throws Exception {
        url = EnvironmentProperties.getInstance().getHomePageUrl();        
        selenium = new DefaultSelenium(EnvironmentProperties.getInstance().getSeleniumServerHost(), 
        		EnvironmentProperties.getInstance().getSeleniumServerPort(), getBrowserForTesting(), url);
        String optionString = "-proxyInjectionMode";
        selenium.start(optionString);
       // selenium.useXpathLibrary("javascript-xpath");
        selenium.setSpeed("0");
        selenium.setTimeout(EnvironmentProperties.getInstance().getStandardPageLoadWaitTime());
    }

    private static String getBrowserForTesting() {
        return "*firefox";  // "*iexplore" or "*firefox"
    }

    public static void stopEverything() {
    	selenium.deleteAllVisibleCookies();
        selenium.close();
        selenium.stop();
        selenium.shutDownSeleniumServer();
        SeleniumProxySingleton.stopJettyProxy();
    }

}

