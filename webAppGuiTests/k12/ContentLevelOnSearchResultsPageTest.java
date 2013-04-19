package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.ContentLevelOnSearchResultsPage;

import k12.BaseWebPageTest;

public class ContentLevelOnSearchResultsPageTest extends BaseWebPageTest {

	public ContentLevelOnSearchResultsPage contentLevelOnSearchResultsPage;

	@BeforeTest
	public void setUp() throws Exception {
		contentLevelOnSearchResultsPage = new ContentLevelOnSearchResultsPage();
	}

	@Test
	public void verifyContentLevelIsDisplayedForReference() throws Exception {
		doBasicSearchUsingSearchTerm("test");
		Assert.assertTrue(contentLevelOnSearchResultsPage
				.verifyIndicatorIsDisplayed("reference"));
	}

	@Test(dependsOnMethods = { "verifyContentLevelIsDisplayedForReference" })
	public void verifyToolTipIsDisplayedForReference() throws Exception {
		Assert.assertTrue(contentLevelOnSearchResultsPage
				.verifyToolTipIsDisplayed());
	}

	@Parameters( { "searchTerm", "displayGroupReference", "sortby" })
	@Test(dependsOnMethods = { "verifyToolTipIsDisplayedForReference" })
	public void validateToolTipWithOCeanForReference() throws Exception {
		Assert.assertTrue(contentLevelOnSearchResultsPage
				.validateToolTipMessages("test", "K12-Reference", "relevance"));
	}

	@Test(dependsOnMethods = { "validateToolTipWithOCeanForReference" })
	public void verifyContentLevelIsDisplayedForAllContentsForReference()
			throws InterruptedException {
		Assert.assertTrue(contentLevelOnSearchResultsPage
				.verifyContentLevelDisplayedInAll("reference"));
	}

	// primarysources

	@Test(dependsOnMethods = { "verifyContentLevelIsDisplayedForAllContentsForReference" })
	public void verifyContentLevelIsDisplayedForPrimarySources()
			throws Exception {
		doBasicSearchUsingSearchTerm("test");
		Assert.assertTrue(contentLevelOnSearchResultsPage
				.verifyIndicatorIsDisplayed("primarysources"));
	}

	@Test(dependsOnMethods = { "verifyContentLevelIsDisplayedForPrimarySources" })
	public void verifyToolTipIsDisplayedForPrimarySources() throws Exception {
		Assert.assertTrue(contentLevelOnSearchResultsPage
				.verifyToolTipIsDisplayed());
	}

	@Test(dependsOnMethods = { "verifyToolTipIsDisplayedForPrimarySources" })
	public void validateToolTipWithOCeanForPrimarySources() throws Exception {
		Assert.assertTrue(contentLevelOnSearchResultsPage
				.validateToolTipMessages("test", "K12-PrimarySources",
						"relevance"));
	}

	@Test(dependsOnMethods = { "validateToolTipWithOCeanForPrimarySources" })
	public void verifyContentLevelIsDisplayedForAllContentsForPrimarySources()
			throws InterruptedException {
		Assert.assertTrue(contentLevelOnSearchResultsPage
				.verifyContentLevelDisplayedInAll("primarysources"));
	}

	// statistics

	@Test(dependsOnMethods = { "verifyContentLevelIsDisplayedForAllContentsForPrimarySources" })
	public void verifyContentLevelIsDisplayedForStatistics() throws Exception {
		doBasicSearchUsingSearchTerm("test");
		Assert.assertTrue(contentLevelOnSearchResultsPage
				.verifyIndicatorIsDisplayed("statistics"));
	}

	@Test(dependsOnMethods = { "verifyContentLevelIsDisplayedForStatistics" })
	public void verifyToolTipIsDisplayedForStatistics() throws Exception {
		Assert.assertTrue(contentLevelOnSearchResultsPage
				.verifyToolTipIsDisplayed());
	}

	@Test(dependsOnMethods = { "verifyToolTipIsDisplayedForStatistics" })
	public void validateToolTipWithOCeanForStatistics() throws Exception {
		Assert.assertTrue(contentLevelOnSearchResultsPage
				.validateToolTipMessages("test", "statistics", "relevance"));
	}

	@Test(dependsOnMethods = { "validateToolTipWithOCeanForStatistics" })
	public void verifyContentLevelIsDisplayedForAllContentsForStatistics()
			throws InterruptedException {
		Assert.assertTrue(contentLevelOnSearchResultsPage
				.verifyContentLevelDisplayedInAll("statistics"));
	}

	// news

	@Test(dependsOnMethods = { "verifyContentLevelIsDisplayedForAllContentsForStatistics" })
	public void verifyContentLevelIsDisplayedForNews() throws Exception {
		doBasicSearchUsingSearchTerm("test");
		Assert.assertTrue(contentLevelOnSearchResultsPage
				.verifyIndicatorIsDisplayed("news"));
	}

