package k12.dev.portals;

import k12.dev.BaseDevWebPageTest;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import webPageContainers4Testing.dev.PortalPortlet;

public class EditorPicksPortletTest extends BaseDevWebPageTest {
	
	private PortalPortlet viewPortalPagesDisplayGroup;

	@BeforeTest
	public void setUp() throws Exception {
		viewPortalPagesDisplayGroup = new PortalPortlet();
	}

	@Test
	public void searchValidationMessageIsDisplayed() throws Exception {
		viewPortalPagesDisplayGroup.openPage("adoption.portalpage");		
	}

	@Test(dependsOnMethods = { "searchValidationMessageIsDisplayed" })
	public void isPortalPresent() throws Exception {
		Assert.assertTrue(viewPortalPagesDisplayGroup.checkPortalIsPresent());
	}

	@Test(dependsOnMethods = { "isPortalPresent" })
	public void isPortalPageTitlePresent() throws Exception {
		Assert.assertTrue(viewPortalPagesDisplayGroup.checkPortalPageTitlePresent());
	}
	
	@Test(dependsOnMethods = {"isPortalPageTitlePresent"})
	public void isRelatedPortalPortletTitlePresent() throws Exception {
		Assert.assertTrue(viewPortalPagesDisplayGroup.doesContentExistInId("expertpicks", "allPortal_content_text_xpath"));
		//Assert.assertTrue(viewPortalPagesDisplayGroup.checkSpecificContentByClass("relatedPortal", ""));
	}
	
	@Test(dependsOnMethods = {"isRelatedPortalPortletTitlePresent"})
	public void isRelatedPortalLinPresent() throws Exception {
		Assert.assertTrue(viewPortalPagesDisplayGroup.linkExists("expertpicks_first_link"));
	}
	
	@Test(dependsOnMethods = {"isRelatedPortalPortletTitlePresent"})
	public void isRelatedPortalLinkWorking() throws Exception {
		Assert.assertTrue(viewPortalPagesDisplayGroup.linkIsWorking("expertpicks_first_link"));
	}
}	


