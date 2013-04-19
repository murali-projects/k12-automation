package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.DocumentTitleMaximumLength;

public class DocumentTitleMaximumLengthTest extends BaseWebPageTest{
	private DocumentTitleMaximumLength documentTitleMaxLength;

	@Parameters( { "searchTerm", "displayGroupName" })
	@BeforeTest
	public void setUp(String searchTerm, String displayGroupName) throws Exception {
		documentTitleMaxLength = new DocumentTitleMaximumLength(displayGroupName);
		doBasicSearchUsingSearchTerm(searchTerm);
	}
	
	@Test
	public void verifyElipsesDisplayedInSearchResultsPage() throws Exception {
		Assert.assertTrue(documentTitleMaxLength.verifyElipsesDisplayedInSearchResultsPage());
	}
	
	@Test(dependsOnMethods = { "verifyElipsesDisplayedInSearchResultsPage" })
	public void verifyDetailedPageDocTitleFromSearchResultsPage() throws Exception {
		Assert.assertTrue(documentTitleMaxLength.verifyDetailedPageDocTitleFromSearchResultsPage());
	}
	
	@Test(dependsOnMethods = { "verifyDetailedPageDocTitleFromSearchResultsPage" })
	public void verifyDocumentTitlesLengthInViewAllPage() throws Exception {
		Assert.assertTrue(documentTitleMaxLength.verifyDocumentTitleLengthInViewAllPage());
	}
	
	@Test(dependsOnMethods = { "verifyDocumentTitlesLengthInViewAllPage" })
	public void verifyElipsesDisplayedInViewAllPage() throws Exception {
		Assert.assertTrue(documentTitleMaxLength.verifyElipsesDisplayedInViewAllPage());
	}
	
	@Test(dependsOnMethods = { "verifyElipsesDisplayedInViewAllPage" })
	public void verifyDetailedPageDocTitleFromViewAllPage() throws Exception {
		Assert.assertTrue(documentTitleMaxLength.verifyDetailedPageDocTitleFromViewAllPage());
	}
	
	@Test(dependsOnMethods = { "verifyDetailedPageDocTitleFromViewAllPage" })
	public void verifyNoElipsesAreDisplayedInDetailedPage() throws Exception {
		Assert.assertTrue(documentTitleMaxLength.verifyNoElipsesAreDisplayedInDetailedPage());
	}
	
	@Test(dependsOnMethods = { "verifyNoElipsesAreDisplayedInDetailedPage" })
	public void verifyDocTitleLengthForAllBuckets() throws Exception {
		Assert.assertTrue(documentTitleMaxLength.verifyDocTitleLengthForAllBuckets());
	}
	
	@Test(dependsOnMethods = { "verifyDocTitleLengthForAllBuckets" })
	public void verifyDocumentTitlesLessThanMaxlengthAreAccessible() throws Exception {
		Assert.assertTrue(documentTitleMaxLength.verifyDocumentTitlesLessThanMaxlengthAreAccessible());
	}
}
