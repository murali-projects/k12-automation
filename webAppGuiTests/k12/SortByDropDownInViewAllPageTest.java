package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.SortByDropDownInViewAllPage;

public class SortByDropDownInViewAllPageTest extends BaseWebPageTest {

	private SortByDropDownInViewAllPage sortByDropDownInViewAllPage;
	private String displayGroup;
	private String[] requiredContents;
	private String[] options;
	private String sortElement;
	private String publicationDateSortElement;
	private String relevanceSortElement;

	@Parameters( { "searchTerm", "displayGroup", "requiredContent", "option",
			"sortElement", "publicationDateSortElement", "relevanceSortElement" })
	@BeforeTest
	public void setUp(String searchTerm, String displayGroup,
			String requiredContent, String option, String sortElement,
			String publicationDateSortElement, String relevanceSortElement)
			throws Exception {
		sortByDropDownInViewAllPage = new SortByDropDownInViewAllPage(
				searchTerm);
		this.displayGroup = displayGroup;
		requiredContents = requiredContent.split(",");
		this.options = option.split(",");
		this.sortElement = sortElement;
		this.publicationDateSortElement = publicationDateSortElement;
		this.relevanceSortElement = relevanceSortElement;
	}

	@Parameters( { "searchTerm" })
	@Test
	public void basicNavigation(String searchTerm) throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		sortByDropDownInViewAllPage.selectViewAllForDisplayGroup(displayGroup);
	}

	@Test
//	@Test(dependsOnMethods = { "basicNavigation" })
	public void defaultSortForReferenceDisplayGroup() throws Exception {
		Assert.assertTrue(sortByDropDownInViewAllPage
				.validateDefaultSort(displayGroup));
	}

	@Test
//	@Test(dependsOnMethods = { "defaultSortForReferenceDisplayGroup" })
	public void sortDisplayGroupByDescendingPublicationDate() throws Exception {
		sortByDropDownInViewAllPage
				.selectSortElement(publicationDateSortElement);
		Assert.assertTrue(sortByDropDownInViewAllPage
				.validateDisplayGroupByDescendingPublicationDate(displayGroup));
	}
	
	@Test
//	@Test(dependsOnMethods = { "sortDisplayGroupByDescendingPublicationDate" })
	public void sortDisplayGroupByDescendingRelevance() throws Exception {
		sortByDropDownInViewAllPage.selectSortElement(relevanceSortElement);
		Assert.assertTrue(sortByDropDownInViewAllPage
				.validateDisplayGroupByDescendingRelevance(displayGroup));
	}

	@Test
//	@Test(dependsOnMethods = { "sortDisplayGroupByDescendingRelevance" })
	public void sortDisplayGroupByAscendingDocumentTitle() throws Exception {
		sortByDropDownInViewAllPage.selectSortElement(relevanceSortElement);
		Assert.assertTrue(sortByDropDownInViewAllPage
				.verifyResultsAfterSortingByDocumentTitle(displayGroup));
	}

	@Test
//	@Test(dependsOnMethods = { "sortDisplayGroupByAscendingDocumentTitle" })
	public void validateDefaultSortAfterViewAll() throws Exception {
		sortByDropDownInViewAllPage.selectSortElement(sortElement);
		Assert.assertTrue(sortByDropDownInViewAllPage
				.validateDefaultSortAfterViewAll(displayGroup));
	}

	@Test
//	@Test(dependsOnMethods = { "validateDefaultSortAfterViewAll" })
	public void ensureSortAfterClickingViewAll() throws Exception {
		sortByDropDownInViewAllPage.selectSortElement(sortElement);
		Assert.assertTrue(sortByDropDownInViewAllPage
				.ensureSortAfterClickingViewAll(displayGroup));
	}

	@Test
//	@Test(dependsOnMethods = { "ensureSortAfterClickingViewAll" })
	public void verifyCountAfterSorting() throws Exception {
		Assert.assertTrue(sortByDropDownInViewAllPage
				.verifyCountAfterSorting(sortElement));
	}

	@Test
//	@Test(dependsOnMethods = { "verifyCountAfterSorting" })
	public void ensureSortDownContent() throws Exception {
		Assert.assertTrue(sortByDropDownInViewAllPage
				.ensureSortDownContent(requiredContents));
	}

	@Test
//	@Test(dependsOnMethods = { "ensureSortDownContent" })
	public void ensureSortDownCountAfterNext() throws Exception {
		Assert.assertTrue(sortByDropDownInViewAllPage
				.ensureSortDownCountAfterNext(requiredContents));
	}

	@Test
//	@Test(dependsOnMethods = { "ensureSortDownCountAfterNext" })
	public void ensureSortDownCountAfterPrevious() throws Exception {
		Assert.assertTrue(sortByDropDownInViewAllPage
				.ensureSortDownCountAfterPrevious(requiredContents));
	}

	@Test
//	@Test(dependsOnMethods = { "ensureSortDownCountAfterPrevious" })
	public void selectMultipleItemsFromDropDown() throws Exception {
		Assert.assertTrue(sortByDropDownInViewAllPage
				.selectMultipleItemsFromDropDown(options));
	}

	@Test
//	@Test(dependsOnMethods = { "selectMultipleItemsFromDropDown" })
	public void verifyDocInfoTypeAfterSort() throws Exception {
		Assert.assertTrue(sortByDropDownInViewAllPage
				.verifyDocInfoTypeAfterSort(sortElement));
	}

}
