package k12.dev.viewall;

import k12.dev.BaseDevWebPageTest;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import util.SeleniumSingletonFactory;
import webPageContainers4Testing.EnvironmentProperties;
import webPageContainers4Testing.dev.ViewFullListPortlet;
import webPageElements4Testing.Link;
import webPageElements4Testing.TextBox;
import webPageElements4Testing.TextLabel;

public class ContentLevelViewAllTest extends BaseDevWebPageTest {

	private ViewFullListPortlet viewAllNews;
	private ViewFullListPortlet viewAllMagazines;
	private ViewFullListPortlet viewAllAcademicJournals;
	private ViewFullListPortlet viewAllAudio;
	private ViewFullListPortlet viewAllPrimarySources;
	private ViewFullListPortlet viewAllReference;
	private ViewFullListPortlet viewAllStatistics;
	private ViewFullListPortlet viewAllViewpoints;

	@BeforeClass
	public void init() throws Exception {
		selenium = SeleniumSingletonFactory.getInstance();
	}

	@BeforeTest
	public void setUp() throws Exception {
		viewAllNews = new ViewFullListPortlet("news");
		viewAllMagazines = new ViewFullListPortlet("magazines");
		viewAllAcademicJournals = new ViewFullListPortlet("academic_journals");
		viewAllAudio = new ViewFullListPortlet("audio");
		viewAllPrimarySources = new ViewFullListPortlet("primarysources");
		viewAllReference = new ViewFullListPortlet("reference"); 
		viewAllStatistics = new ViewFullListPortlet("statistics");
		viewAllViewpoints = new ViewFullListPortlet("viewpoints");
	}

	@Override
	public void startBrowserSession() throws Exception {
		
	}
	
