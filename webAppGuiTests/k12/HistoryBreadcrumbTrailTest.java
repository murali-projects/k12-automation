package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.HistoryBreadcrumbTrail;
import k12.BaseWebPageTest;

public class HistoryBreadcrumbTrailTest extends BaseWebPageTest {

	private HistoryBreadcrumbTrail historyBreadcrumbTrail;

	@Parameters( { "searchTerm" })
	@BeforeTest
	public void setUp(String searchTerm) throws Exception {
		historyBreadcrumbTrail = new HistoryBreadcrumbTrail();
		doBasicSearchUsingSearchTerm(searchTerm);
	}

	@Test
	public void verifyBreadcrumbIsDisplayed() throws Exception {
		Assert.assertTrue(historyBreadcrumbTrail.verifyBreadcrumbIsDisplayed());
	}

	@Test(dependsOnMethods = { "verifyBreadcrumbIsDisplayed" })
	public void verifyUserNavigateToHomePage() throws Exception {
		Assert
				.assertTrue(historyBreadcrumbTrail
						.verifyUserNavigateToHomePage());
	}

	@Parameters( { "displayGroup" })
	@Test(dependsOnMethods = { "verifyUserNavigateToHomePage" })
	public void verifyThreeLinksDisplayed(String displayGroup) throws Exception {
		Assert.assertTrue(historyBreadcrumbTrail
				.verifyThreeLinksDisplayed(displayGroup));
	}

	@Test(dependsOnMethods = { "verifyThreeLinksDisplayed" })
	public void verifyUserAcessToLinks() throws Exception {
		Assert.assertTrue(historyBreadcrumbTrail.verifyUserAcessToLinks());
	}

	@Test(dependsOnMethods = { "verifyUserAcessToLinks" })
	public void verifyContentInPage() throws Exception {
		Assert.assertTrue(historyBreadcrumbTrail.verifyContentInPage());
	}

	@Test(dependsOnMethods = { "verifyContentInPage" })
	public void verifySearchBoxIsField() throws Exception {
		Assert.assertTrue(historyBreadcrumbTrail.verifySearchBoxIsField());
	}

	@Parameters( { "displayGroup" })
	@Test(dependsOnMethods = { "verifySearchBoxIsField" })
	public void verifyDynamicChangeOfBreadcrumb(String displayGroup)
			throws Exception {
		Assert.assertTrue(historyBreadcrumbTrail
				.verifyDynamicChangeOfBreadcrumb(displayGroup));
	}

	@Test(dependsOnMethods = { "verifyDynamicChangeOfBreadcrumb" })
	public void verifyAccessToFiveLinks() throws Exception {
		Assert.assertTrue(historyBreadcrumbTrail.verifyAccessToFiveLinks());
	}

	@Test(dependsOnMethods = { "verifyAccessToFiveLinks" })
	public void verifyLinksForBreadcrumb() throws Exception {
		Assert.assertTrue(historyBreadcrumbTrail.verifyLinksForBreadcrumb());
	}
}
