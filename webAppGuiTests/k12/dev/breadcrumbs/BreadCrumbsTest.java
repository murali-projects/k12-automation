package k12.dev.breadcrumbs;

import k12.dev.BaseDevWebPageTest;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import util.SeleniumSingletonFactory;
import webPageContainers4Testing.dev.ListViewPortlet;
import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;

public class BreadCrumbsTest  extends BaseDevWebPageTest{

	private static final String SEARCH_TERM = "War";
	
	private String homePageBreadCrumbTitle;
	
	private String searchResultsPageBreadCrumbTitle;
	
	private String viewAllPageBreadCrumbTitle;

	private String documentPageBreadCrumbTitle;
	
	private ListViewPortlet viewNewsDisplayGroup;

	@BeforeClass
	public void init() throws Exception {
		selenium = SeleniumSingletonFactory.getInstance();
	}
	
	@BeforeTest
	public void beforeTest() throws Exception {
		homePageBreadCrumbTitle =   properties.get("breadcrumb_homepage_title");
		searchResultsPageBreadCrumbTitle = properties.get("breadcrumb_searchresults_title");
		viewAllPageBreadCrumbTitle = properties.get("breadcrumb_viewall_title");
		documentPageBreadCrumbTitle = properties.get("breadcrumb_document_title");
		
		viewNewsDisplayGroup = new ListViewPortlet("news");
	}

	@Test
	public void isHomePageBreadCrumbPresent() throws Exception {
		if (isBefore("06/01/2010")) return;
		viewNewsDisplayGroup.openPage("homepage.url");
		TextLabel searchStatus = new TextLabel(properties.get("breadcrumb_xpath"));
		waitForElement(searchStatus.getLocator());
		Assert.assertTrue(searchStatus.getLabelText().contains(homePageBreadCrumbTitle));
	}
	
	@Test(dependsOnMethods = { "isHomePageBreadCrumbPresent" })
	public void searchValidTerm() throws Exception {
		if (isBefore("06/01/2010")) return;
		doBasicSearchUsingSearchTerm(SEARCH_TERM);
		TextLabel searchStatus = new TextLabel(properties.get("searchStatus_xpath"));
		Assert.assertTrue(searchStatus.getLabelText().contains(SEARCH_TERM));
	}
	
	@Test(dependsOnMethods = { "searchValidTerm" })
	public void isSearchResultsBreadCrumbPresent() throws Exception {
		if (isBefore("06/01/2010")) return;
		TextLabel searchStatus = new TextLabel(properties.get("breadcrumb_xpath"));
		waitForElement(searchStatus.getLocator());
		Assert.assertTrue(searchStatus.getLabelText().contains(searchResultsPageBreadCrumbTitle),"Search status text " + searchStatus.getLabelText());
	}

	
	@Test(dependsOnMethods = { "isSearchResultsBreadCrumbPresent" })
	public void doesPortletHaveResults() throws Exception {
		if (isBefore("06/01/2010")) return;
		waitABit();
		Assert.assertTrue(viewNewsDisplayGroup.checkArticleLinkCount());
	}

	@Test(dependsOnMethods = { "doesPortletHaveResults" })
	public void viewAllLinkIsDisplayed() throws Exception {
		if (isBefore("06/01/2010")) return;
		waitABit();
		Assert.assertTrue(viewNewsDisplayGroup.checkViewAllLinkIsPresent());
	}

	@Test(dependsOnMethods = { "viewAllLinkIsDisplayed" })
	public void isViewAllBreadCrumbPresent() throws Exception {
		if (isBefore("06/01/2010")) return;
		viewNewsDisplayGroup.goToViewAllPage();
		waitABit();
		TextLabel searchStatus = new TextLabel(properties.get("breadcrumb_xpath"));
		waitForElement(searchStatus.getLocator());
		Assert.assertTrue(searchStatus.getLabelText().contains(viewAllPageBreadCrumbTitle));
	}
	
	@Test(dependsOnMethods = { "isViewAllBreadCrumbPresent" })
	public void isDocumentBreadCrumbPresent() throws Exception {
		if (isBefore("06/01/2010")) return;
		Link firstDocument = new Link(properties.get("view_all_document_title") + "[1]/h3/a");
		firstDocument.click();
		TextLabel searchStatus = new TextLabel(properties.get("breadcrumb_xpath"));
		waitForElement(searchStatus.getLocator());
		Assert.assertTrue(searchStatus.getLabelText().contains(documentPageBreadCrumbTitle));
	}
	
	@Test(dependsOnMethods = { "isDocumentBreadCrumbPresent" })
	public void isViewAllBreadCrumbWorking() throws Exception {
		if (isBefore("06/01/2010")) return;
		String viewAllLinkLocator = properties.get("breadcrumb_viewall_link");
		waitForElement(viewAllLinkLocator);
		Link viewAllLink = new Link(viewAllLinkLocator);
		viewAllLink.click();
		String breadcrumbLocator = properties.get("breadcrumb_xpath");
		waitForElement(breadcrumbLocator);
		TextLabel documentLabel = new TextLabel(breadcrumbLocator);
		Assert.assertTrue(!documentLabel.getLabelText().contains(documentPageBreadCrumbTitle));
	}

	@Test(dependsOnMethods = { "isViewAllBreadCrumbWorking" })
	public void isSearchResultsBreadCrumbWorking() throws Exception {
		if (isBefore("06/01/2010")) return;
		String breadcrumbSearchResultsLink = properties.get("breadcrumb_searchresults_link");
		waitForElement(breadcrumbSearchResultsLink);
		Link searchResultsLink = new Link(breadcrumbSearchResultsLink);
		searchResultsLink.click();
		TextLabel viewallLabel = new TextLabel(properties.get("breadcrumb_xpath"));
		waitForElement(viewallLabel.getLocator());
		Assert.assertTrue(!viewallLabel.getLabelText().contains(viewAllPageBreadCrumbTitle));
	}

	@Test(dependsOnMethods = { "isSearchResultsBreadCrumbWorking" })
	public void isHomeBreadCrumbWorking() throws Exception {
		if (isBefore("06/01/2010")) return;
		Link homePageLink = new Link(properties.get("breadcrumb_homepage_link"));
		homePageLink.click();
		TextLabel searchResultsLabel = new TextLabel(properties.get("breadcrumb_xpath"));
		waitForElement(searchResultsLabel.getLocator());
		Assert.assertTrue(!searchResultsLabel.getLabelText().contains(searchResultsPageBreadCrumbTitle));
	}
}
