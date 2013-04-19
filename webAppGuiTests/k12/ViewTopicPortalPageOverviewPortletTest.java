package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.ViewTopicPortalPageOverviewPortlet;

public class ViewTopicPortalPageOverviewPortletTest extends BaseWebPageTest {

	public ViewTopicPortalPageOverviewPortlet viewtopicportalpageoverview;

	@Parameters( { "searchTerm" })
	@BeforeTest
	public void setUp(String searchTerm) throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		viewtopicportalpageoverview = new ViewTopicPortalPageOverviewPortlet();
	}

	@Parameters( { "searchTerm" })
	@Test
	public void verifyNavigationByclickingoOnTopics(String searchTerm)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(viewtopicportalpageoverview
				.verifyNavigationByclickingoOnTopics());

	}

	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods = "verifyNavigationByclickingoOnTopics")
	public void verifyOverviewTextWithDocumentText(String searchTerm)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(viewtopicportalpageoverview
				.verifyOverviewTextWithDocumentText());
	}

	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods = "verifyOverviewTextWithDocumentText")
	public void verifyTopicMatchesTitle(String searchTerm) throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert
				.assertTrue(viewtopicportalpageoverview
						.verifyTopicMatchesTitle());
	}

	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods = "verifyOverviewTextWithDocumentText")
	public void verifyOverviewDisplayed(String searchTerm) throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert
				.assertTrue(viewtopicportalpageoverview
						.verifyOverviewDisplayed());
	}

	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods = "verifyOverviewDisplayed")
	public void verifyElipsisPresent(String searchTerm) throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(viewtopicportalpageoverview.verifyElipsisPresent());
	}

	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods = "verifyElipsisPresent")
	public void verifyViewMoreOptionAvailable(String searchTerm)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(viewtopicportalpageoverview
				.verifyViewMoreOptionAvailable());
	}

	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods = "verifyViewMoreOptionAvailable")
	public void verifyNavigationByclickingViewMore(String searchTerm)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(viewtopicportalpageoverview
				.verifyNavigationByclickingViewMore());
	}

	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods = "verifyNavigationByclickingViewMore")
	public void verifyOverviewTextWithDetailedPageText(String searchTerm)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(viewtopicportalpageoverview
				.verifyOverviewTextWithDetailedPageText());
	}

	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods = "verifyOverviewTextWithDetailedPageText")
	public void verifyBreadCrumb(String searchTerm) throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(viewtopicportalpageoverview.verifyBreadCrumb());
	}

	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods = "verifyBreadCrumb")
	public void verifyToolsAvailableInDetailPage(String searchTerm)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(viewtopicportalpageoverview
				.verifyToolsAvailableInDetailPage());
	}

	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods = "verifyToolsAvailableInDetailPage")
	public void verifyComparisionOfOverviewWithTwoTopics(String searchTerm)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(viewtopicportalpageoverview
				.verifyComparisionOfOverviewWithTwoTopics());
	}

}
