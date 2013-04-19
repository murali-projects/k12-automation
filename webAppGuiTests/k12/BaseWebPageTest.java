package k12;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import util.SeleniumSingletonFactory;
import utils.PropertyReader;
import webPageContainers4Testing.EnvironmentProperties;
import webPageContainers4Testing.OceanDatabaseConnectivity;
import webPageElements4Testing.Link;
import webPageElements4Testing.PageButton;
import webPageElements4Testing.TextBox;
import webPageElements4Testing.TextLabel;

import com.thoughtworks.selenium.DefaultSelenium;


public abstract class BaseWebPageTest {
	
	protected DefaultSelenium selenium;
	protected TextBox basicSearchBox;
	protected PageButton basicSearchButton;
	protected OceanDatabaseConnectivity oceanDatabaseConnectivity;
	protected PropertyReader properties;
	protected Link dgMoreLink;
	protected String dgTabLinkKey;
	
	public BaseWebPageTest() {
		try {
			loadProperties();
			basicSearchBox = new TextBox(properties.get("searchBox"));
		    basicSearchButton = new PageButton(properties.get("findButton"));
		    dgTabLinkKey = properties.get("dgTabLink");
		    dgMoreLink = new Link(properties.get("dgMoreLink"));
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	protected void loadProperties() {
		properties = new PropertyReader("properties/default.search.properties");
	}
	
	public void doBasicSearchUsingSearchTerm (String SearchTerm) throws InterruptedException{
		basicSearchBox.type(SearchTerm);
		basicSearchButton.clickAndWait();
	}
	
	public void doDisplayGroupSearchUsingSearchTerm (String searchTerm,String displayGroupName) throws InterruptedException{
		new Link(dgTabLinkKey.replace("${id}", displayGroupName)).ajaxClickAndWait();
		doBasicSearchUsingSearchTerm(searchTerm);
	}
	
	public void doDisplayGroupSearchMoreUsingSearchTerm (String searchTerm,String displayGroupName) throws InterruptedException{
		dgMoreLink.ajaxClickAndWait();
		doDisplayGroupSearchUsingSearchTerm (searchTerm,displayGroupName);
	}
	
	public void doDisplayGroupSearchAllUsingSearchTerm (String searchTerm,String allId, String displayGroupName) throws InterruptedException{
		new Link(dgTabLinkKey.replace("${id}", displayGroupName)).ajaxClickAndWait();
		new Link(dgTabLinkKey.replace("${id}", allId)).ajaxClickAndWait();
		doBasicSearchUsingSearchTerm(searchTerm);
	}
	
	
	@BeforeSuite
	public void start() throws Exception {
		selenium = SeleniumSingletonFactory.getInstance();
	}
	
	
	@AfterSuite
	public void tearDown() throws Exception {
		SeleniumSingletonFactory.stopEverything();
	}
	
	public ArrayList<String> readFileContentsAsArrList (File filePathAndName) throws Exception{
		String fileReadLine = null;
		ArrayList<String> fileContentArrList = new ArrayList<String>();
		BufferedReader fileBuffer = new BufferedReader(new FileReader(filePathAndName));
		while ((fileReadLine = fileBuffer.readLine()) != null) {
			fileContentArrList.add(fileReadLine.toString().toUpperCase());
		}
		fileBuffer.close();
		return fileContentArrList;
	}
	
	@BeforeTest
	public void startBrowserSession() throws Exception
	{
		selenium = SeleniumSingletonFactory.getInstance();
		try {
			waitABit();
			selenium.open(EnvironmentProperties.getInstance().getHomePageUrl() + "/portal/default");
		}
		catch(Exception e) {
			login();
		}

	}
	
	private void login() throws Exception {
		login(properties.get("login.username"), properties.get("login.password"));
	}
	
	protected void login(String username, String password) throws Exception {
		String userNameElement = properties.get("login.username_path");
		String passwordElement = properties.get("login.password_path");
		String submitLocator = properties.get("login.submit_path");
		waitForElement(submitLocator);
		selenium.setTimeout(EnvironmentProperties.getInstance().getStandardPageLoadWaitTime());
		
		if(selenium.isElementPresent(userNameElement) && selenium.isElementPresent(passwordElement) && 
			selenium.isElementPresent(submitLocator))  {
			
			new TextBox(userNameElement).type(username);
			new TextBox(passwordElement).type(password);
			selenium.click(properties.get("login.submit_path"));
			try{
				Thread.sleep(8000);
				selenium.open(EnvironmentProperties.getInstance().getHomePageUrl() + "/portal/default");
			}  catch(Exception e) {
				TextLabel title = new TextLabel(properties.get("portal_page_title_xpath"));
				//Assert.assertTrue(title.getLabelText().contains("Home"));
			}
		}
	}
	
	//NEVER call this
	@Deprecated
	public void waitABit() throws InterruptedException {
		Thread.sleep(2000);
	}

	/**
	 * wait the standard page load time for this locator to show up 
	 **/
	public void waitForElement(final String locator) throws InterruptedException {
		waitForElement(locator, Long.parseLong(EnvironmentProperties.getInstance().getStandardPageLoadWaitTime()));
	}

	/**
	 * wait the specified milliseconds for this locator to show up 
	 **/
	public void waitForElement(String locator, long timeout) throws InterruptedException {
		long timerTimeout = new Date().getTime() + timeout;
		while (new Date().getTime() < timerTimeout) {
			if (selenium.isElementPresent(locator)) {
				return;
			}
			Thread.sleep(50);
		}
		Assert.fail(String.format("Waiting for element '%s' timed out at %d ms", locator, timeout));
	}


}
