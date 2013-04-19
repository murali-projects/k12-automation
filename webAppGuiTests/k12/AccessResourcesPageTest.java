package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import webPageContainers4Testing.AccessResourcesPage;

public class AccessResourcesPageTest extends BaseWebPageTest{
	
	private AccessResourcesPage accessResourcesPage;

	@BeforeTest
	public void setUp() throws Exception {
		accessResourcesPage = new AccessResourcesPage();
		accessResourcesPage.navigateToHomeTab();
	}
	
	@Test
	public void verifyResourcesLinkIsPresentAndAccesible() throws Exception {
		Assert.assertTrue(accessResourcesPage.verifyResourcesLinkIsPresentAndAccesible());
	}
	
	@Test(dependsOnMethods = { "verifyResourcesLinkIsPresentAndAccesible" })
	public void verifyTabsUnderResourceLink() throws Exception {
		Assert.assertTrue(accessResourcesPage.verifyTabsUnderResourceLink());
	}
	
	@Test(dependsOnMethods = { "verifyTabsUnderResourceLink" })
	public void verifyBasicSearchAndFooterArePresent() throws Exception {
		Assert.assertTrue(accessResourcesPage.verifyBasicSearchAndFooterArePresent());
	}
	
	@Test(dependsOnMethods = { "verifyBasicSearchAndFooterArePresent" })
	public void verifyOptionsForGettingStarted(String linksInResearchGuide) throws Exception {
		Assert.assertTrue(accessResourcesPage.verifyOptionsForGettingStarted());
	}
	
	@Test(dependsOnMethods = { "verifyOptionsForGettingStarted" })
	public void verifyOptionsForWrappingItUp() throws Exception {
		Assert.assertTrue(accessResourcesPage.verifyOptionsForWrappingItUp());
	}
	
	@Test(dependsOnMethods = { "verifyOptionsForWrappingItUp" })
	public void verifyOptionsForResearchGuide() throws Exception {
		Assert.assertTrue(accessResourcesPage.verifyOptionsForWrappingItUp());
	}
	
	@Test(dependsOnMethods = { "verifyOptionsForResearchGuide" })
	public void verifyBreadCrumb() throws Exception {
		Assert.assertTrue(accessResourcesPage.verifyBreadCrumb());
	}
}
