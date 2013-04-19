package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.UserSort;

public class UserSortTest extends BaseWebPageTest {

	private UserSort sortByDropDownInViewAllPage;
	private String displayGroup;
	private String publicationDateSortElement;
	private String relevanceSortElement;

	@Parameters( { "searchTerm", "displayGroup", "publicationDateSortElement",
			"relevanceSortElement" })
	@BeforeTest
	public void setUp(String searchTerm, String displayGroup,
			String publicationDateSortElement, String relevanceSortElement) throws Exception {
		sortByDropDownInViewAllPage = new UserSort(
				searchTerm);
		this.displayGroup = displayGroup;
		this.publicationDateSortElement = publicationDateSortElement;
		this.relevanceSortElement = relevanceSortElement;
	}
	
	@Parameters( { "searchTerm" })
	@Test
	public void basicSearch(String searchTerm) throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
	}

	@Test
	public void sortDisplayGroupByDescendingPublicationDate() throws Exception {
		sortByDropDownInViewAllPage.selectSortElement(publicationDateSortElement);
		Assert.assertTrue(sortByDropDownInViewAllPage
				.validateDisplayGroupByDescendingPublicationDate(displayGroup));
	}

	@Test
	public void sortDisplayGroupByDescendingRelevance() throws Exception {
		sortByDropDownInViewAllPage.selectSortElement(relevanceSortElement);
		Assert.assertTrue(sortByDropDownInViewAllPage
				.validateDisplayGroupByDescendingRelevance(displayGroup));
	}

	@Test
	public void sortDisplayGroupByAscendingDocumentTitle() throws Exception {
		sortByDropDownInViewAllPage.selectSortElement(relevanceSortElement);
		Assert.assertTrue(sortByDropDownInViewAllPage
				.verifyResultsAfterSortingByDocumentTitle(displayGroup));
	}
}
