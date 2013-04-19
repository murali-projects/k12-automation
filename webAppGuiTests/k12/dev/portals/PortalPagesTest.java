package k12.dev.portals;

import k12.dev.BaseDevWebPageTest;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import webPageContainers4Testing.dev.PortalPortlet;
import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;

public class PortalPagesTest extends BaseDevWebPageTest {

	private static String SEARCH_TERM_PORTAL_PAGE_PORTLET = "contraception";

	private PortalPortlet viewPortalPagesDisplayGroup;

	@BeforeTest
	public void setUp() throws Exception {
		viewPortalPagesDisplayGroup = new PortalPortlet();
	}

	@Test
	public void searchValidationMessageIsDisplayed() throws Exception {
		doBasicSearchUsingSearchTerm(SEARCH_TERM_PORTAL_PAGE_PORTLET);
	}

	@Test(dependsOnMethods = { "searchValidationMessageIsDisplayed" })
	public void isPortalPresent() throws Exception {
		Assert.assertTrue(viewPortalPagesDisplayGroup.checkPortalIsPresent());
	}

	@Test(dependsOnMethods = { "isPortalPresent" })
	public void isPortalPageTitlePresent() throws Exception {
		Assert.assertTrue(viewPortalPagesDisplayGroup.checkPortalPageTitlePresent());
	}

	@Test(dependsOnMethods = { "isPortalPageTitlePresent" })
	public void isOverviewPresent() throws Exception {
		TextLabel content = new TextLabel(viewPortalPagesDisplayGroup.getProperty("portal_overview_div"));

		Assert.assertTrue(content.isPresent());
	}
	
	@Test(dependsOnMethods = { "isOverviewPresent" })
	public void isFooterPresent() throws Exception {
		Assert.assertTrue(viewPortalPagesDisplayGroup.doesContentExistInClass(viewPortalPagesDisplayGroup
				.getProperty("footerDiv"), "footerGaleLogo"));
	}
	
	@Test (dependsOnMethods = {"isFooterPresent"})
	public void overviewGoesToDocDisplay() throws Exception {
		TextLabel linkContent = new TextLabel(viewPortalPagesDisplayGroup.getProperty("portal_overview_view_more"));
		String href = linkContent.getAttribute("href");
		String expectedDocId = href.substring(href.indexOf("documentId=")+11);
		viewPortalPagesDisplayGroup.clickLink("portal_overview_view_more");
		
		TextLabel docNumberContent = new TextLabel(viewPortalPagesDisplayGroup.getProperty("gale_document_number_xpath"));
		String actualDocId = docNumberContent.getLabelText();
		Assert.assertTrue(actualDocId.contains(expectedDocId));
	}
	
	@Test (dependsOnMethods= {"overviewGoesToDocDisplay"})
	public void featuredImagePresent() throws Exception {
		if (isBefore("04/29/2010")) return;
		TextLabel title = new TextLabel(viewPortalPagesDisplayGroup.getProperty("portal_featuredimage_title"));
		Assert.assertTrue(title.isPresent());
		Link detailsLink = new Link(viewPortalPagesDisplayGroup.getProperty("portal_featuredimage_link"));
		Assert.assertTrue(detailsLink.isPresent());
	}
}
