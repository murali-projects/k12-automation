package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.ViewStatisticsDocument;

public class ViewStatisticsDocumentTest extends BaseWebPageTest{
	private ViewStatisticsDocument statisticsDocument;

	@Parameters( { "searchTerm" })
	@BeforeTest
	public void setUp(String searchTerm) throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		statisticsDocument = new ViewStatisticsDocument();
	}

	@Test
	public void verifyDisplayGroupName() throws Exception {
		Assert.assertTrue(statisticsDocument.verifyDisplayGroupName());
	}

	@Test(dependsOnMethods = { "verifyDisplayGroupName" })
	public void verifyViewAllIsDisplayed() throws Exception {
		Assert.assertTrue(statisticsDocument.verifyViewAllIsDisplayed());
	}

	@Test(dependsOnMethods = { "verifyViewAllIsDisplayed" })
	public void verifyStatisticsCount() throws Exception {
		Assert.assertTrue(statisticsDocument.verifyStatisticsCount());
	}

	@Test(dependsOnMethods = { "verifyStatisticsCount" })
	public void verifyHyperLinksOfDocuments() throws Exception {
		Assert.assertTrue(statisticsDocument.verifyHyperLinksOfDocuments());
	}
	
	@Test(dependsOnMethods = { "verifyHyperLinksOfDocuments" })
	public void verifyDetailedViewIsDisplayed() throws Exception {
		Assert.assertTrue(statisticsDocument.verifyDetailedViewIsDisplayed());
	}

	@Test(dependsOnMethods = { "verifyDetailedViewIsDisplayed" })
	public void verifyDocumentTitleInDetailedView() throws Exception {
		Assert.assertTrue(statisticsDocument.verifyDocumentTitleInDetailedView());
	}
	
	@Test(dependsOnMethods = { "verifyDocumentTitleInDetailedView" })
	public void verifySearchFieldsPresent() throws Exception {
		Assert.assertTrue(statisticsDocument.verifySearchFieldsPresent());
	}
	
	@Test(dependsOnMethods = { "verifySearchFieldsPresent" })
	public void verifyNoSpeakerIconIsPresent() throws Exception {
		Assert.assertTrue(statisticsDocument.verifyNoSpeakerIconIsPresent());
	}
	
	@Test(dependsOnMethods = { "verifyNoSpeakerIconIsPresent" })
	public void verifyGaleDocumentAndCopyrightDisplayed() throws Exception {
		Assert.assertTrue(statisticsDocument.verifyGaleDocumentAndCopyrightDisplayed());
	}
	
	@Test(dependsOnMethods = { "verifyGaleDocumentAndCopyrightDisplayed" })
	public void verifyImageIsDisplayedInDetailedView() throws Exception {
		Assert.assertTrue(statisticsDocument.verifyStatisticalImageIsDisplayedInDetailedView());
	}

	@Test(dependsOnMethods = { "verifyImageIsDisplayedInDetailedView" })
	public void verifyPreviousNextThumbnailsPresent() throws Exception {
		Assert.assertTrue(statisticsDocument.verifyPreviousNextThumbnailsPresent());
	}
	
	@Test(dependsOnMethods = { "verifyPreviousNextThumbnailsPresent" })
	public void verifyImageByNavigatingFromViewAllPage() throws Exception {
		Assert.assertTrue(statisticsDocument.verifyImageByNavigatingFromViewAllPage());
	}
	
	@Test(dependsOnMethods = { "verifyImageByNavigatingFromViewAllPage" })
	public void verifyContentForStatisticImage() throws Exception {
		Assert.assertTrue(statisticsDocument.verifyContentForStatisticImage());
	}

}
