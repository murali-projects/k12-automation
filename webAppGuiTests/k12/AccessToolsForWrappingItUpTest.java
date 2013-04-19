package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import webPageContainers4Testing.AccessToolsForWrappingItUp;

public class AccessToolsForWrappingItUpTest extends BaseWebPageTest{
	private AccessToolsForWrappingItUp toolsForWrappingItUp;

	@BeforeTest
	public void setUp() throws Exception {
		toolsForWrappingItUp = new AccessToolsForWrappingItUp();
	}

	@Test
	public void verifyResourcesLinkIsAccessible() throws Exception {
		Assert.assertTrue(toolsForWrappingItUp.verifyResourcesLinkIsAccessible());
	}

	@Test(dependsOnMethods = { "verifyResourcesLinkIsAccessible" })
	public void checkDetailsDisplayedInWrappingItUp() throws Exception {
		Assert.assertTrue(toolsForWrappingItUp.checkDetailsDisplayedInWrappingItUp());
	}
	
	@Test(dependsOnMethods = { "checkDetailsDisplayedInWrappingItUp" })
	public void verifyBreadcrumbInResourcesPage() throws Exception {
		Assert.assertTrue(toolsForWrappingItUp.verifyBreadcrumbInResourcesPage());
	}
	
	@Test(dependsOnMethods = { "verifyBreadcrumbInResourcesPage" })
	public void verifyWrappingUpLinksOpensInNewWindow() throws Exception {
		Assert.assertTrue(toolsForWrappingItUp.verifyWrappingUpLinksOpensInNewWindow());
	}

	@Test(dependsOnMethods = { "verifyWrappingUpLinksOpensInNewWindow" })
	public void verifyUrlOfNewWindow() throws Exception {
		Assert.assertTrue(toolsForWrappingItUp.verifyUrlOfNewWindow());
	}
	
	@Test(dependsOnMethods = { "verifyUrlOfNewWindow" })
	public void verifyOtherToolsArePresent() throws Exception {
		Assert.assertTrue(toolsForWrappingItUp.verifyOtherToolsArePresent());
	}
	
	@Test(dependsOnMethods = { "verifyBreadcrumbInResourcesPage" })
	public void verifyFooterLinksAnBasicSearchAccessible() throws Exception {
		Assert.assertTrue(toolsForWrappingItUp.verifyFooterLinksAnBasicSearchAccessible());
	}
}
