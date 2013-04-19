package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.IntegrateStatisticsDisplayGroup;

public class IntegrateStatisticsDisplayGroupTest extends BaseWebPageTest {
	private IntegrateStatisticsDisplayGroup statisticsDisplayGroup;
	private String searchTerm;
	private String displayGroup;
	
	@Parameters( { "searchTerm", "displayGroup"})
	@BeforeTest
	public void setUp(String searchTerm, String displayGroup) throws Exception {
		this.searchTerm = searchTerm;
		this.displayGroup = displayGroup;
		statisticsDisplayGroup = new IntegrateStatisticsDisplayGroup();
		doBasicSearchUsingSearchTerm(searchTerm);
	}

	@Test
	public void checkContentResultsArePresent() throws Exception {
		Assert.assertTrue(statisticsDisplayGroup
				.checkContentResultsArePresent());
	}

	@Test(dependsOnMethods = { "checkContentResultsArePresent" })
	public void verifyThreeDocumentsArePresent() throws Exception {
		Assert.assertTrue(statisticsDisplayGroup.verifyThreeDocumentsArePresent());
				
	}

	@Test(dependsOnMethods = { "verifyThreeDocumentsArePresent" })
	public void verifyViewAllIsDisplayedWithResultsCount() throws Exception {
		Assert.assertTrue(statisticsDisplayGroup.verifyViewAllIsDisplayedWithResultsCount());
	}
	
	@Test(dependsOnMethods = { "verifyViewAllIsDisplayedWithResultsCount" })
	public void validateDocumentTitlesWithDB() throws Exception {
		Assert.assertTrue(statisticsDisplayGroup.verifyDocumentTitles(searchTerm, displayGroup));
				
	}

	@Test(dependsOnMethods = { "validateDocumentTitlesWithDB" })
	public void verifyDefaultSortIsByRelevance() throws Exception {
		Assert.assertTrue(statisticsDisplayGroup.verifyDocumentTitles(searchTerm, displayGroup));
				
	}
	
	@Test(dependsOnMethods = { "verifyDefaultSortIsByRelevance" })
	public void ensureDocumentsAreRelatedToStatistics() throws Exception {
		Assert.assertTrue(statisticsDisplayGroup.ensureDocumentsAreRelatedToStatistics());
	}
	
	@Test(dependsOnMethods = { "ensureDocumentsAreRelatedToStatistics" })
	public void verifyGroupNameIsDisplayedInViewAllPage() throws Exception {
		Assert.assertTrue(statisticsDisplayGroup.verifyGroupNameIsDisplayedInViewAllPage());
	}

	@Test(dependsOnMethods = { "verifyGroupNameIsDisplayedInViewAllPage" })
	public void verifySearchMessageIsPresent() throws Exception {
		Assert.assertTrue(statisticsDisplayGroup.verifySearchMessage(searchTerm));
	}
	
	@Test(dependsOnMethods = { "verifySearchMessageIsPresent" })
	public void verifyContentLevelIsPresent() throws Exception {
		Assert.assertTrue(statisticsDisplayGroup.verifyContentLevelIsPresent());
	}
	
	@Test(dependsOnMethods = { "verifyContentLevelIsPresent" })
	public void ensureDetailedViewIsDisplayed() throws Exception {
		Assert.assertTrue(statisticsDisplayGroup.isDetailedPageDisplayed());
	}
	
}
