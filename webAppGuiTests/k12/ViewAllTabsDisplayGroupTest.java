package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.ViewAllTabsDisplayGroup;

public class ViewAllTabsDisplayGroupTest extends BaseWebPageTest {
	private ViewAllTabsDisplayGroup tabDisplayGroups;
	public String sortBy;
	public String dataBaseElement;
	public String searchTerm;
	public String displayName;
	private String contentGroup;


	@Parameters( { "searchTerm", "sortBy", "dataBaseElement", "displayName","contentGroup" })
	@BeforeTest
	public void setUp(String searchTerm, String sortBy, String dataBaseElement,
			String displayName,String contentGroup) throws Exception {
		tabDisplayGroups = new ViewAllTabsDisplayGroup();
		this.searchTerm = searchTerm;
		this.sortBy = sortBy;
		this.dataBaseElement = dataBaseElement;
		this.displayName = displayName;
		this.contentGroup=contentGroup;
	}

	@Test
	public void viewDisplayGroupsForSeachTerm() throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(tabDisplayGroups.validateSearchContent());
	}

	@Parameters( { "groupName","contentGroup"})
	@Test(dependsOnMethods = { "viewDisplayGroupsForSeachTerm" })
	public void validateDisplayGroupContent(String groupName,
			String contentGroup)
			throws Exception {
		Assert.assertTrue(tabDisplayGroups
				.validateContentForDisplayGroupWithOcean(groupName,
						displayName, contentGroup, sortBy, dataBaseElement,
						searchTerm));
	}

	@Test(dependsOnMethods = { "validateDisplayGroupContent" })
	public void validateDisplayGroupCount() throws Exception {
		Assert.assertTrue(tabDisplayGroups.verifyDisplayGroupCount(displayName,
				contentGroup, sortBy, searchTerm));
	}

	@Test(dependsOnMethods = { "validateDisplayGroupCount" })
	public void validateDisplayGroupNext()
			throws Exception {
		Assert.assertTrue(tabDisplayGroups
				.verifyNextLinkForDisplayGroup(displayName));
	}

	@Test(dependsOnMethods = { "validateDisplayGroupNext" })
	public void validateDisplayGroupPrevious()
			throws Exception {
		Assert.assertTrue(tabDisplayGroups.verifyPreviousLinkForDisplayGroup());
	}

	@Test(dependsOnMethods = { "validateDisplayGroupPrevious" })
	public void validateTabsAfterNext()
			throws Exception {
		Assert.assertTrue(tabDisplayGroups
				.verifyTabsAfterNext(displayName));
	}

	@Test(dependsOnMethods = { "validateTabsAfterNext" })
	public void validateTabsAfterPrevious()
			throws Exception {
		Assert.assertTrue(tabDisplayGroups
				.verifyTabsAfterPrevious(displayName));
	}

	@Test(dependsOnMethods = { "validateTabsAfterPrevious" })
	public void verifySearchInViewAll() throws Exception {
		Assert.assertTrue(tabDisplayGroups.verifySearchInViewAll());
	}

	@Test(dependsOnMethods = { "verifySearchInViewAll" })
	public void verifyTabsFromDetailedPage() throws Exception {
		Assert.assertTrue(tabDisplayGroups.verifyTabsFromDetailedPage(displayName));
	}

	@Test(dependsOnMethods = { "verifyTabsFromDetailedPage" })
	public void verifyTabsForDetailedPage() throws Exception {
		Assert.assertTrue(tabDisplayGroups.verifyTabsForDetailedPage(displayName));
	}

	@Parameters( { "groupName", "needToSelectDisplayName" })
	@Test(dependsOnMethods = { "verifyTabsForDetailedPage" })
	public void ensureSingleTabIsSelectedOnce(String groupName,
			String needToSelectDisplayName) throws Exception {
		Assert.assertTrue(tabDisplayGroups.ensureSingleTabIsSelectedOnce(
				displayName, needToSelectDisplayName, groupName));
	}

	@Parameters( { "groupName" })
	@Test(dependsOnMethods = { "ensureSingleTabIsSelectedOnce" })
	public void headingMatch(String groupName) throws Exception {
		Assert.assertTrue(tabDisplayGroups.headingMatch(displayName,
				groupName));
	}

	@Test(dependsOnMethods = { "headingMatch" })
	public void verifyRecordsCountWithViewAll()
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(tabDisplayGroups
				.verifyRecordsCountWithViewAll(displayName));
	}

	@Parameters( { "needToSelectDisplayName" })
	@Test(dependsOnMethods = { "verifyRecordsCountWithViewAll" })
	public void verifySearchResult(String needToSelectDisplayName)
			throws Exception {
		Assert.assertTrue(tabDisplayGroups
				.verifySearchResult(needToSelectDisplayName));
	}
}
