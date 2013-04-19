package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.HitcountAndSortingDocumentRelatedSubjects;

public class HitcountAndSortingDocumentsRelatedSubjectsTest extends
		BaseWebPageTest {

	public HitcountAndSortingDocumentRelatedSubjects hitcountandsortingDocumentRelatedSubjects;

	@Parameters( { "searchTerm" })
	@BeforeTest
	public void setUp(String searchTerm) throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		hitcountandsortingDocumentRelatedSubjects = new HitcountAndSortingDocumentRelatedSubjects();
	}

	@Parameters( { "searchTerm" })
	@Test
	public void verifyQuickSearchSidebarisPresent(String searchTerm)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(hitcountandsortingDocumentRelatedSubjects
				.verifyQuickSearchSidebarisPresent());

	}

	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods = { "verifyQuickSearchSidebarisPresent" })
	public void verifyHitcountForAllDisplayGroups(String searchTerm)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(hitcountandsortingDocumentRelatedSubjects
				.verifyHitcountForAllDisplayGroups());

	}

	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods = { "verifyHitcountForAllDisplayGroups" })
	public void verifyOnlyFiveLinksDisplayedInRelatedResults(String searchTerm)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(hitcountandsortingDocumentRelatedSubjects
				.verifyOnlyFiveLinksDisplayedInRelatedResults());

	}

	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods = { "verifyOnlyFiveLinksDisplayedInRelatedResults" })
	public void verifyMoreLinkForMoreThanFiveResults(String searchTerm)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(hitcountandsortingDocumentRelatedSubjects
				.verifyMoreLinkForMoreThanFiveResults());

	}
	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods = { "verifyMoreLinkForMoreThanFiveResults" })
	public void verifyAccesibilityOfMoreLink(String searchTerm)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(hitcountandsortingDocumentRelatedSubjects
				.verifyAccesibilityOfMoreLink());

	}

	
	

	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods = { "verifyAccesibilityOfMoreLink" })
	public void verifyDescendingAfterLessLink(String searchTerm)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(hitcountandsortingDocumentRelatedSubjects
				.verifyDescendingAfterLessLink());

	}

	
	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods = { "verifyAccesibilityOfMoreLink" })
	public void verifySortingOrderAfterClickingMore(String searchTerm)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(hitcountandsortingDocumentRelatedSubjects
				.verifySortingOrderAfterClickingMore());

	}

	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods = { "verifySortingOrderAfterClickingMore" })
	public void verifyFunctionalityOfLessLink(String searchTerm)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(hitcountandsortingDocumentRelatedSubjects
				.verifyFunctionalityOfLessLink());

	}

	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods = { "verifyFunctionalityOfLessLink" })
	public void verifySortingOfRelatedSubjectTerms(String searchTerm)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(hitcountandsortingDocumentRelatedSubjects
				.verifySortingOfRelatedSubjectTerms());

	}

	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods = { "verifySortingOfRelatedSubjectTerms" })
	public void verifyNavigationthroughRelatedSubjectTerm(String searchTerm)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(hitcountandsortingDocumentRelatedSubjects
				.verifyNavigationthroughRelatedSubjectTerm());

	}

	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods = { "verifyNavigationthroughRelatedSubjectTerm" })
	public void verifyHitcountByaddingAllDispalyGroups(String searchTerm)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(hitcountandsortingDocumentRelatedSubjects
				.verifyHitcountByaddingAllDispalyGroups());

	}

	@Parameters( { "searchTerm" })
		@Test(dependsOnMethods = { "verifyNavigationthroughRelatedSubjectTerm" })
	//@Test(dependsOnMethods = { "verifyHitcountByaddingAllDispalyGroups" })
	public void verifyViewingOtherSubjectsByNavigation(String searchTerm)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(hitcountandsortingDocumentRelatedSubjects
				.verifyViewingOtherSubjectsByNavigation());

	}

	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods = { "verifyViewingOtherSubjectsByNavigation" })
	public void verifyNoMoreLinkForLessThanFiveResults(String searchTerm)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(hitcountandsortingDocumentRelatedSubjects
				.verifyNoMoreLinkForLessThanFiveResults());

	}
}
