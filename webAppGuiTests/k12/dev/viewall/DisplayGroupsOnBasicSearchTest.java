package k12.dev.viewall;

import k12.dev.BaseDevWebPageTest;

import static org.testng.Assert.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import util.SeleniumSingletonFactory;
import webPageContainers4Testing.dev.ListViewPortlet;
import webPageContainers4Testing.dev.ViewFullListPortlet;

public class DisplayGroupsOnBasicSearchTest extends BaseDevWebPageTest {

	private ViewFullListPortlet viewAllviewPoints;
	private ViewFullListPortlet viewAllNews;
	private ListViewPortlet viewNewsDisplayGroup;

	@BeforeClass
	public void init() throws Exception {
		selenium = SeleniumSingletonFactory.getInstance();
	}

	@BeforeTest
	public void setUp() throws Exception {
		viewAllviewPoints = new ViewFullListPortlet("viewpoints");
		viewAllNews = new ViewFullListPortlet("news");
		viewNewsDisplayGroup = new ListViewPortlet("news");

	}

	@Test
	public void displayGroupSearchIsWorkingForViewPoints() throws Exception {
		doDisplayGroupSearchUsingSearchTerm("war", "Viewpoints");
		assertTrue(viewAllviewPoints.checkViewAllIsNavigatedCorrectly());
	}
	
	@Test(dependsOnMethods = { "displayGroupSearchIsWorkingForViewPoints" })
	public void displayGroupSearchIsWorkingForNews() throws Exception {
		doDisplayGroupSearchMoreUsingSearchTerm("war", "News");
		viewAllNews.checkViewAllIsNavigatedCorrectly();
	}
	
	@Test(dependsOnMethods = { "displayGroupSearchIsWorkingForNews" })
	public void displayGroupSearchIsWorkingForAll() throws Exception {
		doDisplayGroupSearchAllUsingSearchTerm("war","All","Viewpoints");
		assertTrue(viewNewsDisplayGroup.checkDisplayGroupIsPresent());
	}

}
