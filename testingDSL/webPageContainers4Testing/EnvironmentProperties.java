package webPageContainers4Testing;

import java.io.File;

import utils.PropertyReader;

public class EnvironmentProperties {
	private static final String PROPERTIES_DEFAULT_ENVIRONMENT_PROPERTIES = "properties/default.environment.properties";
	private static final String PROPERTIES_ENVIRONMENT_PROPERTIES = "properties/environment.properties";
	private String homePageUrl = "http://localhost.ggtest.com:8080/portal/portal/default";
	private int seleniumServerPort = 4444;
	private String seleniumServerHost = "localhost";
	private String standardPageLoadWaitTime = "60000";
	private PropertyReader envProperties;

	private static EnvironmentProperties instance = new EnvironmentProperties();

	private EnvironmentProperties() {
		super();
		String environmentPropertyFileFullName = PROPERTIES_DEFAULT_ENVIRONMENT_PROPERTIES;
		File targetFile = new File(PROPERTIES_ENVIRONMENT_PROPERTIES);
		if (targetFile.exists() && targetFile.isFile() && targetFile.canRead()) {
			environmentPropertyFileFullName = PROPERTIES_ENVIRONMENT_PROPERTIES;
		}
		envProperties = new PropertyReader(environmentPropertyFileFullName);
		homePageUrl = envProperties.get("home.page.url");
		seleniumServerPort = Integer.parseInt(envProperties
				.get("selenium.server.port"));
		seleniumServerHost = envProperties.get("selenium.server.host");
		standardPageLoadWaitTime = envProperties.get("standard.page.load.wait.time");
	}

	public static EnvironmentProperties getInstance() {
		return instance;
	}

	public String getHomePageUrl() {
		return homePageUrl;
	}
	
	public String getProperty(String propertyName) {
		return envProperties.get(propertyName);
	}

	public int getSeleniumServerPort() {
		return seleniumServerPort;
	}

	public String getSeleniumServerHost() {
		return seleniumServerHost;
	}

	public String getStandardPageLoadWaitTime() {
		return standardPageLoadWaitTime;
	}
}