	public void startBrowserSession(String username, String password) throws Exception
	{
		selenium = SeleniumSingletonFactory.getInstance();
		try {
			waitABit();
			selenium.open(EnvironmentProperties.getInstance().getHomePageUrl() + "/portal/default");
		}
		catch(Exception e) {
			login(username, password);
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
			}
		}
	}	
	
	@Test
	public void searchValidationMessageIsDisplayedContentLevelOn() throws Exception {
		selenium.deleteAllVisibleCookies();
		startBrowserSession(properties.get("login.contentlevelovrc.on.username"), properties.get("login.contentlevelovrc.on.password"));
		doBasicSearchUsingSearchTerm("war");
	}

	@Test(dependsOnMethods = { "searchValidationMessageIsDisplayedContentLevelOn" })
	public void verifyNewsContentLevelDisplayedWhenPrefIsYes() throws Exception {
		Assert.assertTrue(viewAllNews.checkViewAllLinkIsWorking());
		Assert.assertTrue(viewAllNews.verifyContentLevelIndicatorIsDisplayed());
		Assert.assertTrue(viewAllNews.verifyContentLevelIndicatorHasAltText());		
	}
	
	@Test(dependsOnMethods = { "verifyNewsContentLevelDisplayedWhenPrefIsYes" })
	public void verifyMagazineContentLevelDisplayedWhenPrefIsYes() throws Exception {
		Link link = new Link(properties.get("magazines_tab"));
		link.click();
		Assert.assertTrue(viewAllMagazines.verifyContentLevelIndicatorIsDisplayed());
		Assert.assertTrue(viewAllMagazines.verifyContentLevelIndicatorHasAltText());
	}
	
	@Test(dependsOnMethods = { "verifyMagazineContentLevelDisplayedWhenPrefIsYes" })
	public void verifyAcademicJournalsContentLevelDisplayedWhenPrefIsYes() throws Exception {
		Link link = new Link(properties.get("academicJournals_tab"));
		link.click();
		Assert.assertTrue(viewAllAcademicJournals.verifyContentLevelIndicatorIsDisplayed());
		Assert.assertTrue(viewAllAcademicJournals.verifyContentLevelIndicatorHasAltText());
	}

	@Test(dependsOnMethods = { "verifyAcademicJournalsContentLevelDisplayedWhenPrefIsYes" })
	public void verifyAudioContentLevelDisplayedWhenPrefIsYes() throws Exception {
		Link link = new Link(properties.get("audio_tab"));
		link.click();
		Assert.assertTrue(viewAllAudio.verifyContentLevelIndicatorIsDisplayed());
		Assert.assertTrue(viewAllAudio.verifyContentLevelIndicatorHasAltText());
	}

	@Test(dependsOnMethods = { "verifyAudioContentLevelDisplayedWhenPrefIsYes" })
	public void verifyPrimarySourcesContentLevelDisplayedWhenPrefIsYes() throws Exception {
		Link link = new Link(properties.get("primarySources_tab"));
		link.click();
		Assert.assertTrue(viewAllPrimarySources.verifyContentLevelIndicatorIsDisplayed());
		Assert.assertTrue(viewAllPrimarySources.verifyContentLevelIndicatorHasAltText());
	}

	@Test(dependsOnMethods = { "verifyPrimarySourcesContentLevelDisplayedWhenPrefIsYes" })
	public void verifyStatisticsContentLevelDisplayedWhenPrefIsYes() throws Exception {
		Link link = new Link(properties.get("statistics_tab"));
		link.click();
		Assert.assertTrue(viewAllStatistics.verifyContentLevelIndicatorIsDisplayed());
		Assert.assertTrue(viewAllStatistics.verifyContentLevelIndicatorHasAltText());
	}

	@Test(dependsOnMethods = { "verifyStatisticsContentLevelDisplayedWhenPrefIsYes" })
	public void verifyViewpointsContentLevelDisplayedWhenPrefIsYes() throws Exception {
		Link link = new Link(properties.get("viewpoints_tab"));
		link.click();
		Assert.assertTrue(viewAllViewpoints.verifyContentLevelIndicatorIsDisplayed());
		Assert.assertTrue(viewAllViewpoints.verifyContentLevelIndicatorHasAltText());
	}

	
	@Test(dependsOnMethods = { "verifyViewpointsContentLevelDisplayedWhenPrefIsYes" })
	public void searchValidationMessageIsDisplayedContentLevelOff() throws Exception {
		selenium.deleteAllVisibleCookies();
		startBrowserSession(properties.get("login.contentlevelovrc.off.username"), properties.get("login.contentlevelovrc.off.password"));
		doBasicSearchUsingSearchTerm("war");
	}

	@Test(dependsOnMethods = { "searchValidationMessageIsDisplayedContentLevelOff" })
	public void verifyNewsContentLevelDisplayedWhenPrefIsNo() throws Exception {
		Assert.assertTrue(viewAllNews.checkViewAllLinkIsWorking());
		Assert.assertFalse(viewAllNews.verifyContentLevelIndicatorIsDisplayed());
	}
	
	@Test(dependsOnMethods = { "verifyNewsContentLevelDisplayedWhenPrefIsNo" })
	public void verifyMagazineContentLevelDisplayedWhenPrefIsNo() throws Exception {
		Link link = new Link(properties.get("magazines_tab"));
		link.click();
		Assert.assertFalse(viewAllMagazines.verifyContentLevelIndicatorIsDisplayed());
	}
	
	@Test(dependsOnMethods = { "verifyMagazineContentLevelDisplayedWhenPrefIsNo" })
	public void verifyAcademicJournalsContentLevelDisplayedWhenPrefIsNo() throws Exception {
		Link link = new Link(properties.get("academicJournals_tab"));
		link.click();
		Assert.assertFalse(viewAllAcademicJournals.verifyContentLevelIndicatorIsDisplayed());
	}

	@Test(dependsOnMethods = { "verifyAcademicJournalsContentLevelDisplayedWhenPrefIsNo" })
	public void verifyAudioContentLevelDisplayedWhenPrefIsNo() throws Exception {
		Link link = new Link(properties.get("audio_tab"));
		link.click();
		Assert.assertFalse(viewAllAudio.verifyContentLevelIndicatorIsDisplayed());
	}

	@Test(dependsOnMethods = { "verifyAudioContentLevelDisplayedWhenPrefIsNo" })
	public void verifyPrimarySourcesContentLevelDisplayedWhenPrefIsNo() throws Exception {
		Link link = new Link(properties.get("primarySources_tab"));
		link.click();
		Assert.assertFalse(viewAllPrimarySources.verifyContentLevelIndicatorIsDisplayed());
	}

	@Test(dependsOnMethods = { "verifyPrimarySourcesContentLevelDisplayedWhenPrefIsNo" })
	public void verifyStatisticsContentLevelDisplayedWhenPrefIsNo() throws Exception {
		Link link = new Link(properties.get("statistics_tab"));
		link.click();
		Assert.assertFalse(viewAllStatistics.verifyContentLevelIndicatorIsDisplayed());
	}

	@Test(dependsOnMethods = { "verifyStatisticsContentLevelDisplayedWhenPrefIsNo" })
	public void verifyViewpointsContentLevelDisplayedWhenPrefIsNo() throws Exception {
		Link link = new Link(properties.get("viewpoints_tab"));
		link.click();
		Assert.assertFalse(viewAllViewpoints.verifyContentLevelIndicatorIsDisplayed());
	}
}
