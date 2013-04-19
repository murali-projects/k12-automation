package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.SortByPublicationTitle;

public class SortByPublicatonTitleTest extends BaseWebPageTest {

	private SortByPublicationTitle sortByPublicationTitle;
	private String displayGroupName;

	@Parameters( { "searchTerm", "displayGroupName" })
	@BeforeTest
	public void setUp(String searchTerm, String displayGroupName)
			throws Exception {
		sortByPublicationTitle = new SortByPublicationTitle(searchTerm,displayGroupName);
		this.displayGroupName = displayGroupName;
	}

	@Parameters( { "searchTerm" })
	@Test
	public void basicSearchAndGetIntialValues(String searchTerm) throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		sortByPublicationTitle.getIntialValues();
	}

	@Test
	// @Test(dependsOnMethods = { "basicSearch" })
	public void verifyContentValuesWithDB() throws Exception {
		Assert.assertTrue(sortByPublicationTitle.ensureDefaultSort(
				displayGroupName, "documentTitle"));
	}

	@Test
	// @Test(dependsOnMethods = { "verifyContentValuesWithDB" })
	public void validateSearchResultsByPublicationTitleWithDB()
			throws Exception {
		Assert.assertTrue(sortByPublicationTitle
				.validateSearchResultsByPublicationTitleWithDB(
						displayGroupName, "publicationTitle"));
	}

	@Test
	// @Test(dependsOnMethods = {
	// "validateSearchResultsByPublicationTitleWithDB" })
	public void checkDisplayCountAfterSorting() throws Exception {
		Assert.assertTrue(sortByPublicationTitle
				.checkDisplayCountAfterSorting());
	}

	@Test
	// @Test(dependsOnMethods = { "checkDisplayCountAfterSorting" })
	public void checkViewAllDisplayCountAfterSorting() throws Exception {
		Assert.assertTrue(sortByPublicationTitle
				.checkViewAllDisplayCountAfterSorting());
	}

	@Test
	// @Test(dependsOnMethods = { "checkViewAllDisplayCountAfterSorting" })
	public void verifySortOrderAfterClickingViewAll() throws Exception {
		Assert.assertTrue(sortByPublicationTitle
				.verifySortOrderAfterClickingViewAll(displayGroupName,
						"publicationTitle"));
	}
	
	@Test
	// @Test(dependsOnMethods = { "verifySortOrderAfterClickingViewAll" })
	public void validateSortingAfterClickingNext() throws Exception {
		Assert.assertTrue(sortByPublicationTitle
				.validateSortingAfterClickingNext(displayGroupName,
						"publicationTitle"));
	}
	
	@Test
	// @Test(dependsOnMethods = { "validateSortingAfterClickingNext" })
	public void validateSortingAfterClickingPrevious() throws Exception {
		Assert.assertTrue(sortByPublicationTitle
				.validateSortingAfterClickingPrevious(displayGroupName,
						"publicationTitle"));
	}
	
	@Test
	// @Test(dependsOnMethods = { "validateSortingAfterClickingPrevious" })
	public void validateSortingForBreadCrumbNavigation() throws Exception {
		Assert.assertTrue(sortByPublicationTitle
				.validateSortingAfterClickingPrevious(displayGroupName,
						"publicationTitle"));
	}

}
