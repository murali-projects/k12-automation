package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.AccessResearchGuide;

public class AccessResearchGuideTest extends BaseWebPageTest{
	
	private AccessResearchGuide accessResearchGuide;

	@BeforeTest
	public void setUp() throws Exception {
		accessResearchGuide = new AccessResearchGuide();
		accessResearchGuide.navigateToHomeTab();
	}
	
	@Test
	public void verifyResearchToolsOption() throws Exception {
		Assert.assertTrue(accessResearchGuide.verifyResearchToolsOption());
	}
	
	@Test(dependsOnMethods = { "verifyResearchToolsOption" })
	public void verifyResearchToolsPage() throws Exception {
		Assert.assertTrue(accessResearchGuide.verifyResearchToolsPage());
	}
	
	@Test(dependsOnMethods = { "verifyResearchToolsPage" })
	public void verifyResearchGuideTab() throws Exception {
		Assert.assertTrue(accessResearchGuide.verifyResearchGuideTab());
	}
	
	@Parameters( { "linksInResearchGuide" })
	@Test(dependsOnMethods = { "verifyResearchGuideTab" })
	public void verifyResearchGuideLinks(String linksInResearchGuide) throws Exception {
		Assert.assertTrue(accessResearchGuide.verifyResearchGuideLinks(linksInResearchGuide));
	}
	
	@Test(dependsOnMethods = { "verifyResearchGuideLinks" })
	public void verifyEachLinkAccessbility() throws Exception {
		Assert.assertTrue(accessResearchGuide.verifyEachLinkAccessbility());
	}
}
