package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.TextDocumentDownload;

public class TextDocumentDownloadTest extends BaseWebPageTest{
	private TextDocumentDownload textDocumentDownload;
	private String searchTerm;

	@Parameters( { "searchTerm" })
	@BeforeTest
	public void setUp(String searchTerm) throws Exception {
		textDocumentDownload = new TextDocumentDownload();
		this.searchTerm = searchTerm;
	}
	
	@Test
	public void verifyDownloadOptionAvailable() throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(textDocumentDownload.verifyDownloadOptionAvailable());
	}
	
	@Test(dependsOnMethods = { "verifyDownloadOptionAvailable" })
	public void checkDownloadedHtmlDocument() throws Exception {
		Assert.assertTrue(textDocumentDownload.checkDownloadedHtmlDocument());
	}
	
	@Parameters({"criticalThinkingDocuments_searchTerm"})
	@Test(dependsOnMethods = { "checkDownloadedHtmlDocument" })
	public void checkHtmlDocumentOfCriticalThinkingDocuments(String criticalThinkingDocuments_searchTerm) throws Exception {
		doBasicSearchUsingSearchTerm(criticalThinkingDocuments_searchTerm);
		Assert.assertTrue(textDocumentDownload.checkHtmlDocumentOfCriticalThinkingDocuments());
	}
	
	@Test(dependsOnMethods = { "checkHtmlDocumentOfCriticalThinkingDocuments" })
	public void verifyHtmlDocumentForAllDisplayGroups() throws Exception {
		Assert.assertTrue(textDocumentDownload.verifyHtmlDocumentForAllDisplayGroups());
	}
	
	@Test(dependsOnMethods = { "verifyHtmlDocumentForAllDisplayGroups" })
	public void verifyRelatedTopicsAndToolsNotAvailableInHtmlDocument() throws Exception {
		Assert.assertTrue(textDocumentDownload.verifyRelatedTopicsAndToolsNotAvailableInHtmlDocument());
	}
}
