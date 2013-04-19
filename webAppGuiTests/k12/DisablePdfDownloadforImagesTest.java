package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.DisablePdfDownloadforImagesStatisticsAndWebsites;
import webPageContainers4Testing.HitcountAndSortingDocumentRelatedSubjects;

public class DisablePdfDownloadforImagesTest extends BaseWebPageTest {

	
	public DisablePdfDownloadforImagesStatisticsAndWebsites disablepdfdownload;
	
	@Parameters( { "searchTerm" })
	@BeforeTest
	public void setUp(String searchTerm) throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		disablepdfdownload = new DisablePdfDownloadforImagesStatisticsAndWebsites();
	}

	@Parameters( { "searchTerm" })
	@Test
	public void verifyDownloadOptionAvailableForImageStatisticAndWebsites(String searchTerm)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(disablepdfdownload
				.verifyDownloadOptionAvailableForImageStatisticAndWebsites());

	}

	
	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods = { "verifyDownloadOptionAvailableForImageStatisticAndWebsites" })
	public void verify3optionsavailableForDownload(String searchTerm)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(disablepdfdownload.verify3optionsavailableForDownload());

	}

	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods = { "verify3optionsavailableForDownload" })
	public void verifyPdfRadioButtonNotAvailable(String searchTerm)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(disablepdfdownload
				.verifyPdfRadioButtonNotAvailable());

	}
	
	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods = { "verifyPdfRadioButtonNotAvailable" })
	public void verifyPdfOptionFromKeyboard(String searchTerm)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(disablepdfdownload
				.verifyPdfOptionFromKeyboard());

	}
	
	
	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods = { "verifyPdfOptionFromKeyboard" })
	public void verifyRadioButtonForallOtherDisplayGroups(String searchTerm)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(disablepdfdownload
				.verifyRadioButtonForallOtherDisplayGroups());

	}
	
	

	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods = { "verifyRadioButtonForallOtherDisplayGroups" })
	public void verifyPdfFunctionsForAllotherBuckets(String searchTerm)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(disablepdfdownload
				.verifyPdfFunctionsForAllotherBuckets());

	}
	
	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods = { "verifyPdfFunctionsForAllotherBuckets" })
	public void verifyPdfNotSelectableForImageStatisticsAndWebsite(String searchTerm)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(disablepdfdownload
				.verifyPdfNotSelectableForImageStatisticsAndWebsite());

	}
	
	

	
	
	
	
	
}
