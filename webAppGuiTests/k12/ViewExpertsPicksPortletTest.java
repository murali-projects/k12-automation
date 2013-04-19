package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.ViewExpertsPicksPortlet;

public class ViewExpertsPicksPortletTest extends BaseWebPageTest{
	private ViewExpertsPicksPortlet expertsPicksPortlet;

	@Parameters({"searchTerm"})
	@BeforeTest
	public void setUp(String searchTerm) throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		expertsPicksPortlet = new ViewExpertsPicksPortlet();
	}

	@Test
	public void verifyTopicsAreNotMoreThan10() throws Exception {
		Assert.assertTrue(expertsPicksPortlet.verifyTopicsCount());
	}
	
	@Test(dependsOnMethods = {"verifyTopicsAreNotMoreThan10"})
	public void verifyViewAllNotDisplayed() throws Exception {
		Assert.assertTrue(expertsPicksPortlet.verifyElementNotPresent("viewAllLink"));
	}
	
	@Test(dependsOnMethods = {"verifyViewAllNotDisplayed"})
	public void verifyHitCountNotDisplayed() throws Exception {
		Assert.assertTrue(expertsPicksPortlet.verifyElementNotPresent("topicsCount"));
	}
	
	@Test(dependsOnMethods = {"verifyHitCountNotDisplayed"})
	public void verifyTopicsAreAccessible() throws Exception {
		Assert.assertTrue(expertsPicksPortlet.verifyTopicsAreAccessible());
	}
	
	@Test(dependsOnMethods = {"verifyTopicsAreAccessible"})
	public void verifyTopicOverviewIsDisplayed() throws Exception {
		Assert.assertTrue(expertsPicksPortlet.verifyTopicOverviewIsDisplayed());
	}
	
	@Test(dependsOnMethods = {"verifyTopicOverviewIsDisplayed"})
	public void verifyViewFullOverviewLinkIsDisplayed() throws Exception {
		Assert.assertTrue(expertsPicksPortlet.verifyViewFullOverviewLinkIsDisplayed());
	}
	
	@Test(dependsOnMethods = {"verifyViewFullOverviewLinkIsDisplayed"})
	public void verifyViewFullOverviewLinkIsAccessible() throws Exception {
		Assert.assertTrue(expertsPicksPortlet.verifyViewFullOverviewLinkIsAccessible());
	}
	
	@Test(dependsOnMethods = {"verifyViewFullOverviewLinkIsAccessible"})
	public void verifyOtherTopicLinksNotPresent() throws Exception {
		Assert.assertTrue(expertsPicksPortlet.verifyOtherTopicLinksNotPresent());
	}
}
