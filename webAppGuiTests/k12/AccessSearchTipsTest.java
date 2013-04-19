package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import webPageContainers4Testing.AccessSearchTips;

public class AccessSearchTipsTest extends BaseWebPageTest{
	private AccessSearchTips accessSearchTips;

	@BeforeTest
	public void setUp() throws Exception {
		accessSearchTips = new AccessSearchTips();
	}

	@Test
	public void verifyResourcesLinkIsAccessible() throws Exception {
		Assert.assertTrue(accessSearchTips.verifyResourcesLinkIsAccessible());
	}

	@Test(dependsOnMethods = { "verifyResourcesLinkIsAccessible" })
	public void verifySearchTipsPageDisplayed() throws Exception {
		Assert.assertTrue(accessSearchTips.verifySearchTipsPageDisplayed());
	}
	
	@Test(dependsOnMethods = { "verifySearchTipsPageDisplayed" })
	public void verifySearchTipsOpensInNewWindow() throws Exception {
		Assert.assertTrue(accessSearchTips.verifySearchTipsOpensInNewWindow());
	}
	
	@Test(dependsOnMethods = { "verifySearchTipsOpensInNewWindow" })
	public void verifySpeakerIconsAvailableInSearchTipsPage() throws Exception {
		Assert.assertTrue(accessSearchTips.verifySpeakerIconsAvailableInSearchTipsPage());
	}

	@Test(dependsOnMethods = { "verifySpeakerIconsAvailableInSearchTipsPage" })
	public void verifySearchTipsWindowCanBeClosed() throws Exception {
		Assert.assertTrue(accessSearchTips.verifySearchTipsWindowCanBeClosed());
	}
	
	@Test(dependsOnMethods = { "verifySearchTipsWindowCanBeClosed" })
	public void verifyBreadcrumbInResourcesPage() throws Exception {
		Assert.assertTrue(accessSearchTips.verifyBreadcrumbInResourcesPage());
	}
	
	@Test(dependsOnMethods = { "verifyBreadcrumbInResourcesPage" })
	public void verifyHomePageNavigationViaBreadcrumb() throws Exception {
		Assert.assertTrue(accessSearchTips.verifyHomePageNavigationViaBreadcrumb());
	}
}
