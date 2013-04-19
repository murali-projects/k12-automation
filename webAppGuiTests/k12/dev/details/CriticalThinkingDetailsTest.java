package k12.dev.details;

import k12.dev.BaseDevWebPageTest;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import webPageContainers4Testing.dev.ListViewPortlet;

public class CriticalThinkingDetailsTest extends BaseDevWebPageTest {

	
	private static final String SEARCH_TERM = "Capital Punishment Does in Fact Deter Crime";
	private ListViewPortlet viewViewpointsDisplayGroup;

	@BeforeTest
	public void setUp() throws Exception {
		viewViewpointsDisplayGroup = new ListViewPortlet("viewpoints");
	}
	
	@Test
	public void searchValidationMessageIsDisplayed() throws Exception {
		doBasicSearchUsingSearchTerm(SEARCH_TERM);
	}
	
	@Test(dependsOnMethods = { "searchValidationMessageIsDisplayed" })
	public void isListPortletPresent() throws Exception {
		Assert.assertTrue(viewViewpointsDisplayGroup.checkDisplayGroupIsPresent());
	}
	
	@Test(dependsOnMethods = { "isListPortletPresent" })
	public void isQuestionsToThinkAboutTitlePresent() throws Exception {
		viewViewpointsDisplayGroup.goToCriticalThinkingDocument();
		
		Assert.assertTrue(viewViewpointsDisplayGroup.checkSectionTitlesForCriticalThinkingDocumentOnly(
				"questions_to_think", "Questions To Think About"));
	}

	@Test(dependsOnMethods = { "isQuestionsToThinkAboutTitlePresent" })
	public void isWordsToKnowListPresent() throws Exception {
		Assert.assertTrue(viewViewpointsDisplayGroup.checkSectionTitlesForCriticalThinkingDocumentOnly(
				"words_to_know", "Words To Know"));
	}
	
	@Test(dependsOnMethods = { "isWordsToKnowListPresent" })
	public void isWordsToKnowPortletContainsData() throws Exception {
		Assert.assertTrue(viewViewpointsDisplayGroup.isContentPresent("words_to_know_contents"));
	}

	@Test(dependsOnMethods = { "isQuestionsToThinkAboutTitlePresent" })
	public void isTakeQuizTitlePresent() throws Exception {
		Assert.assertTrue(viewViewpointsDisplayGroup.checkSectionTitlesForCriticalThinkingDocumentOnly(
				"take_quiz", "Take the Quiz"));
	}
	

	@Test(dependsOnMethods = { "isQuestionsToThinkAboutTitlePresent" })
	public void isFindOutMoreTitlePresent() throws Exception {
		Assert.assertTrue(viewViewpointsDisplayGroup.checkSectionTitlesForCriticalThinkingDocumentOnly(
				"find_out_more", "Find Out More"));
	}

	@Test(dependsOnMethods = { "isQuestionsToThinkAboutTitlePresent" })
	public void isFurtherReadingTitlePresent() throws Exception {
		Assert.assertTrue(viewViewpointsDisplayGroup.checkSectionTitlesForCriticalThinkingDocumentOnly(
				"further_readings", "Further Readings"));
	}
	
	@Test(dependsOnMethods = { "isFurtherReadingTitlePresent" })
	public void isFurtherReadingBooksTitlePresent() throws Exception {
		Assert.assertTrue(viewViewpointsDisplayGroup.checkSectionTitlesForCriticalThinkingDocumentOnly(
				"further_readings_books", "Books"));
	}
	
	@Test(dependsOnMethods = { "isFurtherReadingBooksTitlePresent" })
	public void isFurtherReadingBooksListPresent() throws Exception {
		Assert.assertTrue(viewViewpointsDisplayGroup.isContentPresent("further_readings_books_contents"));
	}
	
	@Test(dependsOnMethods = { "isFurtherReadingTitlePresent" })
	public void isFurtherReadingPeriodicalsTitlePresent() throws Exception {
		Assert.assertTrue(viewViewpointsDisplayGroup.checkSectionTitlesForCriticalThinkingDocumentOnly(
				"further_readings_periodicals", "Periodicals"));
	}
	
	@Test(dependsOnMethods = { "isFurtherReadingPeriodicalsTitlePresent" })
	public void isFurtherReadingPeriodicalsListPresent() throws Exception {
		Assert.assertTrue(viewViewpointsDisplayGroup.isContentPresent("further_readings_periodicals"));
	}

	@Test(dependsOnMethods = { "isFurtherReadingPeriodicalsListPresent" })
	public void isFooterPresent() throws Exception {
		Assert.assertTrue(viewViewpointsDisplayGroup.doesContentExistInClass(viewViewpointsDisplayGroup.getProperty("footerDiv"),
				"footerGaleLogo"));
	}
}
