package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.DownloadDocumentInHTMLFormat;

public class DownloadDocumentInHTMLFormatTest extends BaseWebPageTest {

	private DownloadDocumentInHTMLFormat downloadDocument;
	private String displayGroup;
	private String searchTerm;

	@Parameters( { "searchTerm", "displayGroup" })
	@BeforeTest
	public void setUp(String searchTerm, String displayGroup) throws Exception {
		downloadDocument = new DownloadDocumentInHTMLFormat();
		this.displayGroup = displayGroup;
		this.searchTerm=searchTerm;
		doBasicSearchUsingSearchTerm(searchTerm);
	}

	@Test
	public void verifyDownloadOptionAvailable() throws Exception {
		Assert.assertTrue(downloadDocument
				.downloadOptionAvailable(displayGroup));
	}
	
	@Test(dependsOnMethods = { "verifyDownloadOptionAvailable" })
	public void verifyDownloadOptionAvailableFromViewAll() throws Exception {
		Assert.assertTrue(downloadDocument
				.downloadOptionAvailableFromViewAll(displayGroup));
	}

	@Parameters( { "downloadFileName", "displayGroup" })
	@Test(dependsOnMethods = { "verifyDownloadOptionAvailableFromViewAll" })
	public void DownloadHtmlFile(String downloadFileName, String displayGroup)
			throws Exception {
		Assert.assertTrue(downloadDocument.downloadHtmlFile(downloadFileName, displayGroup));
	}
	
	@Test(dependsOnMethods = { "DownloadHtmlFile" })
	public void VerifyDownloadedFileIsHtmlOrNot() throws Exception {
		Assert.assertTrue(downloadDocument.downloadedFileIsHtmlOrNot());
	}
	
	@Test(dependsOnMethods = { "VerifyDownloadedFileIsHtmlOrNot" })
	public void verifyDownloadLink() throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(downloadDocument.clickDownloadLink(displayGroup));
	}

	@Test(dependsOnMethods = { "verifyDownloadLink" })
	public void chooseHtmlOption() throws Exception {
		Assert.assertTrue(downloadDocument.chooseHtmlOption());
	}
}
