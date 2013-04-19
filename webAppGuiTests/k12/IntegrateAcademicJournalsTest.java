package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.IntegrateAcademicJournalsDisplayGroup;

public class IntegrateAcademicJournalsTest extends BaseWebPageTest {
	private IntegrateAcademicJournalsDisplayGroup academicJournals;

	@Parameters( { "searchTerm" })
	@BeforeTest
	public void setUp(String searchTerm) throws Exception {
		academicJournals = new IntegrateAcademicJournalsDisplayGroup();
		doBasicSearchUsingSearchTerm(searchTerm);
	}

	@Test
	public void checkJounalsDisplayedInDisplayGroup() throws Exception {
		Assert.assertTrue(academicJournals.verifyTopThreeDocumentsArePresent());
	}

	@Test(dependsOnMethods = { "checkJounalsDisplayedInDisplayGroup" })
	public void verifyViewAllIsNotDisplayed() throws Exception {
		Assert.assertTrue(academicJournals.verifyViewAllNotDisplayed());
	}

	@Test(dependsOnMethods = { "verifyViewAllIsNotDisplayed" })
	public void verifyViewAllIsDisplayed() throws Exception {
		Assert.assertTrue(academicJournals.verifyViewAllIsDisplayed());
	}

	@Test(dependsOnMethods = { "verifyViewAllIsDisplayed" })
	public void verifyCountIsDisplayed() throws Exception {
		Assert.assertTrue(academicJournals.verifyCountIsDisplayed());
	}

	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods = { "verifyCountIsDisplayed" })
	public void verifyContentLinksAreSortedByPublicationDate(String searchTerm)
			throws Exception {
		Assert.assertTrue(academicJournals
				.verifyContentLinksAreSortedByPublicationDate(searchTerm,
						"Academic Journals", "Relevance"));
	}

	@Test(dependsOnMethods = { "verifyCountIsDisplayed" })
	public void verifyViewAllIsAccessible() throws Exception {
		Assert.assertTrue(academicJournals.verifyViewAllIsAccessible());
	}

	@Test(dependsOnMethods = { "verifyViewAllIsAccessible" })
	public void verifyGroupNameIsDisplayedInCaps() throws Exception {
		Assert.assertTrue(academicJournals.verifyGroupNameIsDisplayedInCaps());
	}

	@Test(dependsOnMethods = { "verifyGroupNameIsDisplayedInCaps" })
	public void verifyPublicationDetailsPresent() throws Exception {
		Assert.assertTrue(academicJournals.verifyPublicationDetailsPresent());
	}

	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods = { "verifyPublicationDetailsPresent" })
	public void verifyContentLinkIsWorking(String searchTerm) throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(academicJournals.verifyContentLinkIsWorking());
	}

	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods = { "verifyContentLinkIsWorking" })
	public void verifyAcademicJournalCanBeMarked(String searchTerm)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(academicJournals.verifyAcademicJournalCanBeMarked());
	}

	@Parameters( { "tabName" })
	@Test(dependsOnMethods = { "verifyAcademicJournalCanBeMarked" })
	public void verifyAfterClickingAcademicJournalsLink(String tabName) throws Exception {
		Assert.assertTrue(academicJournals
				.verifyAfterClickingAcademicJournalsLink(tabName));
	}
}
