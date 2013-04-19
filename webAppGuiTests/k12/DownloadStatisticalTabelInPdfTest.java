package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.DownloadStatisticalTabelInPdf;

public class DownloadStatisticalTabelInPdfTest extends BaseWebPageTest {

	public DownloadStatisticalTabelInPdf downloadStatisticalTabelInPdf;
	private String displayGroup;
	
	@BeforeTest
	public void serUP() throws Exception {
		downloadStatisticalTabelInPdf = new DownloadStatisticalTabelInPdf();
	}
	
	@Parameters( { "searchTerm", "displayGroup" })
	@Test
	public void basicSearch(String searchTerm, String displayGroup) throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		this.displayGroup = displayGroup;
	}

	@Test(dependsOnMethods = { "basicSearch" })
	public void verifyDownloadOptionAvailable() throws Exception {
		Assert.assertTrue(downloadStatisticalTabelInPdf
				.verifyDownloadOption(displayGroup));
	}

	@Test(dependsOnMethods = { "verifyDownloadOptionAvailable" })
	public void verifyDownloadOptionInViewAllPage() throws Exception {
		doBasicSearchUsingSearchTerm("test");
		Assert.assertTrue(downloadStatisticalTabelInPdf
				.verifyDownloadInViewAllPage(displayGroup));
	}

	@Test(dependsOnMethods = { "verifyDownloadOptionInViewAllPage" })
	public void verifyModalWindoIsDisplayed() throws Exception {
		Assert.assertTrue(downloadStatisticalTabelInPdf
				.verifyModalWindowIsDisplayed());
	}

	@Test(dependsOnMethods = { "verifyModalWindoIsDisplayed" })
	public void verifyRadioButtonsPresent() throws Exception {
		Assert.assertTrue(downloadStatisticalTabelInPdf
				.verifyRadioButtonsPresent());
	}

	@Test(dependsOnMethods = { "verifyRadioButtonsPresent" })
	public void verifyCancelAndDownloadButtons() throws Exception {
		Assert.assertTrue(downloadStatisticalTabelInPdf
				.verifyCancelAndDownloadButtons());
	}
	@Test(dependsOnMethods = { "verifyCancelAndDownloadButtons" })
	public void verifyModalWindowIsClosed() throws Exception {
		Assert.assertTrue(downloadStatisticalTabelInPdf
				.verifyModalWindowIsClosed());
	}
	@Test(dependsOnMethods = { "verifyModalWindowIsClosed" })
	public void verifyDownloadCancelled() throws Exception {
		Assert.assertTrue(downloadStatisticalTabelInPdf
				.verifyDownloadedCancelled("Document"));
	}
	@Test(dependsOnMethods = { "verifyModalWindowIsClosed" })
	public void verifyPdfDownloaded() throws Exception {
		Assert.assertTrue(downloadStatisticalTabelInPdf.downlaodPdfFile("Document"));
	}
	
	@Test(dependsOnMethods = { "verifyPdfDownloaded" })
	public void VerifyDownloadedFileIsPdfOrNot() throws Exception {
		Assert.assertTrue(downloadStatisticalTabelInPdf.downloadedFileIsPdfOrNot());
	}
	


}
