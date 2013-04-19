package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.ViewAllPagination;

public class ViewAllPaginationTest extends BaseWebPageTest {

	private ViewAllPagination viewAllPagination;
	private String displayGroup;

	@Parameters( { "searchTerm", "displayGroup" })
	@BeforeTest
	public void setUp(String searchTerm, String displayGroup) throws Exception {
		viewAllPagination = new ViewAllPagination(searchTerm);
		this.displayGroup = displayGroup;
	}

	@Parameters( { "searchTerm" })
	@Test
	public void basicNavigation(String searchTerm) throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		viewAllPagination.selectViewAllForDisplayGroup(displayGroup);
	}

	@Test(dependsOnMethods = { "basicNavigation" })
	public void verifyFirstFivePagesDisplayed() throws Exception {
		Assert.assertTrue(viewAllPagination.numberOfPagesDisplayed());
	}

	@Test(dependsOnMethods = { "verifyFirstFivePagesDisplayed" })
	public void verifyDisplayedPageNumbersAreLink() throws Exception {
		Assert.assertTrue(viewAllPagination.displayedPageNumbersAreLink());
	}

	@Test(dependsOnMethods = { "verifyDisplayedPageNumbersAreLink" })
	public void verifyCorrectPageNavigation() throws Exception {
		Assert.assertTrue(viewAllPagination.correctPageNavigation());
	}

	@Test(dependsOnMethods = { "verifyCorrectPageNavigation" })
	public void verifyCurrentPageVerification() throws Exception {
		Assert.assertTrue(viewAllPagination.currentPageVerification());
	}

	@Test(dependsOnMethods = { "verifyCurrentPageVerification" })
	public void verifyNextPreviousLinksVerification() throws Exception {
		Assert.assertTrue(viewAllPagination.nextPreviousLinksVerification());
	}

	@Test(dependsOnMethods = { "verifyNextPreviousLinksVerification" })
	public void verifyPreviousIsNotPresentInFirstPage() throws Exception {
		Assert.assertTrue(viewAllPagination
				.verifyPreviousIsNotPresentInFirstPage());
	}

	@Test(dependsOnMethods = { "verifyPreviousIsNotPresentInFirstPage" })
	public void validateRandomlyNavigatedContentFromDB() throws Exception {
		Assert.assertTrue(viewAllPagination
				.validateRandomlyNavigatedContentFromDB(displayGroup));
	}
	
	@Test(dependsOnMethods = { "validateRandomlyNavigatedContentFromDB" })
	public void verifyNextIsNotPresentInLastPage() throws Exception {
		Assert.assertTrue(viewAllPagination.verifyNextIsNotPresentInLastPage());
	}

	
}