	@Test(dependsOnMethods = { "verifyContentLevelIsDisplayedForNews" })
	public void verifyToolTipIsDisplayedForNews() throws Exception {
		Assert.assertTrue(contentLevelOnSearchResultsPage
				.verifyToolTipIsDisplayed());
	}

	@Test(dependsOnMethods = { "verifyToolTipIsDisplayedForNews" })
	public void validateToolTipWithOCeanForNews() throws Exception {
		Assert.assertTrue(contentLevelOnSearchResultsPage
				.validateToolTipMessages("test", "news", "relevance"));
	}

	@Test(dependsOnMethods = { "validateToolTipWithOCeanForNews" })
	public void verifyContentLevelIsDisplayedForAllContentsForNews()
			throws InterruptedException {
		Assert.assertTrue(contentLevelOnSearchResultsPage
				.verifyContentLevelDisplayedInAll("news"));
	}

	// magazines

	@Test(dependsOnMethods = { "verifyContentLevelIsDisplayedForAllContentsForNews" })
	public void verifyContentLevelIsDisplayedForMagazines() throws Exception {
		doBasicSearchUsingSearchTerm("test");
		Assert.assertTrue(contentLevelOnSearchResultsPage
				.verifyIndicatorIsDisplayed("magazines"));
	}

	@Test(dependsOnMethods = { "verifyContentLevelIsDisplayedForMagazines" })
	public void verifyToolTipIsDisplayedForMagazines() throws Exception {
		Assert.assertTrue(contentLevelOnSearchResultsPage
				.verifyToolTipIsDisplayed());
	}

	@Test(dependsOnMethods = { "verifyToolTipIsDisplayedForMagazines" })
	public void validateToolTipWithOCeanForMagazines() throws Exception {
		Assert.assertTrue(contentLevelOnSearchResultsPage
				.validateToolTipMessages("test", "magazines", "relevance"));
	}

	@Test(dependsOnMethods = { "validateToolTipWithOCeanForMagazines" })
	public void verifyContentLevelIsDisplayedForAllContentsForMagazines()
			throws InterruptedException {
		Assert.assertTrue(contentLevelOnSearchResultsPage
				.verifyContentLevelDisplayedInAll("magazines"));
	}

	// viewpoints

	@Test(dependsOnMethods = { "verifyContentLevelIsDisplayedForAllContentsForMagazines" })
	public void verifyContentLevelIsDisplayedForViewPoints() throws Exception {
		doBasicSearchUsingSearchTerm("test");
		Assert.assertTrue(contentLevelOnSearchResultsPage
				.verifyIndicatorIsDisplayed("viewpoints"));
	}

	@Test(dependsOnMethods = { "verifyContentLevelIsDisplayedForViewPoints" })
	public void verifyToolTipIsDisplayedForViewPoints() throws Exception {
		Assert.assertTrue(contentLevelOnSearchResultsPage
				.verifyToolTipIsDisplayed());
	}

	@Test(dependsOnMethods = { "verifyToolTipIsDisplayedForViewPoints" })
	public void validateToolTipWithOCeanForViewPoints() throws Exception {
		Assert.assertTrue(contentLevelOnSearchResultsPage
				.validateToolTipMessages("test", "viewpoints", "relevance"));
	}

	@Test(dependsOnMethods = { "validateToolTipWithOCeanForViewPoints" })
	public void verifyContentLevelIsDisplayedForAllContentsForViewPoints()
			throws InterruptedException {
		Assert.assertTrue(contentLevelOnSearchResultsPage
				.verifyContentLevelDisplayedInAll("viewpoints"));
	}

	// AcademicJournals
	@Test(dependsOnMethods = { "verifyContentLevelIsDisplayedForAllContentsForViewPoints" })
	public void verifyContentLevelIsDisplayedForAcademicJournals()
			throws Exception {
		doBasicSearchUsingSearchTerm("test");
		Assert.assertTrue(contentLevelOnSearchResultsPage
				.verifyIndicatorIsDisplayed("academicjournals"));
	}

	@Test(dependsOnMethods = { "verifyContentLevelIsDisplayedForAcademicJournals" })
	public void verifyToolTipIsDisplayedForAcademicJournals() throws Exception {
		Assert.assertTrue(contentLevelOnSearchResultsPage
				.verifyToolTipIsDisplayed());
	}

	@Test(dependsOnMethods = { "verifyToolTipIsDisplayedForAcademicJournals" })
	public void validateToolTipWithOCeanForAcademicJournals() throws Exception {
		Assert.assertTrue(contentLevelOnSearchResultsPage
				.validateToolTipMessages("test", "academicjournals",
						"relevance"));
	}

	@Test(dependsOnMethods = { "validateToolTipWithOCeanForAcademicJournals" })
	public void verifyContentLevelIsDisplayedForAllContentsForAcademicJournals()
			throws InterruptedException {
		Assert.assertTrue(contentLevelOnSearchResultsPage
				.verifyContentLevelDisplayedInAll("academicjournals"));
	}

}
